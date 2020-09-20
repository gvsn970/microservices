package com.nexii.fileupload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexii.fileupload.model.EmpModel;
import com.nexii.fileupload.service.EmpService;

@Controller
public class MainController {

	@Autowired
	private EmpService empService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/regFrom")
	public String regForm() {
		return "registarion";
	}

	@GetMapping("/loginFrom")
	public String loginFrom() {
		return "login";
	}

	@PostMapping("/reg-emp")
	public String regEmp(@ModelAttribute EmpModel empModel, BindingResult bindingResult, HttpServletRequest request) {
		empService.save(empModel);
		HttpSession session = request.getSession(true);
		session.setAttribute("username", empModel.getUsername());
		request.setAttribute("sucessMsg", "Employee Registation Sucess...");
		return "welcome";
	}
}
