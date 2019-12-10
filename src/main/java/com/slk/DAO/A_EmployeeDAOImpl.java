package com.slk.DAO;

import java.sql.*;


import java.util.*;

import org.springframework.stereotype.Repository;



import com.slk.model.A_Employee;
import com.slk.util.DButil;
@Repository
public class A_EmployeeDAOImpl implements A_EmployeeDAO {
	Connection connection = null;
	private static List<A_Employee> admins;
	 private static List<A_Employee> superuser;
	
	
	 {
		connection = DButil.getConnection();
		System.out.println(connection);
	 }
	 
	 
		
	
	
		
	 public List<A_Employee> getAllSuperuser()
	 {
		connection = DButil.getConnection();
		System.out.println(connection);
		String query = "Select * from a_employee where emp_role = 'superUser' ";
	
		superuser = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				A_Employee emp = new A_Employee();
				int empId = rs.getInt(1);
				String empName = rs.getString(2);
				String empDob = rs.getString(3);
				int empContact = rs.getInt(4);
				String uname = rs.getString(5);
				String pwd = rs.getString(6);
				String erole = rs.getString(7);
				System.out.println(empId + " " + empName + " " + empDob + " " + empContact);
				emp.setEmp_no(empId);
				emp.setName(empName);
				emp.setDob(empDob);
				emp.setPhno(empContact);
				emp.setUsername(uname);
				emp.setPassword(pwd);
				emp.setEmp_role(erole);
				
				//admins.add(new Employee(empId,empName,empDob,empContact));
				superuser.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return superuser;
		
		
	}
	
	 @Override
	public List<A_Employee> getAllEmployee(){
		String query = "Select * from a_employee where emp_role = 'admin' ";
		//Employee emp;
		admins = new ArrayList<>();
		PreparedStatement st;
		try {
			
			st = connection.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				A_Employee emp = new A_Employee();
				int empId = rs.getInt(1);
				String empName = rs.getString(2);
				String empDob = rs.getString(3);
				int empContact = rs.getInt(4);
				String uname = rs.getString(5);
				String pwd = rs.getString(6);
				String erole = rs.getString(7);
				System.out.println(empId + " " + empName + " " + empDob + " " + empContact);
				emp.setEmp_no(empId);
				emp.setName(empName);
				emp.setDob(empDob);
				emp.setPhno(empContact);
				emp.setUsername(uname);
				emp.setPassword(pwd);
				emp.setEmp_role(erole);
				//admins.add(new Employee(empId,empName,empDob,empContact));
				admins.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 return admins;
	}





@Override
public Long deleteEmployee(Long empId) {
	// TODO Auto-generated method stub
	try{
		
		String sql="DELETE FROM a_employee WHERE empId = ? ";			
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setLong(1, empId);
		
		int res  = pst.executeUpdate();
		
		if(res > 0)
		{
			System.out.println("Admin Deleted");
		}
		
		return empId;
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	return null;
}


public A_Employee registerAdmin(A_Employee a_Employee) {
	// TODO Auto-generated method stub
	String role = "admin";
	String select_query = "Select max(empId) from a_employee";
	Statement select_stmt;
	int i=0;
	try {
		select_stmt = connection.createStatement();
		ResultSet select_rs = select_stmt.executeQuery(select_query);

		select_rs.next();
		int emp_Id = select_rs.getInt(1);
		int temp_empId = ++emp_Id;
		
		PreparedStatement pst = connection.prepareStatement("Insert into a_employee values(?,?,?,?,?,?,?)");
		
		pst.setInt(1,temp_empId);
		pst.setString(2,a_Employee.getName());
		pst.setString(3,a_Employee.getDob());
		pst.setLong(4,a_Employee.getPhno());
		pst.setString(5,a_Employee.getUsername());
		pst.setString(6,a_Employee.getPassword());
		pst.setString(7,role);
		i = pst.executeUpdate();
		System.out.println(i + " records inserted");
		//admins.add(employee);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return a_Employee;
}


@Override
public A_Employee updateAdmin(Long empId,A_Employee emp)
{
	try {
		
		
		String updSql = "UPDATE  a_employee set name = ?,DOB = ?,phone_no = ?,username = ?,password = ?,emp_role = ? WHERE empId = ?";
		PreparedStatement pst = connection.prepareStatement(updSql);
		System.out.println(emp.getName());
		pst.setString(1, emp.getName());
		pst.setString(2, emp.getDob());
		pst.setLong(3, emp.getPhno());
		pst.setString(4, emp.getUsername());
		pst.setString(5, emp.getPassword());
		pst.setString(6, emp.getEmp_role());
		pst.setLong(7, empId);
		int res  = pst.executeUpdate();
		
		if(res > 0)
		{
			System.out.println("Admin updated");
		}
		return emp;
		
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}




	
	
	
	

}


