package com.slk.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.slk.model.D_Customer;
import com.slk.model.D_Loan;
import com.slk.model.D_Transaction;
import com.slk.util.DButil;

@Repository
public class D_CustomerImpl implements D_CustomerInterface {
	static List<D_Customer> list1 = new ArrayList();
	static List<D_Transaction> listTrans = new ArrayList();
	Connection conn = null;
	{
		try {
			conn =DButil.getConnection();
			System.out.println("test ");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from D_CUSTOMER");
			while (rs.next()) {
				System.out.println("name ="+rs.getString("name"));
				D_Customer cust = new D_Customer();
				cust.setAccount_no(rs.getInt("account_no"));
				cust.setName(rs.getString("name"));
				cust.setDob(rs.getString("DOB"));
				cust.setPhone_no(rs.getInt("phone_no"));
				cust.setUsername(rs.getString("username"));
				cust.setPassword(rs.getString("password"));
				cust.setAmount(rs.getDouble("amount"));
				cust.setBranch_id(rs.getInt("branch_id"));
				cust.setLoan_id(rs.getString("loan_id"));
				cust.setType_id(rs.getString("type_id"));
				list1.add(cust);
				System.out.println(list1);
		} 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	

	@Override
	public D_Customer viewProfile(int id) {
		// TODO Auto-generated method stub
		for (D_Customer c : list1) {
			if (c.getAccount_no() == id) {

				return c;
			}
		}
		return null;

	}

	@Override
	public int loginValidation(String username, String password) {
		int flag = 0;
		for (D_Customer c : list1) {

			if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
				flag = 1;

				return flag;
			}
		}
		return flag;
	}

	@Override
	public int transfer(int id, double amount, int account_number) {
		// TODO Auto-generated method stub
		int sendAccNo = id;
		int receiveAccNo = account_number;
		double sendTempAmt = 0;
		double receiveTempAmt = 0;
		double tempAmt = 0;
		String accType = "";
		double debit = 0;
		double credit = 0;
		int transId = 0;
		int flag = 0;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		D_CustomerInterface cust = new D_CustomerImpl();
		try {
			Connection conn =DButil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select amount,type_id from D_CUSTOMER where account_no=" + sendAccNo);
			while (rs.next()) {
				sendTempAmt = rs.getDouble("amount");
				accType = rs.getString("type_id");
			}
			rs = stmt.executeQuery("select amount from D_CUSTOMER where account_no=" + receiveAccNo);
			while (rs.next()) {
				receiveTempAmt = rs.getDouble("amount");
			}
			rs = stmt.executeQuery("select max(id) as id from D_TRANSACTION");
			while (rs.next()) {
				transId = rs.getInt("id");
			}
			transId++;
			char c = accType.charAt(0);
			if (c == 'C') {
				tempAmt = sendTempAmt - amount;
				if (tempAmt >= 5000) {
					debit = amount;

					stmt.executeUpdate("update D_CUSTOMER set amount=" + tempAmt + "where account_no=" + sendAccNo);
					stmt.executeUpdate("insert into D_TRANSACTION values(" + transId + ",'" + formatter.format(date)
							+ "'," + credit + "," + debit + "," + sendAccNo + "," + tempAmt + ")");
					System.out.println("Sender balance= " + tempAmt);
					debit = 0;
					credit = amount;

					tempAmt = receiveTempAmt + amount;
					transId++;
					stmt.executeUpdate("update D_CUSTOMER set amount=" + tempAmt + "where account_no=" + receiveAccNo);
					stmt.executeUpdate("insert into D_TRANSACTION values(" + transId + ",'" + formatter.format(date)
							+ "'," + credit + "," + debit + "," + receiveAccNo + "," + tempAmt + ")");
					System.out.println("receiver balance= " + tempAmt);
					flag = 1;
				} else {
					System.out.println("No Minimum Balance");
				}
			} else {
				tempAmt = sendTempAmt - amount;
				if (tempAmt >= 500) {
					debit = amount;

					stmt.executeUpdate("update D_CUSTOMER set amount=" + tempAmt + "where account_no=" + sendAccNo);
					stmt.executeUpdate("insert into D_TRANSACTION values(" + transId + ",'" + formatter.format(date)
							+ "'," + credit + "," + debit + "," + sendAccNo + "," + tempAmt + ")");
					System.out.println("Sender balance= " + tempAmt);
					debit = 0;
					credit = amount;

					tempAmt = receiveTempAmt + amount;
					transId++;
					stmt.executeUpdate("update D_CUSTOMER set amount=" + tempAmt + "where account_no=" + receiveAccNo);
					stmt.executeUpdate("insert into D_TRANSACTION values(" + transId + ",'" + formatter.format(date)
							+ "'," + credit + "," + debit + "," + receiveAccNo + "," + tempAmt + ")");
					System.out.println("receiver balance= " + tempAmt);
					flag = 1;
				} else {
					System.out.println("No Minimum Balance");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return flag;
	}

	@Override
	public List<D_Transaction> transactionhistory(int id) {
		// TODO Auto-generated method stub
		listTrans.clear();

		try {
			Connection conn = DButil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from D_TRANSACTION where account_no=" + id);
			while (rs.next()) {
				D_Transaction tl = new D_Transaction();
				tl.setId(rs.getInt("id"));
				tl.setTrans_date(rs.getString("trans_date"));
				tl.setCredit(rs.getDouble("credit"));
				tl.setDebit(rs.getDouble("debit"));
				tl.setAccount_no(rs.getInt("account_no"));
				tl.setAmount(rs.getDouble("balance"));
				System.out.println(rs.getDouble("balance"));
				listTrans.add(tl);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return listTrans;
	}

	@Override
	public D_Customer viewBalance(int id) {
		// TODO Auto-generated method stub
		for (D_Customer c : list1) {
			System.out.println(c.getAmount());
			if (c.getAccount_no() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public D_Loan viewLoan(int id) {
		// TODO Auto-generated method stub
		// List<Loan> listLoan=new ArrayList();
		D_Loan ln = new D_Loan();
		for (D_Customer c : list1) {
			System.out.println(c.getAccount_no());
			if (c.getAccount_no() == id) {

				String ltype = c.getLoan_id();
				char tempChar = ltype.charAt(0);
				if (tempChar == 'C') {
					System.out.println("car loan");
					try {
						Connection conn = DButil.getConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(
								"select D_LOAN.id as id,D_LOAN.loan_type as loan_type ,D_LOAN.interest_rate as interest_rate from D_CUSTOMER,D_LOAN where account_no="
										+ id + " and id='" + ltype + "'");

						while (rs.next()) {
							ln.setId(rs.getString("id"));
							ln.setLoan_type(rs.getString("loan_type"));
							ln.setInterest_rate(rs.getDouble("interest_rate"));
							System.out.println(ln.getId());
						}
						rs.close();
						stmt.close();

					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (tempChar == 'H') {
					System.out.println("Home loan");

					try {
						Connection conn = DButil.getConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(
								"select D_LOAN.id as id,D_LOAN.loan_type as loan_type ,D_LOAN.interest_rate as interest_rate from D_CUSTOMER,D_LOAN where account_no="
										+ id + " and id='" + ltype + "'");

						while (rs.next()) {
							ln.setId(rs.getString("id"));
							ln.setLoan_type(rs.getString("loan_type"));
							ln.setInterest_rate(rs.getDouble("interest_rate"));
							System.out.println(ln.getId());
						}
						rs.close();
						stmt.close();

					} catch (Exception e) {
						System.out.println(e);
					}

				} else if (tempChar == 'S') {
					System.out.println("Student loan");

					try {
						Connection conn = DButil.getConnection();
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(
								"select D_LOAN.id as id,D_LOAN.loan_type as loan_type ,D_LOAN.interest_rate as interest_rate from D_CUSTOMER,D_LOAN where account_no="
										+ id + " and id='" + ltype + "'");

						while (rs.next()) {
							ln.setId(rs.getString("id"));
							ln.setLoan_type(rs.getString("loan_type"));
							ln.setInterest_rate(rs.getDouble("interest_rate"));
							System.out.println(ln.getId());
						}
						rs.close();
						stmt.close();

					} catch (Exception e) {
						System.out.println(e);
					}

				} else {
					System.out.println("No loan");
				}
				return ln;
			}
		}
		return null;
	}

	@Override
	public List<D_Customer> viewAllCustomer() {
		// TODO Auto-generated method stub
		return list1;
	}

	public void updateprofile(int id, D_Customer cust) {
		for (D_Customer c : list1) {
			if (c.getAccount_no() == id) {
				
				
				
				System.out.println("update inside");
				try {
					Connection conn = DButil.getConnection();
					Statement stmt = conn.createStatement();
					stmt.executeUpdate("update D_CUSTOMER set name='" + cust.getName() + "',DOB='" + cust.getDob()
							+ "',phone_no=" + cust.getPhone_no() + ",username='" + cust.getUsername() + "',password='"
							+ cust.getPassword() + "' where account_no=" + id + "");
					System.out.println("successfull updated......!");
					
					int index=list1.indexOf(c);
					
					list1.set(index, cust);
					
					
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
		
		System.out.println(list1);
	

	}

}
