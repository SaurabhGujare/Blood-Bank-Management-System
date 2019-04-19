package com.neu.bloodbankmanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.Role;


public class BloodBankDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public BloodBankDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(BloodBank bloodBank) throws BloodBankException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(bloodBank);
			System.out.println("\n\nAfter getSession().save(bloodBank)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new BloodBankException("Exception while registering the BloodBank: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public BloodBank getBloodBank(long id) throws BloodBankException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where Id = :id");
			q.setLong("id", id);
			BloodBank bloodBank = (BloodBank) q.uniqueResult();
			commit();
			return bloodBank;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get hospital " + id, e);
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username are already registered or not
	public boolean AuthenticateBloodBankRegistration(BloodBank bloodBank) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(bloodBank.getEmail())) {
				return true;
			}else if(role.getUserName().equals(bloodBank.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

}
