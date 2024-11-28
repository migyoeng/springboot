package kr.co.project;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface notice_mapper {

	public List<notice_DTO> noticelist();
	public int noticedel(String nidx);
}
