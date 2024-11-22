package kr.co.lee;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class main_controller {
	
	@Resource(name="memberdto")	//member_DTO.java => load
	member_DTO mdto;
	
	@Autowired
	private shopping_service ss;	//interface를 최종 호출하는 부분
	
	@PostMapping("/joinok.do")
	public String joinok(@ModelAttribute("join") member_DTO mdto, HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");	//script 한글 깨짐 방지
		
		String agree1 = (String)mdto.getMagree1();
		String agree2 = (String)mdto.getMagree2();
		//setter에 신규 값을 갱신하는 조건문
		if(agree1 == null) {
			mdto.setMagree1("N");	//null일 경우 Database enum -> N 강제 입력
		}
		if(agree2 == null) {
			mdto.setMagree2("N");	//null일 경우 Database enum -> N 강제 입력
		}
		
		int result = ss.member_join(mdto);
		PrintWriter pw = null;
		try {
			pw = res.getWriter();
			
			if(result > 0) {
				pw.print("<script>"
						+ "alert('정상적으로 회원가입이 완료 되었습니다.');"
						+ "location.href='./login.html';"
						+ "</script>");
			}
			else {
				pw.print("<script>"
						+ "alert('아이디 및 휴대폰 번호 중복으로 회원가입이 되지 않았습니다.');"
						+ "history.go(-1);"
						+ "</script>");
			}
		} catch (Exception e) {
			pw.print("<script>"
					+ "alert('Data 오류로 인해 가입이 되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			pw.close();
		}
		
		return null;
	}
	
		
	//아이디 중복체크
	@CrossOrigin("*")	//ajax CORS 방지
	@PostMapping("/idcheck.do")
	//public String idcheck(@RequestParam(value="mid") String mid) {
	//public String idcheck(@RequestBody String mid) {	//Frontend에서 배열로 날아 왔을 때 사용
	//public String idcheck(String mid) {
	public String idcheck(@RequestParam String mid, HttpServletResponse res) throws Exception {
		//WEB에서 사용, java에서는 잘 사용하지 않음
		PrintWriter pw = res.getWriter();	//script 및 ajax 시 return 결과값을 Front에 전달
		
		String result = "";	//결과값을 Front-end 에 전달할 변수
		
		if(mid.equals("")) {
			System.out.println("값이 없음");
		}
		else {
			//DB의 값을 검토
			result = ss.search_id(mid);
			//System.out.println(result);
			
			pw.print(result);	//Front-end return 값
			pw.close();
		}
		return null; 
	}
	
	
	@GetMapping("/list.do")
	public String mainpage() {
		return "/WEB-INF/views/list";
	}
	
	@GetMapping("/admin/login.do")
	public String adminpage() {
		System.out.println("TEST");
		return null;
	}

	/*
	@GetMapping("/index.do")	//가상의 주소
	public String mainpage() {
		System.out.println("Test");
		return "test";	//application View 경로 => .jsp 사용하지 않고 jsp 파일명만 사용 | .jsp를 붙이면 404 에러
	}
	
	@RequestMapping(value="/index2.do", method=RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String mainpage2() {
		System.out.println("Test2");
		return "index";
		//return null;	//=> WEB-INF/views/index2.jsp | 가상 주소와 동일한 jsp 페이지를 찾는다
	}
	*/
	
}
