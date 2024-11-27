package kr.co.lee;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class file_controller {

	PrintWriter pw = null;
	
	//파일 전송 응용문제
	@PostMapping("/fileupok3.do")
	public String fileupok3(ServletRequest req, ServletResponse res, @RequestParam("mfile") List<MultipartFile> files) throws Exception {
		//xls 파일만 저장 : application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
		res.setContentType("text/html;charset=utf-8");
		try {
			int fileno = files.size();
			String url = req.getServletContext().getRealPath("/payfile/");
			int w = 0;
			while(w < fileno) {
				/* 
				   getOriginalFilename() : 파일명
				   get(w).getContentType() : 파일 속성
				   getSize() : 파일 크기
				*/
				//String oriname = files.get(w).getOriginalFilename().substring(w);
				String oriname = files.get(w).getOriginalFilename();
				String types = oriname.substring(oriname.lastIndexOf("."));
				
				String filetype = files.get(w).getContentType();
				long filesize = files.get(w).getSize();
				//UUID : 암호화 알고리즘 클래스(Spring 전용)
				/*
				  UUID : version1 (PC - Macaddress 형태로 암호화)
				  UUID : version3 (MD5, hash)
				  UUID : version4 (Random 함수 이용, hash)
				  UUID : version5 (SHA-1 함수 이용
				  )
				  UUID uuid3 = UUID.nameUUIDFromBytes(oriname.getBytes());
				*/
				String uuid = UUID.randomUUID().toString();
				String newname = url + uuid + types;
				if(files.get(w).getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
					FileCopyUtils.copy(files.get(w).getBytes(), new File(newname));
				}
				
				w++;
			}
			this.pw = res.getWriter();
			this.pw.print("<script>"
					+ "alert('등록하신 첨부파일이 모두 정상적으로 저장되었습니다');"
					+ "history.go(-1);"
					+ "</script>");
			this.pw.close();
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('첨부파일이 올바르지 않거나 해당 용량이 맞지 않아 저장되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}
		
		return null;
	}
	
	//복합 파일 전송
	@PostMapping("/fileupok2.do")
	public String fileupok2(HttpServletRequest req, HttpServletResponse res, @RequestParam("mfile") MultipartFile files[]) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		//여러 개의 파일 첨부 시 name 값은 모두 동일, 배열로 받아온다
		String filenm1 = files[0].getOriginalFilename();
		String filenm2 = files[1].getOriginalFilename();
		String filenm3 = files[2].getOriginalFilename();
		
		//파일 저장 경로
		String url = req.getServletContext().getRealPath("/upload/");
		//확장자명 추출
		int w = 0;
		
		//이름을 변경하여 저장
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHms");
		String ymd = df.format(today);
		
		while(w < files.length) {
		
			if(!files[w].getOriginalFilename().equals("")) {
				int no = (int)Math.ceil(Math.random()*1000);
				String nm = files[w].getOriginalFilename().substring(files[w].getOriginalFilename().lastIndexOf("."));
				String rename = ymd + no + nm;
				FileCopyUtils.copy(files[w].getBytes(), new File(url + rename));
			}
			w++;
		}
		this.pw = res.getWriter();
		this.pw.print("<script>"
				+ "alert('등록하신 첨부파일이 모두 정상적으로 저장되었습니다');"
				+ "history.go(-1);"
				+ "</script>");
		
		return null;
	}
	
	//단일 파일 전송
	@PostMapping("/fileupok1.do")
	public String fileupok1(@RequestParam("mfile") MultipartFile files, HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		String filenm = files.getOriginalFilename();	//파일명 출력
		long filesize = files.getSize(); //파일 크기(용량)
		String filetype = files.getContentType();	//파일 타입(속성) - jpg, jpeg 등
		System.out.println(filenm);
		System.out.println(filesize);	//byte 단위
		System.out.println(filetype);
		
		//application.properties에서 최대 용량을 핸들링하면 서버 자체가 죽는다
		//용량 제한 두려면 controller에서 제어하기
		//2MB 이하의 용량만 처리하겠다는 조건문
		this.pw = res.getWriter();
		if(filesize > 2097152) {
			this.pw.print("<script>"
					+ "alert('첨부파일 용량은 최대 2MB까지 등록 가능합니다');"
					+ "history.go(-1);"
					+ "</script>");
		}
		else {	//첨부파일 image 또는 압축파일, pdf, xls, doc 허용
			//.jsp .java .php .asp .ini .js .. => 해킹의 위험으로 인해 절대 첨부 파일에 적용하지 않는다
			if(filetype.equals("image/jpeg") || filetype.equals("image/png") || filetype.equals("image/gif") || filetype.equals("image/bmp")
					|| filetype.equals("image/webp")) {
				String url = req.getServletContext().getRealPath("/upload/");
				File fe = new File(url+filenm);	//경로 + 기존 파일명
				byte bt[] = files.getBytes();
				FileCopyUtils.copy(bt, fe);
				
				this.pw.print("<script>"
						+ "alert('정상적으로 파일이 업로드되었습니다');"
						+ "history.go(-1);"
						+ "</script>");
			}
			else {
				this.pw.print("<script>"
						+ "alert('첨부파일은 이미지만 업로드 가능합니다');"
						+ "history.go(-1);"
						+ "</script>");
			}
		}
		
		return null;
	}
	
}
