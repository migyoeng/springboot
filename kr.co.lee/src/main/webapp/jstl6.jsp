<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
JSTL : Controller에서 각각 import된 page 모두 전달이 가능하다
**Controller에서 Model, ModelAndView를 활용하면 외부 import된 jsp에서 jstl로 모든 결과값을 출력할 수 있다
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 외부 페이지 로드</title>
</head>
<body>
<cr:import url="./top.jsp"></cr:import><!-- JSTL의 import 태그 : 외부 파일을 가져올 수 있는 속성 -->
<cr:import url="./notice.jsp"></cr:import>
</body>
</html>