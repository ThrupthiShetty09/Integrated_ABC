package com.slk.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.slk.DAO.D_CustomerImpl;
import com.slk.DAO.D_CustomerInterface;
import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;
@CrossOrigin(origins="http://localhost:4200")
@RestController
//@RequestMapping("/customerService")

public class D_CustomerRestController {
	
	@Autowired
	private D_CustomerImpl customerImpl;
//	D_CustomerInterface ci=new D_CustomerImpl();

	@GetMapping("/Customer")
		public List getCustomers() {
		
		return customerImpl.viewAllCustomer();
	}

	@GetMapping("/Customer/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") int id) {
		
		D_Customer customer=customerImpl.viewProfile(id);
			
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
		System.out.println("new name="+customer.getName());
		return new ResponseEntity(customer, HttpStatus.OK);
	}
	//********************  Login   ****************************
	@GetMapping("/Customer/{username}/{password}")
	public ResponseEntity getCustomerLogin(@PathVariable("username") String username,@PathVariable("password") String password) {
		
		int flag=customerImpl.loginValidation(username,password);
			
		if (flag == 0) {
			return new ResponseEntity("No Customer found for ID " + username, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(flag, HttpStatus.OK);
	}
	//
	
	@GetMapping("/Customer/Balance/{id}")
	public ResponseEntity getCustomerBalance(@PathVariable("id") int id) {
		
		D_Customer customer=customerImpl.viewBalance(id);;
			
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
	
	
	@GetMapping("/Customer/Loan/{id}")
	public ResponseEntity getCustomerLoan(@PathVariable("id") int id) {
		
		D_Loan loan=customerImpl.viewLoan(id);
			
		if (loan == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(loan, HttpStatus.OK);
	}
	
	
	@GetMapping("/Customer/Transaction/{id}")
	public ResponseEntity getCustomerTransaction(@PathVariable("id") int id) {
		
		List<D_Transaction> t=customerImpl.transactionhistory(id);;
			
		if (t == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(t, HttpStatus.OK);
	}
	
	
	@GetMapping("/Customer/Transfer/{id}/{amount}/{accNo}")
	public ResponseEntity getCustomerTransfer(@PathVariable("id") int id,@PathVariable("amount") double amount,@PathVariable("accNo") int accNo) {
		//double amount=500.00;
		//int accNo=123456;
		int flag=customerImpl.transfer(id,amount,accNo);
			
		if (flag == 0) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(flag, HttpStatus.OK);
	}
	
	@PutMapping(value = "/put/Customer/{id}")
	public ResponseEntity createCustomer(@PathVariable("id") int id,@RequestBody D_Customer cust ) {

		customerImpl.updateprofile(id, cust);
		return new ResponseEntity(cust, HttpStatus.OK);
	}
	    


	
}
