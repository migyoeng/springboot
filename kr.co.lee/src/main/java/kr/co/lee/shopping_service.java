package kr.co.lee;


public interface shopping_service {

	public String search_id(String mid);	//Controller에서 Mapper로 전달
	public int member_join(member_DTO mdto);
}
