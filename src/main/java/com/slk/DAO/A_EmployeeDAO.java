package com.slk.DAO;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.A_Employee;

@Repository
public interface A_EmployeeDAO {
	

	
		A_Employee registerAdmin(A_Employee a_Employee);
		//Employee registerSuperuser(Employee employee);
		A_Employee updateAdmin(Long empId,A_Employee emp);
		Long deleteEmployee(Long empId);
		
		List<A_Employee> getAllEmployee();
		List<A_Employee> getAllSuperuser();

	}


