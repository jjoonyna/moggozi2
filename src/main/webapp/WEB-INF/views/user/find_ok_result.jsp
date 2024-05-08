<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 조회 결과</title>
</head>
<body>
    <h1>계정 조회 결과</h1>
    <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>
    <c:if test="${empty message}">
        <p>일치하는 회원 정보가 없습니다. 아이디를 생성해주세요!</p>
    </c:if>
</body>
</html>