package com.fissionlab.coe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fissionlab.coe.SpringBootBaseProjectApplication;
import com.fissionlab.coe.entity.Employee;
import com.fissionlab.coe.exception.ResourceNotFoundException;
import com.fissionlab.coe.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	 @Autowired
	private EmployeeRepository employeeRepository;
	
	 public List<Employee> getAllEmployees() {
		 LOGGER.info("Started with get employees");

	        return employeeRepository.findAll();
	    }
	 
	 public Employee getEmployeeById(Long employeeId)
		        throws ResourceNotFoundException {
		 LOGGER.info("Started with get employees by id");
		 Employee employee = employeeRepository.findById(employeeId)
		    		  .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		      return employee;
		 
		    }

	 public Employee createEmployee( Employee employee) {
	        return employeeRepository.save(employee);
	    }

	
	    public Employee updateEmployee(Long employeeId,
	         Employee employeeDetails) throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employee.setEmailId(employeeDetails.getEmailId());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setFirstName(employeeDetails.getFirstName());
	        final Employee updatedEmployee = employeeRepository.save(employee);
	        return updatedEmployee;
	    }

	    
	    public Map<String, Boolean> deleteEmployee(Long employeeId)
	         throws ResourceNotFoundException {
	        Employee employee = employeeRepository.findById(employeeId)
	       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	        employeeRepository.delete(employee);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}
