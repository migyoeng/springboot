package kr.co.lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class sample_serviceimp implements sample_service {

	@Autowired
	private sample_mapper sm;
	
	@Override
	public String datainsert(sample_dto sdto) {
		String result = sm.datainsert(sdto);
		return result;
	}
}
