<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호  찾기 결과</title>
</head>
<body>
    <h1>비밀번호 찾기 결과</h1>
    <c:if test="${not empty message}">
        <p><strong>${message}</strong></p>
    </c:if>
    <c:if test="${empty newPassword}">
        <p>일치하는 아이디가 없습니다.</p>
    </c:if>
</body>
</html>