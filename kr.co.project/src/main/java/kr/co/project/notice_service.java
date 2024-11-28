package kr.co.project;

import java.util.List;

public interface notice_service {

	public List<notice_DTO> noticelist();
	public int noticedel(String nidx);
}
