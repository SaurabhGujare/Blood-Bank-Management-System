package com.neu.bloodbankmanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.neu.bloodbankmanagement.dao.HospitalDao;
import com.neu.bloodbankmanagement.pojo.Hospital;


@Controller
@RequestMapping("/*")
public class AuthenticationController {
	
	@Autowired
	private HospitalDao hospitalDao;
	
	@RequestMapping("/")
	public String showHome(){
		return "home";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping(value = "/registerhospital", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response, ModelMap map, Model model) {
		//request.setAttribute("hospital", new Hospital());
		model.addAttribute("hospital", new Hospital());
		return new ModelAndView("registerHospital");
	}
	
	@RequestMapping(value= "/registerhospital" , method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute("hospital") Hospital hospital, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("\n\nbindingResult.hasErrors() is TRUE");
			return "registerHospital";
		}else {
			System.out.println("\n\nbindingResult.hasErrors() is FALSE");
			hospitalDao.save(hospital);
			System.out.println("\n\nhospital is "+ hospital);
			return "redirect:/";
		}
	}
		
}
