package com.banepali.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.dto.EmployeeDto;
import com.banepali.service.EmployeeService;

//@Repository(helloController)  //we can use this but, as this is a model, we must use the "@Controller"
@Controller
public class LoginController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping({ "/", "index" })
	public String indexPage() {
		return "index";
	}

	@PostMapping("/login")
	public String loginPost(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {
		EmployeeDto optionalEmplDto = employeeService.authUser(email, password);
		if (optionalEmplDto != null) {
			session.setAttribute("userData", optionalEmplDto);
			return "dashboard";
		} else {
			model.addAttribute("message", "Sorry, Incorrect Email or password.");
			return "index";
		}
	}

	@GetMapping("/dashboard")
	public String openDashboard() {
		return "dashboard";
	}

	@PostMapping("/dashboard")
	public String openDashboardPost() {
		return "redirect:/login";
	}

	@GetMapping("/validateEmail")
	public String forgotPassword() {
		return "validateEmail";
	}

	@PostMapping("/validateEmailSearch")
	public String validateEmail(@RequestParam String email, HttpSession session, Model model) {
		Optional<EmployeeDto> employeeDto = employeeService.findByEmail(email);
		String output = null;
		try {
			if (employeeDto != null) {
				session.setAttribute("userData", employeeDto);
				output = "getNewPasswords";
			} else {
				model.addAttribute("message", "Sorry, Email not found. Try again");
				output = "validateEmail";
			}
		} catch (Exception e) {
		}
		return output;
	}

	@GetMapping("/getNewPasswords")
	public String getNewPasswords() {
		return "getNewPasswords";
	}

	/*
	 * @PostMapping("/updatePasswords") public String
	 * getNewPasswordsPost(@RequestParam String password1, @RequestParam String
	 * password2,
	 * 
	 * @RequestParam String email, HttpSession session, Model model) { if
	 * (password1.equals(password2)) { employeeService = new EmployeeServiceImpl();
	 * String result = employeeService.updatePassword(email, password1);
	 * 
	 * if (result.equals("Update Successful")) { model.addAttribute("message",
	 * "Password changed successfully."); return "index"; } else {
	 * model.addAttribute("message",
	 * "Server Connection problem. Please Try Again."); return "getNewPasswords"; }
	 * } else { model.addAttribute("message", "Passwords do not match."); return
	 * "getNewPasswords"; } }
	 */

}
