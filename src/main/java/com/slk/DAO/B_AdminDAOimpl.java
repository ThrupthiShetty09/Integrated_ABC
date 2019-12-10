package com.slk.DAO;

import com.slk.model.B_Admin;
import com.slk.util.DButil;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class B_AdminDAOimpl implements B_AdminDAO {

	Connection connection = DButil.getConnection();

	public B_AdminDAOimpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}

	// @Override
	public boolean login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		B_Admin ad = new B_Admin();
		boolean flag = false;
		PreparedStatement stmt = connection
				.prepareStatement("select username,password from b_admin where username=?  and password=? ");
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			// Admin ad = new Admin();
			// ad.setEmpId(rs.getInt(1));
			// ad.setName(rs.getString(2));
			// ad.setDOB(rs.getString(3));
			// ad.setPhone_num(rs.getInt(4));
			ad.setUsername(rs.getString(1));
			ad.setPassword(rs.getString(2));

			// ad.setRole(rs.getString(7));
			flag = true;

		}
		return flag;

	}

	@Override
	public B_Admin updateAdmin(int empId, B_Admin ad) {
		// TODO Auto-generated method stub
		System.out.println("ID to be updated " + empId);
		try {
			// Admin ad = new Admin();
			String sql = ("UPDATE b_admin SET name=?,DOB=?,phone_num=?,username=?,password=?,role=? where empId=?");

			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, ad.getName());
			pst.setString(2, ad.getDOB());
			pst.setLong(3, ad.getPhone_num());
			pst.setString(4, ad.getUsername());
			pst.setString(5, ad.getPassword());
			pst.setString(6, ad.getRole());
			pst.setInt(7, empId);
			int res = pst.executeUpdate();
			System.out.println(res);

			if (res > 0) {
				System.out.println("admin Updated");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;

	}

}
