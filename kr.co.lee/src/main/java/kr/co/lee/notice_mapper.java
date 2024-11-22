package kr.co.lee;

import org.apache.ibatis.annotations.Mapper;

//DTO, Controller, ServiceImp - class
//Mapper, Service - interface
@Mapper	//.xml과 연동
public interface notice_mapper {

	int notice_insert(notice_DTO nd);
}
