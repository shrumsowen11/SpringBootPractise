package com.banepali.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banepali.dto.EmployeeDto;
import com.banepali.service.EmployeeService;

@Controller
public class RegisterController {
	
	@Autowired
	private EmployeeService employeeService;

	
	@GetMapping("/register")
	public String registerUser() {
		return "register";
	}

	@PostMapping("/rregister")
	public String registerUserPost(@ModelAttribute EmployeeDto employeeDto, Model model) throws IOException {

		// MultipartFile into byte[]
		byte[] bPhoto = employeeDto.getPhoto().getBytes();
		employeeDto.setBphoto(bPhoto);

		/*
		 * String check = "good"; List<String> previousUsernames =
		 * employeeService.findAllUsername();
		 * System.out.println("From IndexController \n\n\n" + previousUsernames); if
		 * (previousUsernames.contains(employeeDto.getUsername())) {
		 * 
		 * System.out.println(employeeDto.getUsername()); check = "Sorry,      \"" +
		 * employeeDto.getUsername() + "\"     is already taken."; } if (check !=
		 * "good") { model.addAttribute("message", check); return "register"; } else {
		 * employeeService.persist(employeeDto); model.addAttribute("message",
		 * "You have succcessfully registered.."); return "index"; }
		 */
		employeeDto.setRole("Customer");
		employeeDto.setDatecreated(new Timestamp(new Date().getTime()));
		employeeService.persist(employeeDto);
		model.addAttribute("message", "You have succcessfully registered..");
		return "index";
	}
	
	/*
	 * @PostMapping("/updatePhoto") public String updatePhotoPost(@RequestParam int
	 * eId, Model model) throws IOException { String hqlUpdate =
	 * "update Customer c set c.name = :newName where c.name = :oldName"; // or
	 * String hqlUpdate =
	 * "update Customer set name = :newName where name = :oldName"; int
	 * updatedEntities = s.createQuery( hqlUpdate ) .setString( "newName", newName )
	 * .setString( "oldName", oldName ) .executeUpdate();
	 * employeeService.updatePhoto(bPhoto); return "showAllData"; }
	 */
	
	
	// <img src="image?sid =1"
	@GetMapping("/image")
	public void showImage(@RequestParam int eid, HttpServletResponse httpServletResponse) throws IOException {
		byte[] photo = employeeService.findImageById(eid);
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		if (photo != null) {
			httpServletResponse.setContentType("image/jpg");
			outputStream.write(photo);
		} else {
			outputStream.write(new byte[] {});
		}
		outputStream.flush();
	}

	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(true);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    }
	
	 
	@GetMapping("/showAllContactInfo")
	public String contactUsPost(Model model) {
		List<EmployeeDto> employeeList =  employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "showAllContacts";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "logout";
	}

	@GetMapping("/showProfile")
	public String showProfile(@RequestParam int eid, HttpSession session, Model model) {

		EmployeeDto employeeDto = employeeService.findById(eid);
		model.addAttribute("entity", employeeDto);
		return "showProfile";
	}

	
	@GetMapping("/showAllEmployees")
	public String showAllEmployees(Model model) {
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		model.addAttribute("employeeList", employeeDtos);
		return "showAllData";

	}

	
	
	@GetMapping("/showAllEmployeesBlockTime")
	public String showAllEmployeesBlockTime(Model model) {
		List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		employeeList = employeeService.findAll();
		model.addAttribute("employeeList", employeeList);
		return "showAllDataBlockTime";

	}

	@GetMapping("/deletePerson")
	public String deleteData(@RequestParam int eid) {
		employeeService.deleteById(eid);
		return "redirect:/showAllEmployees";
	}

	@GetMapping("/editPerson")
	public String editPersonData(@RequestParam int eid, Model model) {
		EmployeeDto employeeDto = employeeService.findById(eid);
		model.addAttribute("message", "Please edit the fields you like to update.");
		model.addAttribute("employeeEntity", employeeDto);
		return "editEmploeeData";
	}

	@PostMapping("/updateEmployeeData")
	public String updateData(@ModelAttribute EmployeeDto employeeDto, Model model) {
		employeeService.updateEmployee(employeeDto);
		model.addAttribute("message", "You have successfully updated.");
		return "redirect:/showAllEmployees";

	}

}
