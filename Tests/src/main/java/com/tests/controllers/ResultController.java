package com.tests.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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

@Controller
@RequestMapping("/result")
public class ResultController extends AbstractProjectController{

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String testRes(
			@CookieValue(value = "ANSWERS", defaultValue = "NaN", required = false) String answers,
			@CookieValue(value = "STATUS", defaultValue = "end", required = false) String status,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		if (status.equals("go")) {

			HttpSession session = request.getSession();
			List<Integer> trueAnswers = (List<Integer>) session
					.getAttribute("trueAnswers");
//Move all answers in List
			List<String> listAnswers = new ArrayList<>(Arrays.asList(answers
					.split(", ")));
//Have many true test answers
			int res = listAnswers.parallelStream()
					.filter(e -> e.matches("\\d+")).map(Integer::new)
					.filter(e -> trueAnswers.contains(e))
					.collect(Collectors.toSet()).size()
					* 100 / Q_NUMBER;

			model.addAttribute("result", res);
//Remove all session
			Enumeration<String> allHttpSession = session.getAttributeNames();

			while (allHttpSession.hasMoreElements()) {
				session.removeAttribute(allHttpSession.nextElement());
			}
//Remove some cookies
			Arrays.asList(request.getCookies())
					.parallelStream()
					.filter(e -> !e.getName().equals("STATUS")
							&& !e.getName().equals("JSESSIONID")
							&& !e.getName().equals("JQSMILE")).forEach(e -> {
						e.setMaxAge(0);
						response.addCookie(e);
					});
//Save result in cookie file
			session.setAttribute("result", res);
			response.addCookie(new Cookie("STATUS", "end"));

			return "test-res";

		} else {

			HttpSession session = request.getSession();

			model.addAttribute("result", session.getAttribute("result"));

			return "test-res";

		}
	}
	
}
