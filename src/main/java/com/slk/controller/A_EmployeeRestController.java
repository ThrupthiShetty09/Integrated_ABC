package com.slk.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.DAO.A_EmployeeDAO;
import com.slk.DAO.A_EmployeeDAOImpl;
import com.slk.model.A_Employee;


@Repository
@RestController
//@RequestMapping("/customerService")
@CrossOrigin(origins="http://localhost:4200")
public class A_EmployeeRestController {



	@Autowired
	private A_EmployeeDAOImpl a_EmployeeDAOImpl;

	
	@GetMapping("/a_employee")
		public List getEmployee() {

		return a_EmployeeDAOImpl.getAllEmployee();

	}
	@GetMapping("/a_superuser")
	public List getSuperuser() {

	return a_EmployeeDAOImpl.getAllSuperuser();

}
	@DeleteMapping("/delete/a_employee/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {
		
		if (null == a_EmployeeDAOImpl.deleteEmployee(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/post/a_employee")
	public ResponseEntity createCustomer(@RequestBody A_Employee a_Employee) {

		System.out.println(a_Employee);
		a_EmployeeDAOImpl.registerAdmin(a_Employee);

		return new ResponseEntity(a_Employee, HttpStatus.OK);
	}
	
	@PutMapping("/put/a_employee/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody A_Employee a_Employee) {
		System.out.println(id);
		System.out.println(a_Employee);
		a_Employee = a_EmployeeDAOImpl.updateAdmin(id, a_Employee);

		if (null == a_Employee) {
			return new ResponseEntity("No Customer found for ID " + id,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(a_Employee, HttpStatus.OK);
	}
}