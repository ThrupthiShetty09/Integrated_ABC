package com.slk.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.DAO.c_EmployeeDAOImpl;
import com.slk.model.c_Customer;
import com.slk.model.c_Employee;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public class c_RestEmployee {
	@Autowired
	c_EmployeeDAOImpl employeeDaoimpl= new c_EmployeeDAOImpl();
	
	@GetMapping("/c_agent1")
	public List getAgentLogin()
	{
		return employeeDaoimpl.listLogin();
	}
	@GetMapping("/c_agent/{id}")
	public List getAgent(@PathVariable String id)
	{
		return employeeDaoimpl.list(id);
	}
	@GetMapping("/c_customer/{id}")
	public List getCustomer(@PathVariable String id) throws SQLException
	{
		System.out.println(id);
		return employeeDaoimpl.getAllCustomer(id);
	}
	@GetMapping("/c_transaction/{id}")
	public List getTransaction(@PathVariable String id) throws SQLException
	{
		return employeeDaoimpl.getAllTransaction(id);
	}
	
	@DeleteMapping("/delete/c_agent/{id}")
	public ResponseEntity deleteCustomer(@PathVariable String id) {
	
		if (null == employeeDaoimpl.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}
	@PutMapping("/put/c_customer/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody c_Customer c) {
      System.out.println("ggasgcs");
		c =employeeDaoimpl.update(id, c);

		if (null == c) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(c, HttpStatus.OK);
	}
	@PutMapping("/put/c_agent/{id}")
	 // System.out.println("ggasgcs");
	public ResponseEntity updateAgent(@PathVariable Long id, @RequestBody c_Employee e) {

  
  	System.out.println("agent "+e);
		e =employeeDaoimpl.updateAgent(id, e);
		
	

		if (null == e) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(e, HttpStatus.OK);
	}
	

	/*@PutMapping("/put/agent")
	public ResponseEntity updateAgent1(@RequestBody Employee g) {
      System.out.println("ggasgcs");
		g =employeeDaoimpl.updateAgent1(g);

		if (null ==g) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(g, HttpStatus.OK);
	}*/

}
	


