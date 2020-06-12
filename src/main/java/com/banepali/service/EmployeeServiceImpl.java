package com.banepali.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banepali.dao.EmployeeDaoRepository;
import com.banepali.dao.EmployeeEntity;
import com.banepali.dto.EmployeeDto;
import com.banepali.utils.Copy;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDaoRepository employeeDaoRepository;

	@Override
	public void persist(EmployeeDto employeeDto) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDto, employeeEntity);
		employeeDaoRepository.save(employeeEntity);
	}

	@Override
	public void updateEmployee(EmployeeDto employeeDto) {
		EmployeeDto dbemployeeDto = findById(employeeDto.getEid());
		Copy.copyNonNullProperties(employeeDto, dbemployeeDto);
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(dbemployeeDto, employeeEntity);
		employeeDaoRepository.save(employeeEntity);
	}

	@Override
	public EmployeeDto authUser(String username, String password) {
		// employeeEntity = Optional.empty();
		// if (emailOrUsername.contains("@")){
		Optional<EmployeeEntity> employeeEntity = employeeDaoRepository.findByEmailAndPassword(username, password);
		// }else {
		// employeeEntity =
		// employeeDaoRepository.findByUsernameAndPassword(emailOrUsername, password);
		// }
		EmployeeDto employeeDto = null;
		if (employeeEntity != null) {
			employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employeeEntity.get(), employeeDto); // .get()--> method is for getting the values
																			// from the optional
		}
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> findAll() {
		List<EmployeeEntity> employeeEntities = employeeDaoRepository.findAll();
		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
		for (EmployeeEntity employee : employeeEntities) {
			EmployeeDto employeeDto = new EmployeeDto();
			BeanUtils.copyProperties(employee, employeeDto);
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto findById(int eid) {
		EmployeeEntity employeeEntity = employeeDaoRepository.findById(eid).get();
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employeeEntity, employeeDto);
		return employeeDto;
	}

	@Override
	public int findTotalEmployee() {
		return (int) employeeDaoRepository.count(); // this return long actually
	}

	@Override
	public byte[] findImageById(int eid) {
		return employeeDaoRepository.findById(eid).get().getBphoto();
	}

	/*
	 * @Override public List<String> findAllUsername() { List<String> usernames =
	 * new ArrayList<String>(); usernames = employeeDaoRepository.findAllUsername();
	 * return usernames; }
	 */

	@Override
	public Optional<EmployeeDto> findByEmail(String email) {
		EmployeeEntity employeeEntity = employeeDaoRepository.findByEmail(email).get();
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employeeEntity, employeeDto);
		return Optional.ofNullable(employeeDto);
	}

	@Override
	public void deleteById(int eid) {
		employeeDaoRepository.deleteById(eid);
	}

	@Override
	public List<EmployeeDto> getSignups(int pageid, int total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EmployeeDto> optionalEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	

}