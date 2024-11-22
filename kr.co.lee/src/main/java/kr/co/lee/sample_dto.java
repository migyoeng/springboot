package kr.co.lee;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("sampledto")
public class sample_dto {
	int sidx;
	String subject, stext, sdate;
	
}
