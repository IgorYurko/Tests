package com.tests.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tests.model.Questions;
import com.tests.springform.TestForm;

@Controller
@RequestMapping(value = "/test-no-js")
public class TestNoJsController extends AbstractProjectController{

	@RequestMapping(method = RequestMethod.GET)
	public String testNoJsGet(
			@CookieValue(value = "STATUS", defaultValue = "", required = false) String status,
			RedirectAttributes redirectAttrs, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (status.equals("first")) {
//Add new cookie
			HttpSession session = request.getSession();
			session.setAttribute("timerBegin", (long) (System.currentTimeMillis() + 1000 * 30));
			response.addCookie(new Cookie("STATUS", "go"));
			response.addCookie(new Cookie("ANSWERS", ""));
			response.addCookie(new Cookie("QUEST_NUMBER", "1"));
//If JavaScript is disabled
			Integer timer = Math.round(((long)session.getAttribute("timerBegin") - System.currentTimeMillis())/1000);
			response.addHeader("REFRESH",
					timer + "; url=" + request.getContextPath()
							+ "/test-no-js?nothingSelected");

			model.addAttribute("questions",
					session.getAttribute("actualQuestion"));
			model.addAttribute("testForm", testForm);
			model.addAttribute("timer", timer);

			return "test";

		} else if (status.equals("go")) {

			HttpSession session = request.getSession();
//If JavaScript is disabled
			Integer timer = Math.round(((long)session.getAttribute("timerBegin") - System.currentTimeMillis())/1000);
			response.addHeader("REFRESH",
					timer + "; url=" + request.getContextPath()
							+ "/test-no-js?nothingSelected");

			model.addAttribute("questions",
					session.getAttribute("actualQuestion"));
			model.addAttribute("testForm", testForm);
			model.addAttribute("timer", timer);

			return "test";

		}

		redirectAttrs.addFlashAttribute("err",
				"Куки изменять и удалять нельзя!");
		return "redirect:/";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "nothingSelected", method = RequestMethod.GET)
	public String nothingSelectedTestGet(
			@CookieValue(value = "ANSWERS", defaultValue = "", required = false) String answers,
			@CookieValue(value = "QUEST_NUMBER", defaultValue = "0", required = false) Integer questNumber,
			RedirectAttributes redirectAttrs, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (questNumber != Q_NUMBER) {

			try {
//Add new timer
				HttpSession session = request.getSession();
				session.setAttribute("timerBegin", (long)(System.currentTimeMillis() + 1000*31));
				List<Questions> allQuestions = (List<Questions>) session
						.getAttribute("allQuestions");
				session.setAttribute("actualQuestion",
						allQuestions.get(questNumber));

				response.addCookie(new Cookie("ANSWERS", answers + "NaN, "));
				response.addCookie(new Cookie("QUEST_NUMBER", (++questNumber)
						.toString()));

				return "redirect:/test-no-js";

			} catch (NullPointerException e) {

				redirectAttrs.addFlashAttribute("err",
						"Не надо пытатся жульничать!");
				return "redirect:/";

			}

		} else {

			response.addCookie(new Cookie("ANSWERS", answers + "NaN, "));

			return "redirect:/result";

		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public String testNoJsPost(
			@CookieValue(value = "ANSWERS", defaultValue = "", required = false) String answers,
			@CookieValue(value = "QUEST_NUMBER", defaultValue = "0", required = false) Integer questNumber,
			@ModelAttribute TestForm testForm,
			RedirectAttributes redirectAttrs, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (questNumber != Q_NUMBER) {

			try {
//Add new timer
				HttpSession session = request.getSession();
				session.setAttribute("timerBegin", (long)(System.currentTimeMillis() + 1000*31));
				List<Questions> allQuestions = (List<Questions>) session
						.getAttribute("allQuestions");
				session.setAttribute("actualQuestion",
						allQuestions.get(questNumber));

				response.addCookie(new Cookie("ANSWERS", answers
						+ testForm.getAnswerForm().toString() + ", "));
				response.addCookie(new Cookie("QUEST_NUMBER", (++questNumber)
						.toString()));

				return "redirect:/test-no-js";

			} catch (NullPointerException e) {

				redirectAttrs.addFlashAttribute("err",
						"Не надо пытатся жульничать!");
				return "redirect:/";

			}

		} else {

			response.addCookie(new Cookie("ANSWERS", answers
					+ testForm.getAnswerForm().toString() + ", "));

			return "redirect:/result";

		}

	}

}
