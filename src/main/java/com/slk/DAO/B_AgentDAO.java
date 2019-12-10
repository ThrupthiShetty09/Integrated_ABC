package com.slk.DAO;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.slk.model.*;
@RestController
public interface B_AgentDAO {
     void addAgent(B_Agent agent);
	
    public void updateAgent(B_Agent agent);
	
	//public void   deleteAgent(Agent agent) ;
	int deleteAgent(int id) throws SQLException;
	
	public List<B_Agent> list() throws Exception;
	
	
	B_Agent getAgentById(int id);

	
	
	
	
}
