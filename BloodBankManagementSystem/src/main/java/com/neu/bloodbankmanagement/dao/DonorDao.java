package com.neu.bloodbankmanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.pojo.Donor;
import com.neu.bloodbankmanagement.pojo.Role;


public class DonorDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public DonorDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(Donor donor) throws DonorException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(donor);
			System.out.println("\n\nAfter getSession().save(donor)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new DonorException("Exception while registering the donor: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public Donor getDonor(long id) throws DonorException {
		try {
			begin();
			Query q = getSession().createQuery("from Donor where Id = :id");
			q.setLong("id", id);
			Donor donor = (Donor) q.uniqueResult();
			commit();
			return donor;
		} catch (HibernateException e) {
			rollback();
			throw new DonorException("Could not get donor " + id, e);
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username is already registered or not
	public boolean AuthenticateDonorRegistration(Donor donor) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(donor.getEmail())) {
				return true;
			}else if(role.getUserName().equals(donor.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

}
