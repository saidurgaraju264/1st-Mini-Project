package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.dashboard.DashBoard;
import in.ashokit.entity.Enquiry;
import in.ashokit.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	@Autowired
	private EnquiryService enquiryservice;
	
	@GetMapping("/add")
	public String loadAddEnquiries(Model model) {
		model.addAttribute("enquiry", new Enquiry());
		return "addenq";
		
	}
	 @PostMapping("/add")
	 public String handleAddEnquiries (Model model, HttpServletRequest req, Enquiry e) {
		 
		 HttpSession session = req.getSession(false);
		 Integer cid = (Integer)  session.getAttribute("cid");
		 boolean saveEnquiry = enquiryservice.saveEnquiry(e, cid);
		  if (saveEnquiry) {
			  model.addAttribute("smsg", "Enquiry is added");
		  }else {
			  model.addAttribute("emsg", "Enquiry is not added");
		  }
			model.addAttribute("enquiry", new Enquiry());

		return "addenq";
		 
	 }
	 @GetMapping("/enquiries")
	  public String getEnquiries(Model model, HttpServletRequest req) {

			 HttpSession session = req.getSession(false);
			 Integer cid = (Integer)  session.getAttribute("cid");
			model.addAttribute("enquiry", new Enquiry());

			 List<Enquiry> allEnquiries = enquiryservice.getAllEnquiries(new Enquiry(), cid);
			 model.addAttribute("enqs", allEnquiries);

			model.addAttribute("enquiry", new Enquiry());
	 
			return "view";
		  
	  }
	 @PostMapping("/filter-enquiries")
	 public String filters (Model model, HttpServletRequest req, @ModelAttribute("e")Enquiry e ) {
		 
		 HttpSession session = req.getSession(false);
		 Integer cid = (Integer)  session.getAttribute("cid");
			model.addAttribute("enquiry", new Enquiry());

		 List<Enquiry> allEnquiries = enquiryservice.getAllEnquiries(e, cid);
		 model.addAttribute("enqs", allEnquiries);


		 
		return "view";
		 
	 }
	 @GetMapping("/edit")
	 public String editFunction ( @RequestParam("eId") Integer eId, Model model) {
		 
		 Enquiry edit = enquiryservice.edit(eId);
		 
		 
		 model.addAttribute("edit", edit);
			model.addAttribute("enquiry", new Enquiry());

		return "addenq";
		 
	 }
	   @GetMapping("/dash")
	   public String dashboard(Model model, HttpServletRequest req) {
		   HttpSession session = req.getSession(true);
			 Integer cid = (Integer)  session.getAttribute("cid");
		   DashBoard getdashBoardInfo = enquiryservice.getdashBoardInfo(cid);
		   model.addAttribute("Dashboard", getdashBoardInfo);

		   
		   
		return "dashboard";
		   
	   }


	 
}

