package com.neu.bloodbankmanagement.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.DonationHistoryDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.DonationHistoryException;
import com.neu.bloodbankmanagement.exception.DonorException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.DonationHistory;
import com.neu.bloodbankmanagement.pojo.Donor;

@Controller
public class DonateController {
	
	@Autowired
	private BloodBankDao bloodBankDao;
	
	@Autowired
	private DonorDao donorDao;
	
	@Autowired
	private DonationHistoryDao donationHistoryDao;
	
	//amount donated at once is always 450ml
	static final int BLOOD_AMOUNT = 450; 

	@RequestMapping(value = "/login/bloodbank/donateform", method = RequestMethod.GET)
	public ModelAndView showDonateForm(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		//request.setAttribute("hospital", new Hospital());
		model.addAttribute("donationHistory", new DonationHistory());
		return new ModelAndView("DonateForm");
	}
	
	@RequestMapping(value = "/login/bloodbank/donateform", method = RequestMethod.POST)
	public String processDonateForm(HttpServletRequest request, HttpServletResponse response) throws ParseException, DonorException, BloodBankException, DonationHistoryException {
		HttpSession session = request.getSession();
		String donarEmail;
		Date donattionDate;
		donarEmail = request.getParameter("donarEmail");
		donattionDate = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("donationDate"));
		//get donor and check him with the credentials entered
		//if donor if valid then add its donation history else throw donate form view
		System.out.println(donarEmail);
		for(String email: donorDao.getAllDonorEmails()) {
			System.out.println(email);
			if(donarEmail.equals(email)) {
				//Insert donation history;
				//Set Id(PK) as FK in Donor and BloodBank tables
				
				//Get bloodbank instance from the session username
				BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
				System.out.println("\n\n\n***************"+(String)session.getAttribute("userName")+"\n\n");
				System.out.println(bloodBank);
				//Get Donor from the email entered
				Donor donor = donorDao.getDonor(donarEmail);
				
				
				DonationHistory donationHistory = new DonationHistory();
				donationHistory.setBloodType(donor.getBloodType());
				donationHistory.setDate(donattionDate);
				donationHistory.setBloodAmount(BLOOD_AMOUNT);//Constant value
				//save donor and bloodbank in donatoinhistry
				donationHistory.setBloodBank(bloodBank);
				donationHistory.setDonor(donor);
				
				donationHistoryDao.save(donationHistory);
				return "homeBloodBank";
				
			}else {
				System.out.println(email+" Email address does not exists");
				 
			}
			
		}
		
		
		return "DonateForm";
	}
	
	
}
