package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;
 

@Service
public class CounsellorServiceImp implements CounsellorService {
    @Autowired
	private CounsellorRepo counsellorRepo;
	@Override
	public boolean saveCounsellor(Counsellor c) {
		Counsellor findByEmail = counsellorRepo.findByEmail(c.getEmail());
		    if (findByEmail!=null) {
		    	return false;
		    }else {
		    	Counsellor save = counsellorRepo.save(c);
		    	return save.getcId()!=null;
		    }
		    
			 
	}	     
	@Override
	public Counsellor loginCounsellor(String email, String pwd) {
		
		Counsellor findyByEmailAndPwd = counsellorRepo.findByEmailAndPwd(email, pwd);
		
		
		return findyByEmailAndPwd;
	}

}
