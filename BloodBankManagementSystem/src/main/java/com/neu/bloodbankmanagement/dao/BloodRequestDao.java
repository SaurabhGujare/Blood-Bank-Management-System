package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.BloodRequest;

public class BloodRequestDao extends DAO {
	
	
	public void save(BloodRequest bloodRequest) throws BloodBankException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(bloodRequest);
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
	
	public List<BloodRequest> getBloodRequests(long bloodBankId) throws BloodBankException {
		List<BloodRequest> bloodRequests = new ArrayList<BloodRequest>();
		try {
			begin();
			//create
			commit();
			return bloodRequests;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get bloodrequest " + bloodBankId, e);
		}finally {
			close();
		}
	}

}
