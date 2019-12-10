package com.slk.DAO;

import com.slk.model.B_Admin;
//import com.slk.model.Agent;

public interface B_AdminDAO {
	public boolean login(String username, String password) throws Exception;
	public B_Admin updateAdmin(int empId, B_Admin ad);

}
