package kr.co.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class goods_service_imp implements goods_service{
	@Autowired
	goods_mapper gm;
	
	@Override
	public int goods_insert(goods_DTO gdto) {
		int result = gm.goods_insert(gdto);
		return result;
	}
}
