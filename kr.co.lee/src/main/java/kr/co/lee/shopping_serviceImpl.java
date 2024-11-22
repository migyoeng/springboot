package kr.co.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service	//해당 interface를 로드하면 해당 class를 실행시키는 어노테이션
public class shopping_serviceImpl implements shopping_service {
	
	//mapper.xml에 있는 DDL 명령어를 실행
	@Autowired
	private shopping_query sq;
	
	@Override
	public int member_join(member_DTO dto) {
		int result = sq.member_join(dto);	//Controller에서 받은 dto 내용을 mapper로 전달
		return result;
	}
	
	@Override
	public String search_id(String mid) {	//아이디 중복 체크 메소드
		String result = sq.search_id(mid);
		return result;	//Controller에게 결과값을 통보
	}
}
