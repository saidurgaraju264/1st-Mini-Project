package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dashboard.DashBoard;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	@Autowired
	private CounsellorService counsellorService;
	@Autowired
	private EnquiryService enquiryService;
	@GetMapping("/logout")
	public String logOut (Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "redirect:/login";
		
	}
	
	@GetMapping("/regi")
	public String loadRegistraion(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		
		return "register";
		
	}
	 @PostMapping("/regi")
	public String handleRegistration (Model model, Counsellor c) {
		boolean saveCounsellor = counsellorService.saveCounsellor(c);
		 if (saveCounsellor) {
			 model.addAttribute("smsg", "Registration saved Successfully");
			 
		 }else {
			 model.addAttribute("emsg", "Registraion is  not saved Successfully ");
		 }
		
		model.addAttribute("counsellor", new Counsellor());

		return "register";
		
	}
	  @GetMapping("/login")
	 public String loadLogin (Model model) {
		 model.addAttribute("counsellor", new Counsellor());
		 
		return "login";
		 
	 }
	   @PostMapping("/login")
	  public String handleLogin(Model model, HttpServletRequest req, Counsellor c) {
		   Counsellor loginCounsellor = counsellorService.loginCounsellor(c.getEmail(), c.getPwd());
		   if ( loginCounsellor == null) {
			   model.addAttribute("err", "Invalid credentials");
				 model.addAttribute("counsellor", new Counsellor());

			   return "login";
			   
			   
		   }else {
			    HttpSession session = req.getSession(true);
			    session.setAttribute("cid", loginCounsellor.getcId());
			   DashBoard getdashBoardInfo = enquiryService.getdashBoardInfo(loginCounsellor.getcId());
			   model.addAttribute("Dashboard", getdashBoardInfo);
		   }
		  // model.addAttribute("counsellor", c);
		   
		return "dashboard";
		  
	  }

}
