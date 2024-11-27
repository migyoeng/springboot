package kr.co.lee;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class main_controller implements security {
	
	@Resource(name="memberdto")	//member_DTO.java => load
	member_DTO mdto;
	
	@Autowired
	private shopping_service ss;	//interface를 최종 호출하는 부분
	
	PrintWriter pw = null;
	HttpSession se = null;	//session을 필드에 선언 시 모든 Mapping에서 해당 session 핸들링할 수 있다
	
	
	//장바구니 페이지
	@GetMapping("/basket.do")
	public String basket(Model m) {
		m.addAttribute("id", this.se.getAttribute("id"));
		m.addAttribute("name", this.se.getAttribute("name"));
		m.addAttribute("email", this.se.getAttribute("email"));
		return null;
	}
	
	//메인 페이지
	@GetMapping("/main.do")
	public String main(Model m, HttpServletRequest req) {
		//this.se = req.getSession();	//세션 값
		//JSTL 활용으로 로그인 정보 출력
		m.addAttribute("id", this.se.getAttribute("id"));
		m.addAttribute("name", this.se.getAttribute("name"));
		m.addAttribute("email", this.se.getAttribute("email"));
		return null;
	}
	
	@PostMapping("/loginok.do")
	public String loginok(@RequestParam String mid, String mpass, String idsave, ServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		//Service -> Controller 로 DTO에 값을 이관
		List<member_DTO> mdto = ss.login_id(mid);
		
		if(mdto.size() == 0) {	//만약 해당 DTO에 내용이 없을 경우
			System.out.println("값 없음");
		}
		else {	//사용자의 정보를 출력(해당 아이디가 있을 경우)
			try {
				this.pw = res.getWriter();
				StringBuilder repass = secode(mpass);
				
				if(mdto.get(0).mpass.equals(repass.toString())) {
					this.se = req.getSession();
					se.setAttribute("id", mdto.get(0).mid);
					se.setAttribute("name", mdto.get(0).mname);
					se.setAttribute("email", mdto.get(0).memail);
					//storage까지 등록을 하는 코드
					if(idsave == null) {
						this.pw.print("<script>"
								+ "window.localStorage.removeItem('mid');"
								+ "</script>");
					}
					else {
						this.pw.print("<script>"
								+ "window.localStorage.setItem('mid','" + mid + "');"
								+ "</script>");
					}
					
					this.pw.print("<script>"
							+ "alert('로그인 되셨습니다.');"
							+ "location.href='./main.do';"
							+ "</script>");
				} else {
					this.pw.print("<script>"
							+ "alert('아이디 및 패스워드가 올바르지 않습니다.');"
							+ "history.go(-1);"
							+ "</script>");
				}
				
			} catch (Exception e) {
				this.pw.print("<script>"
						+ "alert('DB 오류로 인하여 서비스가 올바르지 않습니다.');"
						+ "history.go(-1);"
						+ "</script>");
			} finally {
				this.pw.close();
			}
		}
		return null;
	}
	
	@PostMapping("/joinok.do")
	public String joinok(@ModelAttribute("join") member_DTO mdto, HttpServletResponse res) throws Exception {
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
		
		//정보 저장 전 패스워드 암호화
		//setter에 사용자가 입력한 패스워드를 getter로 로드한 후 다시 암호화한 값을 setter로 이관
		String userpw = mdto.getMpass();
		StringBuilder repass = secode(userpw);	//interface에 있는 암호화된 값을 return
		mdto.setMpass(repass.toString());	//StringBuilder -> String
		
		//회원 가입 정보 저장하는 코드
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
