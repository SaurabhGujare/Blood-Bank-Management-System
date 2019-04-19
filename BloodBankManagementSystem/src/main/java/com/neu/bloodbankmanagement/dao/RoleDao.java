package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

import com.neu.bloodbankmanagement.exception.RoleException;
import com.neu.bloodbankmanagement.pojo.Hospital;
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
	
	public ArrayList<Role> getRoles(){
		ArrayList<Role> Roles = new ArrayList<Role>();
		try {
		begin();
		Query q = getSession().createQuery("from Role");
		Roles = (ArrayList) q.list();
		}catch(Exception e) {
			rollback();
		}finally {
			close();
		}
		
		return Roles;
		
	}
	
	

}
