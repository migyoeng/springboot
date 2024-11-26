package kr.co.lee;

import java.security.MessageDigest;

import org.springframework.stereotype.Service;
//패스워드 암호화 하는 interface
public interface security {
	//접근 제어자가 default 일 경우에만 메소드 body 구문을 작성할 수 있다
	//접근 제어자를 생략하거나 public 인 경우 body 구문이 없는 메소드 이름만 생성 가능
	default StringBuilder secode_sha1(String passwd) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("SHA1");
		md5.update(passwd.getBytes());
		byte[] repw = md5.digest();
		StringBuilder sb = new StringBuilder();
		for(byte w : repw) {
			String word = String.format("%x", w);
			sb.append(word);
		}
		return sb;
	}

	default StringBuilder secode(String passwd) throws Exception{
		System.out.println(passwd);
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(passwd.getBytes());
		byte[] repw = md5.digest();
		StringBuilder sb = new StringBuilder();
		for(byte w : repw) {
			String word = String.format("%x", w);
			sb.append(word);
		}
		return sb;
	}
}
