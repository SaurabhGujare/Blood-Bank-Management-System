package com.neu.bloodbankmanagement.validation;

import java.util.ArrayList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.dao.RoleDao;
import com.neu.bloodbankmanagement.pojo.Role;

public class CheckEmailContraintValidator implements ConstraintValidator<CheckEmail, String>{
	
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public boolean isValid(String userEnteredValue, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		ArrayList<Role> roles= roleDao.getRoles();
		System.out.println("*****"+userEnteredValue);
		boolean result= true;
		try {
			 
			
			for(Role role: roles) {
				if(role.getEmail().equals(userEnteredValue)) {
					result = false ;
					break;
				}else {
					result = true;
				}
				
			}
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			
		}
		
		
		return result;
	}

}
