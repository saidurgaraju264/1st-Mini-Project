package in.ashokit.service;

import java.util.List;

import in.ashokit.dashboard.DashBoard;
import in.ashokit.entity.Enquiry;

public interface EnquiryService {
	
	public DashBoard getdashBoardInfo(Integer cId);
    
	public boolean saveEnquiry (Enquiry e, Integer cId);
	
	public List<Enquiry> getAllEnquiries (Enquiry e, Integer cId);
	
	public Enquiry edit(Integer eId);
}
