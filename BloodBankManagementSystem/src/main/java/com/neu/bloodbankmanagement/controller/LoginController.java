package com.neu.bloodbankmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.bloodbankmanagement.dao.BloodBankDao;
import com.neu.bloodbankmanagement.dao.DonorDao;
import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.dao.RoleDao;
import com.neu.bloodbankmanagement.exception.BloodBankException;
import com.neu.bloodbankmanagement.pojo.BloodBank;
import com.neu.bloodbankmanagement.pojo.LoginUser;
import com.neu.bloodbankmanagement.pojo.Role;


//@RequestMapping("/login/*")
@Controller
public class LoginController {
	

	@Autowired
	private HospitalDao hospitalDao;
	
	@Autowired
	private BloodBankDao bloodBankDao;
	
	@Autowired
	private DonorDao donorDao;
	
	@Autowired
	private RoleDao roleDao;
		
	//Invalidate Session
	@RequestMapping("/home")
	public String showHome(HttpServletRequest request){
		HttpSession session = request.getSession();
		System.out.println("\n\n*******Current Session is"+session.getId()+"\n"+(String)session.getAttribute("userName"));
		if(session !=null) {
			session.invalidate();
		}
		System.out.println("\n\n*******Current Session is invalidated");
		return "home";
	}
	
	@RequestMapping("/login")
	public String showLoginPage(Model model){
		System.out.println("Inside showLoginPage");
		model.addAttribute("loginUser", new LoginUser());
		return "login";
	}
	
	@RequestMapping(value= "/login" , method = RequestMethod.POST)
	public String processLoginPage(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws BloodBankException {
		
        HttpSession session = request.getSession();
        session.setAttribute("userName", request.getParameter("userName"));
        session.setAttribute("password", request.getParameter("password"));
        
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "login";
		}else {
			System.out.println("\n\nbindingResult.hasErrors() is FALSE");
			System.out.println("\nLoggedIn User is "+ loginUser);
			System.out.println("\nLoginUser Role is "+request.getParameter("role")+"\n");
			
			if(roleDao.checkLoginUser(loginUser.getUserName(),loginUser.getPassword(),request.getParameter("role"))) {
				if(request.getParameter("role").equals("Hospital")) {
					System.out.println("Redirecting to hospital dashboard");
					return "homeHospital";
					
				}else if(request.getParameter("role").equals("Donor")) {
					System.out.println("Redirecting to Donor dashboard");
					return "homeDonor";
					
				}else if(request.getParameter("role").equals("BloodBank")) {
					
					System.out.println("Redirecting to Blood Bank dashboard");
					return "homeBloodBank";
					
				}else {
					return "login";
				}
			}
			
	
			return "login";
		}
	}

}
