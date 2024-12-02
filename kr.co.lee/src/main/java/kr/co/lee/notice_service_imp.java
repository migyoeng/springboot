package kr.co.lee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class notice_service_imp implements notice_service{
	@Autowired
	protected notice_mapper nm;
	
	
	@Override
	public int notice_insert(notice_DTO nd) {
		int result = nm.notice_insert(nd);
		return result;
	}
	
	@Override
	public int noticein(notice_DTO ndto) {
		int result = nm.noticein(ndto);
		return result;
	}
	
}
