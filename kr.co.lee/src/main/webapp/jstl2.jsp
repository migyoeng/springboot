<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL 기본 문법 2 - if문 및 choose문 + when문</title>
</head>
<body>
	<!-- if문 선언 및 test가 조건을 사용하는 속성 : var (true, false) 결과 형태-->

	<cr:if test="${5 > 10}" var="result">
	5가 큽니다.
	</cr:if>
	결과 : ${result}
	<br><br>
	
	<cr:if test="${5 < 10}" var="result">
	10이 큽니다.
	</cr:if>
	<br><br>
	
	결과 : ${result}
	<br><br>
	
	<cr:set var="no" value="100"></cr:set>
	<cr:if test="${no < 100}">
	100 미만의 값입니다.
	</cr:if>
	
	<cr:if test="${no > 100}">
	100 이상의 값입니다.
	</cr:if>
	
	<cr:if test="${no == 100}">
	100입니다.
	</cr:if>
	<br><br>
	
	<cr:set var="mid" value="jeong123"></cr:set>
	<!-- eq : equals 의 약어 : 일치한다면 -->
	<!-- ne : not equals 다르다면 -->
	<!-- 문자 검토 시 ''를 이용하여 검트 -->
	<cr:if test="${mid eq 'jeong'}">
		로그인 하셨습니다
	</cr:if>
	<cr:if test="${mid ne 'jeong'}">
		가입되지 않은 사용자입니다
	</cr:if>
	<br><br>
	
	<!-- 
	조건식의 그룹 태그 : choose
	*choose 내 절대 html 주석을 사용하지 못한다 단, jsp 주석은 사용 가능하다*
	
	eq, ne, or(||), and(&&)
	otherwise : else 와 같은 성격을 지닌다
	 -->
	 <cr:set var="no2" value="z"></cr:set>
	 <cr:choose>
	 	<cr:when test="${no2 eq 'a'}">
	 		신용카드 결제
	 	</cr:when>
	 	<cr:when test="${no2 eq 'b'}">
	 		계좌이체
	 	</cr:when>
	 	<cr:when test="${no2 eq 'c'}">
	 		무통장 입금
	 	</cr:when>
	 	<cr:otherwise>
	 		포인트 결제
	 	</cr:otherwise>
	 </cr:choose>
	
	
	
	
	
	
</body>
</html>