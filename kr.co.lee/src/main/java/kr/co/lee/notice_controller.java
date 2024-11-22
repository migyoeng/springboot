package kr.co.lee;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class notice_controller {
	
	PrintWriter pw = null;
	
	//DTO 파일 로드
	@Resource(name="noticedto")
	notice_DTO nd;
	
	@Autowired
	notice_service ns;
	
	@GetMapping("/notice/notice_list.do")
	public String notice_list() {
		
		return null;
	}
	
	@PostMapping("/notice/notice_writeok.do")
	public void notice_writeok(@ModelAttribute(name="nt") notice_DTO nd, HttpServletResponse res) throws Exception{
		//html에서 script를 작동시키기 위한 방식 => text/html : 문자 형태를 html 파일 형태로 실행되게 설정
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		
		int result = ns.notice_insert(nd);
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('정상적으로 등록 완료되었습니다.');"
					+ "location.href='./notice_list.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('시스템 오류로 인하여 서비스가 중지되었습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
	}

	@GetMapping("/notice/notice_write.do")
	public String notice_writer(Model m) {	//Model : JSTL
		//setAttribute(Controller) , getAttribute(JSP) => JSP
		m.addAttribute("user", "ADMIN");
		m.addAttribute("action", "./notice_writeok.do");
		return null;	//view : notice_write.jsp 호출
	}
}
