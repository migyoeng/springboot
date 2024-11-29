package kr.co.lee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mobile_serviceIm implements mobile_service {

	@Autowired
	mobile_mapper mm;
	
	@Override
	public String search_image(String eidx) {
		String result = mm.search_image(eidx);
		return result;
	}
	
	@Override
	public int delete_image(String eidx) {
		int result = mm.delete_image(eidx);
		return result;
	}
	
	@Override
	public List<file_DTO> list_image() {
		List<file_DTO> all = mm.list_image();
		return all;
	}
	
	@Override
	public int insert_image(file_DTO fdto) {
		int result = mm.insert_image(fdto);
		return result;
	}
}
