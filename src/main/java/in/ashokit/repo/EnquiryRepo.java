package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{
	
	@Query(value = "select count(*) from enquiry_tbl where counsellor_id=:id", nativeQuery = true)
	public Long getEnquries(Integer id);
	
	@Query(value = "select count(*) from enquiry_tbl where counsellor_id=:id and status=:status", nativeQuery = true)
	public Long getEnquries(Integer id, String status);


}
