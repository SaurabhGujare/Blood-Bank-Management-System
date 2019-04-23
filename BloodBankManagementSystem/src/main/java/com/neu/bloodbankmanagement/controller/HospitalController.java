package com.neu.bloodbankmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.BloodRequestDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.exception.HospitalException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.BloodRequest;
import com.neu.bloodbankmanagement.pojo.Hospital;

@Controller
public class HospitalController {
	
	@Autowired
	private BloodBankDao bloodBankDao;
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private BloodRequestDao bloodRequestDao;
	
	@RequestMapping(value = "login/hospital/sendrequest", method = RequestMethod.GET)
	public ModelAndView showRequestForm(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) throws BloodBankException {
		//request.setAttribute("hospital", new Hospital());
		List<BloodBank> bloodBanks = new ArrayList<BloodBank>();
		bloodBanks = bloodBankDao.getAllBloodBanks();
		
		request.setAttribute("bloodBankList", bloodBanks);
				
		model.addAttribute("bloodRequest", new BloodRequest());
		return new ModelAndView("requestForm");
	}
	
	@RequestMapping(value = "login/hospital/sendrequest", method = RequestMethod.POST)
	public String processRequestForm(@Valid @ModelAttribute("bloodRequest") BloodRequest bloodRequest, BindingResult bindingResult,HttpServletRequest request) throws BloodBankException, HospitalException {
		HttpSession session = request.getSession();
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "requestForm";
		}else {
			//get hospital from  the userName and password stored in session
			String userName = (String)session.getAttribute("userName");
			String password = (String)session.getAttribute("password");
			Hospital hospital = hospitalDao.getHospital(userName, password);
			
			//save hospital in bloodRequest
			bloodRequest.setHospital(hospital);
			
			//get bloodbank from form parameter bloodbank
			BloodBank bloodBank = bloodBankDao.getBloodBank(request.getParameter("bloodBankName"));
			
			//save bloodbank in bloodRequest
			bloodRequest.setBloodBank(bloodBank);
			
			//set confirmation to pending for now (until it get approved by the blood bank)
			bloodRequest.setConfirmation("pending");
			
			//save blood request
			bloodRequestDao.save(bloodRequest);
		
		}

		
				
		return "redirect:/login";
	}

}
