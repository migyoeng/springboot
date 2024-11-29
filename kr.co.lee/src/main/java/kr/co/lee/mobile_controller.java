package kr.co.lee;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class mobile_controller {
	
	@Autowired
	mobile_service ms;
	
	@Resource(name="file_DTO")
	file_DTO fdto;

	PrintWriter pw = null;	//javascript
	Date date = null;		//오늘 날짜
	SimpleDateFormat si = new SimpleDateFormat("yyyyMMddhhmmss");	//새로운 파일명
	Random random = new Random();	//랜덤 숫자 하나 선택

	//@RequestPart : 파일 첨부 기능 중 필수값으로 세팅하지 않을 경우 (Spring Boot 3.x)
	//@RequestParam : 필수로 파일 또는 내용(값을)을 받는 경우
	@PostMapping("excel_uploadok.do")
	public String excel_uploadok(@RequestPart(value="mfile", required = false) MultipartFile mfile,
									HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		
		/* 파일 저장 */
		//String url = req.getServletContext().getRealPath("/payfile/");
		
		//파일 read
		int sign = 0;
		
		InputStream is = mfile.getInputStream();	//업로드 된 파일을 byte 단위로 읽어오는 상황
		//XLSX, XLS를 읽어들이는 코드
		Workbook workbook = WorkbookFactory.create(is);
		//Excel 시트를 가져와 핸들링
		Sheet sheet = workbook.getSheetAt(0);
		//전체 row 개수 파악
		Iterator<Row> rows = sheet.iterator();
		
		while(rows.hasNext()) {		//값이 있는 행을 전체 확인
			Row row = rows.next();	//값이 있는 전체 행을 읽어서 확인 작업
			Iterator<Cell> cell = row.cellIterator();	//열을 배열 형태로 읽어들이는 코드
			if(row.getRowNum() > 0) {
				while(cell.hasNext()) {
					Cell ce = cell.next();	//cell 값을 가져오는 역할
					System.out.println(ce.toString());	//Excel 시트에 사용된 내용을 읽어서 출력
				}
			}
		}
		
		System.out.println(rows.next());
		
		return null;
	}
	
	@PostMapping("/hpfile3.do")
	public String hpfile3() {
		
		return null;
	}
	
	//캠코더 영상 업로드하는 컨트롤러
	@PostMapping("/hpfile2.do")
	public String hpfile2(@RequestParam("mfile2") MultipartFile files, ServletResponse res, ServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		
		return null;
	}
	
	@PostMapping("event_del.do")
	public String event_del(@RequestParam String eidx, HttpServletResponse res, HttpServletRequest req) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			//파일명을 확인하여 실제 디렉토리에서 삭제
			String result = ms.search_image(eidx);
			
			//nio : Local Server, io : Web Server
			//Path url = Paths.get("/mobile/" + result);	//c:\ d:\
			String url = req.getServletContext().getRealPath("/mobile");
			File fe = new File(url+result);
			fe.delete();
			
			
			int result2 = ms.delete_image(eidx);
			if(result2 > 0) {
				this.pw.write("<script>"
						+ "alert('정상적으로 해당 파일을 삭제하였습니다.');"
						+ "location.href='./event_list.do';"
						+ "</script>");
			} else {
				this.pw.write("<script>"
						+ "alert('시스템 오류로 인하여 정상적으로 삭제 되지 않습니다.');"
						+ "location.href='./event_list.do';"
						+ "</script>");
			}
		} catch (Exception e) {
			this.pw.write("<script>"
					+ "alert('시스템 오류로 인하여 정상적으로 삭제 되지 않습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//Model, HttpServletResponse, ServletResponse 함께 사용 시 페이지 백지 화면 출력 주의
	@GetMapping("/event_list.do")
	public String event_list(Model m) {
		List<file_DTO> all = ms.list_image();
		m.addAttribute("ea", all.size());	//데이터 총 개수
		m.addAttribute("all", all);	//데이터 내용
		return "/WEB-INF/views/event_list";
	}
	
	//모바일 사진 이미지 : 용량 제한(10MB 안팎), 캠코더 : 시간제약(100MB), 음성 : 50MB
	@PostMapping("/hpfile1.do")
	public String hpfile1(@RequestParam("mfile") MultipartFile files, ServletRequest req, ServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			
			String filenm = files.getOriginalFilename();
			long filesize = files.getSize();
			//파일 업로드 제한 용량 5242880 byte(=5MB)
			if(filesize > 5242880) {
				this.pw.print("<script>"
						+ "alert('이미지 첨부 최대 크기는 5MB입니다.');"
						+ "history.go(-1);"
						+ "</script>");
			} else {
				//Database에 저장 및 웹 디렉토리에 저장
				//이미지가 저장되는 경로
				String url = req.getServletContext().getRealPath("/mobile/");
				//파일 속성 타입을 가져오는 코드(확장자)
				String type = filenm.substring(filenm.lastIndexOf("."));
				//신규 파일명 직접 랜덤으로 생성 -1 난수 생성
				int no = random.nextInt(40)+1;	//1~40
				//신규 파일명 생성 -2 날짜
				String new_nm = si.format(date);
				//해당 디렉토리에 파일을 업로드 시킴
				FileCopyUtils.copy(files.getBytes(), new File(url + new_nm + no + type));
				
				//해당 URL 경로를 DB에 저장시킴 - 원본명, 사보념ㅇ, 저장경로)
				fdto.setMfile_ori(filenm);	//사용자가 업로드한 원본 파일명
				fdto.setMfile_new(new_nm + no + type);	//개발자가 원본 파일명을 다른 이름으로 변경
				fdto.setMfile_url("./mobile/");
				
				int result = ms.insert_image(fdto);
				if(result > 0) {
					this.pw.print("<script>"
							+ "alert('정상적으로 이벤트에 참여하셨습니다.');"
							+ "location.href='./event_list.do';"
							+ "</script>");
				}
				else {
					
				}
			}
		} catch (Exception e) {
			this.pw.print("<script>"
					+ "alert('이미지 저장에 따른 서비스가 불안정합니다.');"
					+ "history.go(-1);"
					+ "</script>");
		} finally {
			this.pw.close();
		}
		
		return null;
	}
}
