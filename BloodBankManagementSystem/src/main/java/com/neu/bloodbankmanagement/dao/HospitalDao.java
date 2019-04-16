package com.neu.bloodbankmanagement.dao;

import com.neu.bloodbankmanagement.pojo.Hospital;

public class HospitalDao extends DAO {
	
	public HospitalDao() {
		
	}
	
	public void save(Hospital hospital) {
		try {
			begin();
			getSession().save(hospital);
			commit();
		}catch(Exception e) {
			rollback();
			System.out.println("Exception while registering the hospital: "+e.getMessage());	
		}finally {
			close();
		}
		
	}

}
