package com.banepali.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banepali.dto.ApplicationResponse;
import com.banepali.dto.EmployeeDto;
import com.banepali.service.EmployeeService;

@RestController
@RequestMapping ("/v1")
public class JSONController {
	@Autowired
	private EmployeeService employeeService;
	
	
	// since when the PROFILE button is clicked, this is called, 
	// but we make it different by adding the @RequestMapping in the top
	
	
	// Accept -application/json
	// Accept -application/xml
	
	
	
	@PutMapping("/showAllEmployees")
	public ApplicationResponse updateData(@RequestBody EmployeeDto employeeDto) {
		employeeService.updateEmployee(employeeDto);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		applicationResponse.setCode(122);
		applicationResponse.setStatus("success");
		applicationResponse.setMessage("data is updated");
		return applicationResponse;
	}
	
	@PostMapping("/showAllEmployees")
	public ApplicationResponse createEmployees(@RequestBody EmployeeDto employeeDto) {
		employeeDto.setRole("Customer");
		employeeDto.setDatecreated(new Timestamp(new Date().getTime()));
		employeeService.persist(employeeDto);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		applicationResponse.setCode(122);
		applicationResponse.setStatus("success");
		applicationResponse.setMessage("data is created");
		return applicationResponse;
	}
	
	
	@GetMapping("/showAllEmployees")
	public List<EmployeeDto> showAllEmployees() {
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		return employeeDtos;
	}
	
	@GetMapping("/showProfile/{eid}") 
	// @PathVariable makes the eid as a token for displaying the data
	public EmployeeDto showProfile(@PathVariable int eid ) {

		EmployeeDto employeeDto = employeeService.findById(eid);
		return employeeDto;
	}
	
	@DeleteMapping("/showProfile/{eid}")
	public ApplicationResponse deleteData(@PathVariable int eid) {
		employeeService.deleteById(eid);
		ApplicationResponse applicationResponse = new ApplicationResponse();
		applicationResponse.setCode(122);
		applicationResponse.setStatus("success");
		applicationResponse.setMessage("data is deleted");
		return applicationResponse;
		
	}
	

}
