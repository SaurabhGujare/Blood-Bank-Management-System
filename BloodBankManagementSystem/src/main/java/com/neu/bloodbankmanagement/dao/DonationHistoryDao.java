package com.neu.bloodbankmanagement.dao;

import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.pojo.DonationHistory;
import com.neu.bloodbankmanagement.pojo.Hospital;

public class DonationHistoryDao extends DAO {
	
	
	public void save(DonationHistory donationHistory) throws DonationHistoryException {
		try {
			begin();
			System.out.println("\n\nAfter Begin");
			getSession().save(donationHistory);
			System.out.println("\n\nAfter getSession().save(hospital)");
			commit();
			System.out.println("\n\nAfter commit");
		}catch(Exception e) {
			System.out.println("\n\nBefore rollback");
			rollback();
			System.out.println("*********************\n\n"+e.getLocalizedMessage()+" "+e.getMessage()+"\n");
			throw new DonationHistoryException("Exception while registering the DonationHistory: "+e.getMessage());	
		}finally {
			close();
		}
		
	}

}
