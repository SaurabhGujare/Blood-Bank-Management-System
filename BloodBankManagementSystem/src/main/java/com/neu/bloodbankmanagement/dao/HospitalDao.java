package com.neu.bloodbankmanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.Hospital;
import com.neu.bloodbankmanagement.pojo.Role;


public class HospitalDao extends DAO {
	
	
	private RoleDao roleDao;
	
	public HospitalDao() {
		roleDao = new RoleDao();
		
	}
	
	public void save(Hospital hospital) throws HospitalException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(hospital);
			System.out.println("\n\nAfter getSession().save(hospital)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new HospitalException("Exception while registering the hospital: "+e.getMessage());	
		}finally {
			close();
		}
		
	}
	
	public Hospital getHospital(long id) throws HospitalException {
		try {
			begin();
			Query q = getSession().createQuery("from Hospital where Id = :id");
			q.setLong("id", id);
			Hospital hospital = (Hospital) q.uniqueResult();
			commit();
			return hospital;
		} catch (HibernateException e) {
			rollback();
			throw new HospitalException("Could not get hospital " + id, e);
		}finally {
			close();
		}
	}
	
	//get Hospital from userName and password
	public Hospital getHospital(String userName, String password) throws HospitalException {
		try {
			begin();
			Query q = getSession().createQuery("from Hospital where userName = :userName AND password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			Hospital hospital = (Hospital) q.uniqueResult();
			commit();
			return hospital;
		} catch (HibernateException e) {
			rollback();
			throw new HospitalException("Could not get hospital " + userName, e);
		}finally {
			close();
		}
	}
	
	//Authenticates whether email or username is already registered or not
	public boolean AuthenticateHospitalRegistration(Hospital hospital) {
		for(Role role: roleDao.getRoles()) {
			if( role.getEmail().equals(hospital.getEmail())) {
				return true;
			}else if(role.getUserName().equals(hospital.getUserName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

}
