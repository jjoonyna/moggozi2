<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 조회 결과</title>
<link rel="stylesheet" href="../css/style.css"> <!-- 필요한 CSS 파일을 여기에 링크 -->
</head>
<body>
	<div class="container" id="container">
		<div class="form-container result-card">
		    <h1 style="color: black;">계정 조회 결과</h1>
			    <c:if test="${not empty message}">
			        <p style="color: black;"><strong>${message}</strong></p>
			    	<a href="signup">로그인하러 가기</a>
			    </c:if>
			    <c:if test="${empty message}">
			        <p>일치하는 회원 정보가 없습니다. 아이디를 생성해주세요!</p>
			   		<a href="signup">가입하기</a> <!-- 결과에 따라 이동할 수 있는 링크를 추가 -->
			    </c:if>
		</div>
	</div>
</body>
</html>
