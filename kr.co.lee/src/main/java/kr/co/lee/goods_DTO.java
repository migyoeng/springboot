package kr.co.lee;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("goods_DTO")
public class goods_DTO {
	int gidx, gprice, gsales;
	String gname, gdate;
}
