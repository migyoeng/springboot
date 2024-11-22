package kr.co.lee;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class goods_controller {
	
	PrintWriter pw = null;
	
	@Autowired
	goods_service gs;
	
	@Resource(name="goods_DTO")
	goods_DTO gdto;
	
	@GetMapping("/goods/goods_list.do")
	public String goods_list() {
		return null;
	}
	
	@PostMapping("/goods/goods_insertok.do")
	public void goods_insertok(goods_DTO gdto, HttpServletResponse res) throws Exception{
		res.setContentType("text/html; charset=utf-8");
		this.pw = res.getWriter();
		
		int result = gs.goods_insert(gdto);
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('상품이 성공적으로 등록되었습니다.');"
					+ "location.href='./goods/goods_list.do';"
					+ "</script>");
		}
		else {
			this.pw.print("<script>"
					+ "alert('서비스 오류로 인해 상품 등록에 실패하였습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		pw.close();
	}

	@GetMapping("/goods/goods_insert.do")
	public void goods_insert(Model m) {
		m.addAttribute("action", "./goods_insertok.do");
	}
}
