package kr.co.lee;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class jstl_controller {

	@GetMapping("/jstl6.do")
	public String jstl6(Model m) {	//Model : JSTL
		String menu = "대메뉴 출력 파트";
		m.addAttribute("menu", menu);
		
		return null;
	}
	
	/*
	응용문제 :
		다음 배열 데이터의 값을 이용하여 웹 페이지에 출력되도록 코드를 작성하시오.
		{"홍길동","강감찬","이순신","유관순","김유신"}
		{"25", "30", "27", "44", "37"}
		
		해당 데이터를 다음과 같이 출력하시오
		홍길동 : 25
		강감찬 : 30
		이순신 : 27
		유관순 : 44
		김유신 : 37
		
		긴급 수정 : 사용자 리스트 중에서 30살 이상 되는 고객만 출력
	*/
	@GetMapping("/jstl7.do")
	public ModelAndView jstl7() {
		//ModelAndView : class
		//mv : instance, new ModelAndView() : 객체
		ModelAndView mv = new ModelAndView();
		String product = "LG 냉장고";
		
		//문제 - 2차 클래스 배열 한번에 코드 작성
		ArrayList<ArrayList<String>> user_data = new ArrayList<>();
		user_data.add(new ArrayList<>(Arrays.asList("홍길동", "25")));
		user_data.add(new ArrayList<>(Arrays.asList("강감찬", "30")));
		user_data.add(new ArrayList<>(Arrays.asList("이순신", "27")));
		user_data.add(new ArrayList<>(Arrays.asList("유관순", "44")));
		user_data.add(new ArrayList<>(Arrays.asList("김유신", "37")));
		
		mv.addObject("user_data", user_data);
		
		//문제
		ArrayList<String> name_list = new ArrayList<>();
		name_list.add("홍길동");
		name_list.add("강감찬");
		name_list.add("이순신");
		name_list.add("유관순");
		name_list.add("김유신");
		
		ArrayList<String> age_list = new ArrayList<>();
		age_list.add("25");
		age_list.add("30");
		age_list.add("27");
		age_list.add("44");
		age_list.add("37");
		
		ArrayList<ArrayList<String>> user_list = new ArrayList<>();
		for(int i=0; i<name_list.size(); i++) {
			ArrayList<String> data = new ArrayList<>();
			data.add(name_list.get(i));
			data.add(age_list.get(i));
			user_list.add(data);
		}
		mv.addObject("user_list", user_list);
		
		mv.addObject("pdname",product);	//key 이름으로 데이터 값을 이관하여 .jsp에 출력시키기 위함
		//mv.setView(null);	//REST-API 구축 시 사용
		//mv.setViewName("/jstl8");	//jsp view 파일명을 지정
		
		return mv;	//mv -> ModelAndView -> jstl7.jsp를 찾아 출력
	}
	
	/* 숙제
	응용문제 : jstl8.do를 실행 시 jstl8.jsp 에 값이 출력 되도록 하시오
		다음 문자 데이터 값이 있습니다. 해당 문자 데이터를 이용하여 웹에 출력하시오.
		"hong, 홍길동, hong@nate.com, 35, 010-2233-4507, 서울특별시 종로3가 국일빌딩"
		
		[웹 출력 예시]
		아이디 : hone
		고객명 : 홍길동
		이메일 : hong@nate.com
		나이 : 35
		연락처 : 010-2233-4507
		주소 : 서울특별시 종로3가 국일빌딩
	*/
	@GetMapping("/jstl8.do")
	public ModelAndView jstl8() {
		ModelAndView mv = new ModelAndView();
		
		String data = "hong, 홍길동, hong@nate.com, 35, 010-2233-4507, 서울특별시 종로3가 국일빌딩";
		String[] user_data = data.split(",");
		for(String info : user_data) {
			info = info.trim();
		}
		mv.addObject("user_data", user_data);
		
		return mv;
	}
	
	
	
}
