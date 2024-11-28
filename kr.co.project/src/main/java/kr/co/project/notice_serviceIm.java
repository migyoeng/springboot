package kr.co.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class notice_serviceIm implements notice_service {

	@Autowired
	notice_mapper nm;
	
	@Override
	public int noticedel(String nidx) {
		int result = nm.noticedel(nidx);
		return result;
	}
	
	@Override
	public List<notice_DTO> noticelist() {
		List<notice_DTO> result = nm.noticelist();
		return result;
	}
}
