package com.neu.bloodbankmanagement.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.Hospital;


public class HospitalDao extends DAO {
	
	public HospitalDao() {
		
	}
	
	public void save(Hospital hospital) throws HospitalException {
		try {
			begin();
			getSession().save(hospital);
			commit();
		}catch(Exception e) {
			rollback();
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

}
