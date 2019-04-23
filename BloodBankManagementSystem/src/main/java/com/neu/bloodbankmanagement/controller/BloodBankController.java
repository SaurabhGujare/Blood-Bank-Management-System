package com.neu.bloodbankmanagement.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.DonationHistory;


@Controller
public class BloodBankController {
	
	@Autowired
	private BloodBankDao bloodBankDao;

	@Autowired
	private DonorDao donorDao;

	@Autowired
	private DonationHistoryDao donationHistoryDao;
	
	@RequestMapping(value = "/login/bloodbank/searchdonor", method = RequestMethod.GET)
	public String showBloodBankRespectiveDonors(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException {
		
		HttpSession session = request.getSession();
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		//get Bloodbank id from username and password
		BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
		System.out.println(bloodBank);
		
		//get donation history of the blood bank
		for(DonationHistory dh: donationHistoryDao.getBloodBankDonationHistory(bloodBank.getId())) {
			System.out.println("****\nDonationDate: "+dh.getDate()+"\nBloodBankId: "+dh.getBloodBank().getName()+"\nDonorName: "+dh.getDonor().getFirstName());
		}
		//send donationhistory list to the jsp to display
		request.setAttribute("donationHistoryList",donationHistoryDao.getBloodBankDonationHistory(bloodBank.getId()) );
		
		//get donor of the blood bank
		
		return "bloodBankDonors";
	}
	
	@RequestMapping(value = "/login/bloodbank/stocks", method = RequestMethod.GET)
	public String showBloodBankStocks(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException {
		
		HttpSession session = request.getSession();
		System.out.println("\n\n\n***Blood Bank username"+(String)session.getAttribute("userName")+"\n\n");
		//get Bloodbank id from username and password
		BloodBank bloodBank = bloodBankDao.getBloodBankSession((String)session.getAttribute("userName"),(String) session.getAttribute("password"));
		System.out.println(bloodBank);
		
		//get blood bank stocks
		List<Object[]> bloodBankStocks =  donationHistoryDao.getBloodBankStock(bloodBank.getId());
		String[] bloodTypes = new String[bloodBankStocks.get(0).length]; 
		Integer[] totalBloodAmount = new Integer[bloodBankStocks.get(1).length];
		
		System.out.println("*****\n\nType||Amount\n");
		
		for(int i=0; i<bloodBankStocks.size();i++) {
			System.out.println(bloodBankStocks.get(i)[0]+"  ||"+bloodBankStocks.get(i)[1]+"  |");
			
		}
		//send donationhistory list to the jsp to display
		request.setAttribute("stockList", bloodBankStocks);
		
		return "bloodBankStocks";
	}
	
	@RequestMapping(value = "/login/bloodbank/bloodrequest", method = RequestMethod.GET)
	public String showBloodBankRequests(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException, DonationHistoryException {
		
		
		return "bloodBankRequests";
	}

}
