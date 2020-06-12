package com.banepali.service;

import java.util.List;
import java.util.Optional;

import com.banepali.dto.EmployeeDto;


public interface EmployeeService {
	
	
	void persist(EmployeeDto employeeDto);
	void updateEmployee(EmployeeDto employeeDto);
	
	EmployeeDto authUser(String emailOrUsername, String password);
	List<EmployeeDto> findAll();
	EmployeeDto findById(int eid);
	int findTotalEmployee();
	byte[] findImageById(int eid);
	List<String> findAllUsername();
	Optional<EmployeeDto> findByEmail(String email);
	
	Optional<EmployeeDto> optionalEmployeeByEmail(String email);

	void deleteById(int eid);
	
	List<EmployeeDto> getSignups(int pageid, int total);
	

}
