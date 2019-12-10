package com.slk.DAO;

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

import com.slk.model.B_Agent;
import com.slk.util.*;

@RestController
public class B_AgentDAOimpl implements B_AgentDAO {

	// Connection connection = null;
	Connection connection = DButil.getConnection();

	public B_AgentDAOimpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}

	// @Override
	public List<B_Agent> list() throws Exception {
		// TODO Auto-generated method stub
		List<B_Agent> agt = new ArrayList();

		// PreparedStatement ps = null;
		// ResultSet rs1 = null;

		try {
			System.out.println("function inside list");

			PreparedStatement stmt = connection.prepareStatement("select * from b_agent");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				B_Agent a = new B_Agent();
				a.setAgent_id(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setDOB(rs.getString(3));
				a.setPhone_no(rs.getInt(4));
				a.setUsername(rs.getString(5));
				a.setPassword(rs.getString(6));
				a.setBranch_id(rs.getInt(7));

				System.out.println(rs.getInt(1) + " " + rs.getString(2));

				agt.add(a);
				System.out.println("testing Tomcat");
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return agt;

	}

	// @Override
	public void addAgent(B_Agent agent) {
		// TODO Auto-generated method stub
		try {
			
			java.sql.PreparedStatement pst = connection.prepareStatement("Insert into b_agent values(?,?,?,?,?,?,?)");
			System.out.println("function incoming");
			pst.setInt(1, agent.getAgent_id());
			pst.setString(2, agent.getName());
			pst.setString(3, agent.getDOB());
			pst.setInt(4, agent.getPhone_no());
			pst.setString(5, agent.getUsername());
			pst.setString(6, agent.getPassword());
			pst.setInt(7, agent.getBranch_id());

			int i = pst.executeUpdate();
			System.out.println(i + "Records Inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// //@Override
	public void updateAgent(B_Agent agent) {
		// TODO Auto-generated method stub

		try {

			String sql = ("Update b_agent set name=?,DOB=?,phone_no=?,username=?,password=?,branch_id=? where agent_id=?");

			PreparedStatement pst = connection.prepareStatement(sql);

			// pst.setInt(1, customer.getCustID());
			// pst.setInt(1, agent.getAgent_id());

			pst.setString(1, agent.getName());
			pst.setString(2, agent.getDOB());
			pst.setLong(3, agent.getPhone_no());
			pst.setString(4, agent.getUsername());
			pst.setString(5, agent.getPassword());
			pst.setLong(6, agent.getBranch_id());
			pst.setLong(7, agent.getAgent_id());
			int res = pst.executeUpdate();

			if (res > 0) {
				System.out.println("Agent Updated");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// @Override
	public B_Agent getAgentById(int id) {
		// TODO Auto-generated method stub

		B_Agent agent = null;
		System.out.println("function called");

		try {

			String sql = "Select * from b_agent where agent_id=?";

			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				agent = new B_Agent();

				agent.setAgent_id(rs.getInt(1));

				agent.setName(rs.getString(2));

				agent.setDOB(rs.getString(3));

				agent.setPhone_no(rs.getInt(4));

				agent.setUsername(rs.getString(5));

				agent.setPassword(rs.getString(6));

				agent.setBranch_id(rs.getInt(7));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agent;
	}

	// @Override
	public int deleteAgent(int agent_id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("function coming in to delete");
		System.out.println(agent_id);
		B_Agent agent = new B_Agent();
		String sql = "DELETE FROM b_agent WHERE agent_id =?";
		PreparedStatement pst = connection.prepareStatement(sql);
		// pst.setInt(1, agent.getAgent_id());
		pst.setInt(1, agent_id);
		int res = pst.executeUpdate();
		System.out.println(res);

		if (res > 0) {
			System.out.println("Agent  Deleted");
		}

		return agent_id;
	}
}
