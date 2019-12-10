package com.slk.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;

@Repository
public interface D_CustomerInterface {
	D_Customer viewProfile(int id);
	List<D_Customer> viewAllCustomer();
	int transfer(int id,double amount,int account_number);
	List<D_Transaction> transactionhistory(int id);
	D_Customer viewBalance(int id);
	D_Loan viewLoan(int id);
	public void updateprofile(int id,D_Customer cust);
	public int loginValidation(String username,String password);
}
