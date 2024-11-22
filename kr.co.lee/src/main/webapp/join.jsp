<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Date today = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddhhmmss");	//년월일시분초를 원하는 형태로 변경
%>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <title>회원가입</title>
    
</head>
<style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>
<link href="form-validation.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="col-md-8 order-md-1">
    <h4 class="mb-3">MEMBER-SHIP</h4>
    <form id="f" class="needs-validation" method="post" action="./joinok.do" onsubmit="return member_check()">
    <!-- 휴대폰 번호 전송 : xxx-xxxx-xxxx -->
    <input type="hidden" name="mhp" value="">
      <div class="mb-3">
        <div class="mb-3">
          <label>아이디</label>
          <div>
          <ul class="ulcss">
	          <li><input type="text" name="mid" class="form-control" placeholder="" value=""></li>
	          <li><button type="button" class="btn btn-primary" onclick="idcheck()">중복확인</button></li>
          </ul>
          </div>
        </div>
        <br>
        <div class="mb-3">
          <label>고객명</label>
          <input type="text" name="mname" class="form-control" placeholder="" value="">
        </div>
        <div class="mb-3">
          <label>패스워드</label>
          <input type="password" name="mpass" class="form-control" placeholder="" value="">
        </div>
        <div class="mb-3">
          <label>패스워드 확인</label>
          <input type="password" id="mpass2" class="form-control" placeholder="동일한 패스워드를 입력하세요" value="">
        </div>
      </div>

      <div class="mb-3">
        <label for="username">연락처 입력</label>
        <div class="input-group" style="align-items: center;">
          <select class="custom-select d-block w-10" name="telcorp">
            <option value="">통신사</option>
            <option value="SKT">SKT</option>
            <option value="KT">KT</option>
            <option value="LGT">LGT</option>
          </select>&nbsp;&nbsp;
          <input type="text" id="hp1" class="form-control" maxlength="3" placeholder="" style="width: 5%;">&nbsp; - &nbsp;
          <input type="text" id="hp2" class="form-control" maxlength="4" placeholder="" style="width: 15%;">&nbsp; - &nbsp;
          <input type="text" id="hp3" class="form-control" maxlength="4" placeholder="" style="width: 15%;">
        </div>
      </div>

      <div class="mb-3">
        <label for="email">이메일</label>
        <input type="email" name="memail" id="email" class="form-control" placeholder="you@example.com" value="">
      </div>

      <div class="mb-3">
        <label>주소</label>
        <div class="col-md-8" style="margin-left: -3%;">
          <ul class="ulcss">
          <li>
          <input type="text" name="mpost" class="form-control" id="mpost" placeholder="" value="" readonly>
          </li>
          <li><button type="button" class="btn btn-primary" onclick="sample2_execDaumPostcode()">주소찾기</button></li>
          </ul>
          </div>
      </div>
      
      <div class="mb-3">
        <label>도로명 주소</label>
        <input type="text" id="mroad" name="mroad" class="form-control" placeholder="" readonly>
      </div>
      <div class="mb-3">
        <label>상세 주소</label>
        <input type="text" name="maddr" class="form-control" placeholder="">
      </div>
      <div class="row">

      </div>
      <hr class="mb-4">
      <div class="custom-control custom-checkbox">
        <input type="checkbox" class="custom-control-input" id="sms" name="magree1" value="Y">
        <label class="custom-control-label" for="sms">SMS 수신동의</label>
      </div>
      <div class="custom-control custom-checkbox">
        <input type="checkbox" class="custom-control-input" id="email" name="magree2" value="Y">
        <label class="custom-control-label" for="email">EMAIL 수신동의</label>
      </div>
      <hr class="mb-4">
      <button class="btn btn-primary btn-lg btn-block" type="submit">회원가입</button>
    </form>
  </div>
</div>

<footer class="my-5 pt-5 text-muted text-center text-small">
  <p class="mb-1">&copy; 2024 Sample MEMBER-SHIP</p>
</footer>
</div>

<!-- 우편번호 검색 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script src="./join.js?v=<%=sf.format(today)%>"></script>

</body>
</html>
