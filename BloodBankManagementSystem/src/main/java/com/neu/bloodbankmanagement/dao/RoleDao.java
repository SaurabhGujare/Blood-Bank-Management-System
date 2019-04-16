package com.neu.bloodbankmanagement.dao;

import com.neu.bloodbankmanagement.exception.RoleException;
import com.neu.bloodbankmanagement.pojo.Role;

public class RoleDao extends DAO {
	
	public RoleDao() {
		
	}
	
	public void save(Role role) throws RoleException {
		try {
			begin();
			getSession().save(role);
			commit();
		}catch(Exception e) {
			rollback();
			throw new RoleException("Exception while registering the hospital: "+e.getMessage());	
		}finally {
			close();
		}
		
	}

}
