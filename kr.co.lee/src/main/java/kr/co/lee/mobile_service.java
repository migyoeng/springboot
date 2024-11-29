package kr.co.lee;

import java.util.List;

public interface mobile_service {

	public int insert_image(file_DTO fdto);
	public List<file_DTO> list_image();
	public int delete_image(String eidx);
	public String search_image(String eidx);
}
