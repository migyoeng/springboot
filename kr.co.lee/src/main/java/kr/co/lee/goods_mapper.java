package kr.co.lee;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface goods_mapper {
	int goods_insert(goods_DTO gdto);
}
