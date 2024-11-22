package kr.co.lee;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class sample_controller {

	@Autowired
	private sample_service ss;
	
	@Resource(name="sampledto")
	private sample_dto sdto;
	
	PrintWriter pw = null;
	
	@PostMapping("/sampleok.do")
	public String sampleok(@ModelAttribute("smaple") sample_dto sdto, HttpServletResponse res) {
		try {
			this.pw = res.getWriter();
			
		} catch (Exception ep) {
			
		} finally {
			pw.close();
		}
		return null;	//동일한 이름의 jsp 파일을 찾는다
	}
}
