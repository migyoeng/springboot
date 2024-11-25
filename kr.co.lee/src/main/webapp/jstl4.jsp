<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/*
	응용 문제 :
	각각의 1차 배열 값을 2차 배열로 입력되도록 한다
	
	1번 데이터: 홍길동, 강감찬, 이순신, 유관순
	2번 데이터: A형, O형, AB형, A형

	out.print 출력 예시)
	{{"홍길동", "A형"},{"강감찬", "O형"},{"이순신", "AB형"},{"유관순", "A형"}}
*/
String data1[] = new String[]{"홍길동", "강감찬", "이순신", "유관순"};
String data2[] = new String[]{"A형", "O형", "AB형", "A형"};

//각각의 1차 배열 -> 2차 배열로 변환 : 2차 원시 배열, 2차 클래스 배열

//1) 2차 클래스 배열로 변환
//ArrayList<String, String> al2 = new ArrayList<String,String>();
ArrayList<ArrayList<String>> al2 = new ArrayList<ArrayList<String>>();
//이름(원시 배열 -> 클래스 배열)
ArrayList<String> data_c1 = new ArrayList<String>(Arrays.asList(data1));
//혈액형
ArrayList<String> data_c2 = new ArrayList<String>(Arrays.asList(data2));

//out.print(data_c1);
for(int i=0; i<data_c1.size(); i++){
	//빈 클래스 배열을 이용하여 2차 배열로 값 저장
	ArrayList<String> redata = new ArrayList<>();
	redata.add(data_c1.get(i));
	redata.add(data_c2.get(i));
	al2.add(redata);
}
//out.println(al2);


//2) 2차 원시 배열로 변환
String data3[][] = new String[data1.length][2];	//2차 배열의 전체 크기
for(int i=0; i<data1.length; i++){
	data3[i][0] = data1[i];
	data3[i][1] = data2[i];
}
/* 
data3[0][0] = "홍길동";
data3[0][1] = "A형";
data3[1][0] = "강감찬";
data3[1][1] = "O형";
data3[2][0] = "이순신";
data3[2][1] = "AB형";
data3[3][0] = "유관순";
data3[3][1] = "A형";
*/

//out.print(Arrays.toString(data3[0]));
//out.print(Arrays.deepToString(data3));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기본 문법 4 - 2차 배열</title>
</head>
<body>

	<p>사용자별 혈액형(원시배열로 출력)</p>
	<ol>
	<cr:forEach var="person" items="<%=data3 %>">
		<li>고객명 : ${person[0]}, 혈액형 : ${person[1]}</li>
	</cr:forEach>
	</ol>
	
	<br><hr><br>
	
	<p>사용자별 혈액형(클래스 배열로 출력)</p>
	<ol>
	<cr:forEach var="person" items="<%=al2 %>">
		<li>고객명 : ${person.get(0)}, 혈액형 : ${person.get(1)}</li>
	</cr:forEach>
	</ol>
</body>
</html>