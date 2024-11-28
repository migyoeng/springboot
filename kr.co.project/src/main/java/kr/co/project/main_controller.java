package kr.co.project;

import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class main_controller {
	PrintWriter pw = null;
	
	@Resource(name="notice_DTO")
	notice_DTO ndto;
	
	@Autowired
	notice_service ns;
	
	
	@GetMapping("/notice_delete.do")
	public String notice_delete(@RequestParam("nidx") String nidx, 
								String key, ServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		Decoder dc = Base64.getDecoder();
		byte keycheck[] = dc.decode(key);
		String keycode = new String(keycheck);
		if(keycode.equals("kr.co.prject")) {	//보안 키
			byte nidxcheck[] = dc.decode(nidx);	//삭제할 nidx 값(auto_increment) 해석
			String no = new String(nidxcheck);
			int result = ns.noticedel(no);
			if(result > 0) {
				this.pw.print("<script>"
						+ "alert('해당 게시물을 정상적으로 삭제 하였습니다.');"
						+ "location.href='./notice_list.do';"
						+ "</script>");
			}
			else {
				this.pw.print("<script>"
						+ "alert('해당 게시물의 정보가 올바르지 않습니다.');"
						+ "location.href='./notice_list.do';"
						+ "</script>");
			}
			
		} else {
			this.pw.print("<script>"
					+ "alert('해당 페이지에 접근이 잘 못 되었습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		this.pw.close();
		return null;
	}
	
	//Model JSTL 사용 시 Response를 사용하지 못 한다
	//단, 사용해야 하는 경우 return 값으로 null이 아닌 직접 jsp 파일명을 입력해야 웹 페이지 출력이 된다
	@GetMapping("/notice_list.do")
	public String notice_list(Model m, HttpServletResponse res) throws Exception {	//Model : JSTL
		res.setContentType("text/html;charset=utf-8");
		try {	//pw를 사용하면 안된다
			List<notice_DTO> all = ns.noticelist();
			m.addAttribute("total", all.size());	//총 게시글의 개수
			m.addAttribute("all", all);
			
		} catch (Exception e) {	//문제 발생 시 해당 메세지를 웹에 출력 후 해당 페이지로 이동
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('현재 시스템 오류로 인해 정상적인 서비스를 지원하지 못합니다.');"
					+ "location.href='./test.jsp';"
					+ "</script>");
			this.pw.close();
		}
		return "notice_list";	//Response 사용 시 null 값을 사용하지 못함
	}
	
	/*
	//Model JSTL 사용 시 Response를 사용하지 못 한다
	//return 값으로 null이 아닌 "notice_list"로 하면 웹 페이지 출력이 된다
	@GetMapping("/notice_list.do")
	public String notice_list(Model m) throws Exception {	//Model : JSTL
		try {
			List<notice_DTO> all = ns.noticelist();
			m.addAttribute("total", all.size());	//총 게시글의 개수
			m.addAttribute("all", all);
		} catch (Exception e) {
			m.addAttribute("error", "ok");
		}
		return null;
	}
	*/
}
