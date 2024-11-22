package kr.co.lee;

import org.apache.ibatis.annotations.Mapper;

@Mapper		//mybatis .xml 과 동기화
public interface shopping_query {

	String search_id(String mid);	//사용자 ID 체크
	int member_join(member_DTO mdto);	//신규 가입 mapper
}
