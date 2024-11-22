package kr.co.lee;

import org.apache.ibatis.annotations.Mapper;

//interface 에는 절대 private을 사용하지 않는다
@Mapper
public interface sample_mapper {

	public String datainsert(sample_dto sdto);
}
