package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dashboard.DashBoard;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Enquiry;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.EnquiryRepo;
@Service
public class EnquiryServiceImplement implements EnquiryService{
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	@Autowired
	private CounsellorRepo counsellorRepo;

	@Override
	public DashBoard getdashBoardInfo(Integer cId) {
		Long totalEnqs = enquiryRepo.getEnquries(cId);
		Long openCnt = enquiryRepo.getEnquries(cId, "Open");
		Long lostCnt = enquiryRepo.getEnquries(cId, "Lost");
		Long enrolledCnt = enquiryRepo.getEnquries(cId, "Enrolled");
		
		DashBoard d = new DashBoard();
		d.setTotalEnqs(totalEnqs);
		d.setEnrolledEnqs(enrolledCnt);
		d.setLostEnqs(lostCnt);
		d.setOpenEnqs(openCnt);
		
		return d;
		
	}

	@Override
	public boolean saveEnquiry(Enquiry e, Integer cId) {
		Counsellor counsellor = counsellorRepo.findById(cId).orElseThrow();
		e.setCounsellor(counsellor);
		
		Enquiry save = enquiryRepo.save(e);
		
		
		return save.geteId()!=null;

	}

	@Override
	public List<Enquiry> getAllEnquiries(Enquiry e, Integer cId) {
		Counsellor counsellor = counsellorRepo.findById(cId).orElseThrow();
		Enquiry search = new Enquiry();
		search.setCounsellor(counsellor);
		if(null!=e.getCourse()&&!"".equals(e.getCourse())) {
			search.setCourse(e.getCourse());		
		}
		if(null!=e.getMode()&&!"".equals(e.getMode())) {
			search.setMode(e.getMode());				
		}
		if(null!=e.getStatus()&&!"".equals(e.getStatus())) {
			search.setStatus(e.getStatus());							
		}
		
		
		Example<Enquiry> of = Example.of(search);
		List<Enquiry> findAll = enquiryRepo.findAll(of);
		

		return findAll;
			}

	@Override
	public Enquiry edit(Integer eId) {
		Enquiry enquiry = enquiryRepo.findById(eId).orElseThrow();

		return enquiry;
	}

}
