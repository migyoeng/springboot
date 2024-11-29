package kr.co.lee;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("file_DTO")
public class file_DTO {
	int eidx;
	String mfile_ori, mfile_new, mfile_url, mfile_date;
}
