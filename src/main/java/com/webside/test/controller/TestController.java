package com.webside.test.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;



@Controller
public class TestController {
	@RequestMapping("/shiroSet")
	@ResponseBody
	public String shiroSet() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("aa","11"); // 获取session中的验证码
		return "成功";
	}
	@RequestMapping("/shiroGet")
	@ResponseBody
	public String shiroGet() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String result = (String) session.getAttribute("aa"); // 获取session中的验证码
		String expected = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY); // 获取session中的验证码
		System.out.println(result);
		System.out.println(expected);
		return result+expected;
	}
}
