package in.ashokit.service;

import in.ashokit.entity.Counsellor;

public interface CounsellorService {
	
	public boolean saveCounsellor (Counsellor c);
	
	public Counsellor loginCounsellor (String email, String pwd);

}
