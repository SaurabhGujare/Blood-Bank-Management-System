package com.neu.bloodbankmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.BloodRequestException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.BloodRequest;
import com.neu.bloodbankmanagement.pojo.DonationHistory;

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
			Criteria bloodRequestCrit = getSession().createCriteria(BloodRequest.class);
			Criteria bloodBankCrit = bloodRequestCrit.createCriteria("bloodBank");
			bloodBankCrit.add(Restrictions.eq("id",bloodBankId));
			bloodRequests = bloodRequestCrit.list();
			commit();
			return bloodRequests;
		} catch (HibernateException e) {
			rollback();
			throw new BloodBankException("Could not get bloodrequest " + bloodBankId, e);
		}finally {
			close();
		}
	}
	
	//Update confirmation status to approve or disapprove
	public void updateConfirmationStatus(long bloodRequestId, String confirmationValue) {
		System.out.println("*****\nInside BloodRequestDao.updateConfirmationStatus");
		try {
			begin();
			String updateQuery = "UPDATE BloodRequest SET confirmation = :confirmationValue "+
			"WHERE id= :bloodRequestId";
			Query q = getSession().createQuery(updateQuery);
			q.setString("confirmationValue", confirmationValue);
			q.setLong("bloodRequestId", bloodRequestId);
			int result = q.executeUpdate();
			System.out.println("Rows Update: "+result);
			commit();
		}catch(Exception e) {
			rollback();
			System.out.println("*****\nCan not update bloodrequest"+bloodRequestId+"\n"+e.getMessage());
			
		}finally {
			close();
		}
	}
	
	//get confirmation value of the BloodRequest
	public BloodRequest getBloodRequestById(long bloodRequestId) throws BloodRequestException {
		try {
			begin();
			Query q = getSession().createQuery("FROM BloodRequest WHERE id= :bloodRequestId");
			q.setLong("bloodRequestId", bloodRequestId);
			@SuppressWarnings("deprecation")
			BloodRequest bloodRequest = (BloodRequest) q.uniqueResult();
			
			commit();
			return bloodRequest;
			
		}catch(Exception e) {
			rollback();
			System.out.println("Unable to fetch confirmation value: "+e.getMessage());
			throw new BloodRequestException("",e);
		}finally {
			close();
		}
		
	}

}
