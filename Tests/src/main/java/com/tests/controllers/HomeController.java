package com.tests.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tests.model.Category;
import com.tests.model.Questions;

@Controller
@RequestMapping("/")
public class HomeController extends AbstractProjectController{
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(
			Model model,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		List<Category> res = category.findAll();
		model.addAttribute("category", res);
		
		return "home";
	}
	
	@RequestMapping(params = "category", method = RequestMethod.GET)
	public String homeFromCategory(
			@RequestParam("category") String id,
			@CookieValue(value = "JQSMILE", defaultValue = "", required = false) String jqSmile,
			RedirectAttributes redirectAttrs,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model) {

		try {
//Get questions for category, if not - do nothing
			int res = Integer.parseInt(id);
			List<Questions> allQuestions = questions.findAllFromId(res);
			if (allQuestions.size() == 0) {
				redirectAttrs.addFlashAttribute("err",
						"Извените, такой категории нет!!!");
				return "redirect:/";
			}
//Get shuffle and limit Q_NUMBER collection. Save it in session variables.
			Collections.shuffle(allQuestions);
			List<Questions> allQuestionsLimit = allQuestions
					.parallelStream()
					.limit(Q_NUMBER)
					.collect(Collectors.toList());
			List<Integer> trueAnswers = allQuestionsLimit
					.parallelStream()
					.map(e -> e.getGoodanswer().get(0).getAnswers().getId())
					.collect(Collectors.toList());

			HttpSession session = request.getSession();
			session.setAttribute("allQuestions", allQuestionsLimit);
			session.setAttribute("trueAnswers", trueAnswers);
			session.setAttribute("actualQuestion", allQuestionsLimit.get(0));
			
			Cookie cookie = new Cookie("STATUS", "first");
			cookie.setMaxAge(5);
			response.addCookie(cookie);
			
			if(jqSmile.equals(""))
				return "redirect:/test-no-js";
			else
				return "redirect:/test-js";

		} catch (NumberFormatException e) {
			redirectAttrs.addFlashAttribute("err", "Ну-ну!!!" + e.getMessage());
			return "redirect:/";
		}

	}
	
}
