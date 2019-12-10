package com.slk.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RestController;
import com.slk.DAO.B_AgentDAO;
import com.slk.DAO.B_CustomerDAO;
import com.slk.model.B_Agent;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class B_CustomerRestController {
	@Autowired
	private B_CustomerDAO customerDAO;
	// private Agent agent;
	
	@GetMapping("/B_Customer")
	public List getAllCustomers() throws Exception {
		System.out.println("function inside customer rest");
		return customerDAO.getAllCustomers();
	}

}
