package kr.co.lee;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//기존 Controller 단에서 interface를 사용하기 위해 @Service 클래스를 생성하여 Controller 단에서 @Autowired를 사용했었다
//@Service 클래스를 생성하지 않고 Controller 단에서 즉시 interface를 사용하는 방법은
//controller에서 implements interface!!!!!하면 즉시 interface의 메소드를 사용할 수 있다
@Controller	//security interface를 로드함
public class security_controller implements security {
	
	@GetMapping("/userpass.do")
	public String userpass() {
		String pw = "1004apink";
		try {
			//secode : default 메소드를 핸들링한 형태 - 이 것이 정통 암호화를 사용하는 형태
			StringBuilder result = secode(pw);
			System.out.println(result);
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("패스워드 변경 오류");
		}
		
		return null;
	}

	//Base64 암호화 기술
	@GetMapping("/se1.do")
	public String se1() {
		String pw = "a123546";
		byte pw2[] = pw.getBytes();	//문자를 byte로 변환
		Encoder code = Base64.getEncoder();	//해당 암호화(base64)
		byte se[] = code.encode(pw2);		//해당 내용을 byte형으로 암호화
		
		String se_pw = new String(se);
		System.out.println(se_pw);
		System.out.println(new String(se));	//byte 배열을 문자열로 변환
		
		return null;
	}
	
	//Base64 복호화 기술
	@GetMapping("/se2.do")
	public String se2() {
		String pw = "YTEyMzU0Ng==";
		//byte pw2[] = pw.getBytes();
		Decoder dc = Base64.getDecoder();
		byte repw[] = dc.decode(pw);
		System.out.println(new String(repw));
		
		return null;
	}
	
	@GetMapping("/se3.do")
	public String se3() {
		String pw = "a123456";
		try {
			//md5 : 암호화 기술
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(pw.getBytes());
			byte[] repw = md5.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : repw) {
				//문자 포맷 : %x - 소문자 + 숫자, %X - 대문자 + 숫자
				//%02x : 2진수 형태의 소문자 + 숫자 암호화
				//%05x : 5진수 형태의 소문자 + 숫자 암호화
				String word = String.format("%x", w);
				sb.append(word);
			}
			System.out.println(sb);
			
		} catch (Exception e) {
			System.out.println("해당 문자는 암호화가 불가능합니다");
		}
		
		return null;
	}
	
	//SHA1 암호화 기술
	@GetMapping("/se4.do")
	public String se4() {
		try {
			String pass = "a123456";
			//MD5 -> SHA1 -> SHA-224 -> SHA-256 -> SHA-512 -> SHA3-224 : 길이가 점점 길어진다
			//(1)base64로 암호화 -> (2)MD5로 암호화
			//(1)MD5로 암호화 -> (2)SHA1로 암호화 -> DB 저장하는 중복 암호화 방식을 많이 사용한다
			MessageDigest sha = MessageDigest.getInstance("SHA-512");
			sha.update(pass.getBytes());
			byte[] m = sha.digest();
			StringBuilder sb = new StringBuilder();
			for(byte w : m) {
				String code = String.format("%x", w);
				sb.append(code);
			}
			System.out.println(sb);
			
		} catch (Exception e) {
			System.out.println("해당 문자는 암호화가 불가능합니다");
		}
		
		return null;
	}
	
}
