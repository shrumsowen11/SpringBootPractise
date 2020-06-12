package com.banepali.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//this is for the DAO layer and if we need more, we can write the declaration
public interface EmployeeDaoRepository extends JpaRepository<EmployeeEntity, Integer> {
	
	//this below method is an additional programmer needed method for verifying the username and pw
	public Optional<EmployeeEntity> findByUsernameAndPassword(String username,String password);
	public Optional<EmployeeEntity> findByEmailAndPassword(String email,String password);

	public Optional<EmployeeEntity> findByEmail(String email);
	//public List<String> findAllUsername(); //do I have to make a custom made class or interface
}
