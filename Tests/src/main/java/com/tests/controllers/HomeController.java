package com.tests.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tests.model.Category;
import com.tests.model.Questions;
import com.tests.service.ICategoryService;
import com.tests.service.IQuestionsService;
import com.tests.springform.TestForm;

@Controller
public class HomeController {
	
	@Autowired
	private ICategoryService category;
	
	@Autowired
	private IQuestionsService questions;
	
	@Autowired
	private TestForm testForm;
	
	private final int Q_NUMBER = 10;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		List<Category> res = category.findAll();
		model.addAttribute("category", res);
		return "home";
	}
	
	
	@RequestMapping(value = "/", params = "category", method = RequestMethod.GET)
	public String homeFromCategory(@RequestParam("category") String id,
									RedirectAttributes redirectAttrs,
									HttpServletRequest request,
									HttpServletResponse response,
									Model model) {

		try {
			int res = Integer.parseInt(id);
			List<Questions> allQuestions = questions.findAllFromId(res);
			if (allQuestions.size() == 0) {
				redirectAttrs.addFlashAttribute("err",
						"Извените, такой категории нет!!!");
				return "redirect:/";
			}

			Collections.shuffle(allQuestions);
			List<Questions> allQuestionsLimit = allQuestions.parallelStream()
					.limit(Q_NUMBER)
					.collect(Collectors.toList());
			List<Integer> trueAnswers = allQuestionsLimit.parallelStream()
					.map(e -> e.getGoodanswer().get(0).getAnswers().getId())
					.collect(Collectors.toList());
			
			HttpSession session = request.getSession();
			session.setAttribute("allQuestions", allQuestionsLimit);
			session.setAttribute("trueAnswers", trueAnswers);
			session.setAttribute("actualQuestion", allQuestionsLimit.get(0));
			
			Cookie cookie = new Cookie("STATUS", "first");
			cookie.setMaxAge(5);
			response.addCookie(cookie);
			
			return "redirect:/test";

		} catch (NumberFormatException e) {
			redirectAttrs.addFlashAttribute("err", "Ну-ну!!!" + e.getMessage());
			return "redirect:/";
		}

	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testGet(@CookieValue(value = "STATUS", defaultValue = "NaN", required = false) String status,
							RedirectAttributes redirectAttrs,
							HttpServletRequest request,
							HttpServletResponse response,
							Model model) {

		if (status.equals("first")) {

			HttpSession session = request.getSession();
			response.addCookie(new Cookie("STATUS", "go"));
			response.addCookie(new Cookie("ANSWERS", ""));
			response.addCookie(new Cookie("QUEST_NUMBER", "1"));
			
			model.addAttribute("questions",
					session.getAttribute("actualQuestion"));
			model.addAttribute("testForm", testForm);

			return "test";

		} else if (status.equals("go")) {

			HttpSession session = request.getSession();

			model.addAttribute("questions",
					session.getAttribute("actualQuestion"));
			model.addAttribute("testForm", testForm);

			return "test";

		}
		
		redirectAttrs.addFlashAttribute("err", "Куки изменять и удалять нельзя!");
		return "redirect:/";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping( value="/test", method = RequestMethod.POST)
	public String testPost(@CookieValue(value = "ANSWERS", defaultValue = "", required = false ) String answers,
							@CookieValue(value = "QUEST_NUMBER", defaultValue = "0", required = false) Integer questNumber,
							@ModelAttribute TestForm testForm,
							RedirectAttributes redirectAttrs,
							HttpServletRequest request,
							HttpServletResponse response,
							Model model){
		
		if (questNumber != Q_NUMBER) {

			try{
			
				HttpSession session = request.getSession();
				List<Questions> allQuestions = (List<Questions>) session
						.getAttribute("allQuestions");
				session.setAttribute("actualQuestion",
						allQuestions.get(questNumber));
	
				response.addCookie(new Cookie("ANSWERS", answers
						+ testForm.getAnswerForm().toString() + ", "));
				response.addCookie(new Cookie("QUEST_NUMBER", (++questNumber)
						.toString()));
	
				return "redirect:/test";
				
			}catch(NullPointerException e){
				
				redirectAttrs.addFlashAttribute("err", "Не надо пытатся жульничать!");
				return "redirect:/";
				
			}
			
		}else{
			
			response.addCookie(new Cookie("ANSWERS", answers
					+ testForm.getAnswerForm().toString() + ", "));
			
			return "redirect:/result";
			
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	@RequestMapping( value = "/result", method = RequestMethod.GET)
	public String testRes(@CookieValue(value ="ANSWERS", defaultValue = "NaN", required = false) String answers,
							@CookieValue(value = "STATUS", defaultValue = "end", required = false) String status,
							HttpServletRequest request,
							HttpServletResponse response,
							Model model){
		
		if( status.equals("go")){
		
			HttpSession session = request.getSession();
			List<Integer> trueAnswers = (List<Integer>) session.getAttribute("trueAnswers");
			
			List<String> listAnswers = new ArrayList<>(Arrays.asList(answers.split(", ")));
	
			int res = listAnswers.parallelStream()
									.filter(e -> e.matches("\\d+"))
									.map(Integer::new)
									.filter(e -> trueAnswers.contains(e))
									.collect(Collectors.toSet())
									.size()
									*100/Q_NUMBER;
			
			
			model.addAttribute("result", res);
			
			Enumeration<String> allHttpSession = session.getAttributeNames();
			
			while(allHttpSession.hasMoreElements()){
				session.removeAttribute(allHttpSession.nextElement());
			}
			
			Arrays.asList(request.getCookies()).parallelStream()
									.filter(e -> !e.getName().equals("STATUS") && !e.getName().equals("JSESSIONID")
														&& !e.getName().equals("JQSMILE"))
									.forEach(e ->
												{
													e.setMaxAge(0);
													response.addCookie(e);
												});
			
			session.setAttribute("result", res);
			response.addCookie(new Cookie("STATUS", "end"));
			
			return "test-res";
		
		}else{
		
			HttpSession session = request.getSession();
			
			model.addAttribute("result", session.getAttribute("result"));
		
		return "test-res";
		
		}
	}
	
}
