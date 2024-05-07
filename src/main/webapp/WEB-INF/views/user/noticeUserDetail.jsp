<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세보기</title>
    <!-- 필요한 CSS 파일들을 로드합니다 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>공지사항 상세보기</h1>
        <div>
            <p><strong>번호:</strong> ${notice.notiNo}</p>
            <p><strong>분류:</strong> ${notice.notiImpt}</p>
            <p><strong>제목:</strong> ${notice.notiTitle}</p>
            <p><strong>작성자:</strong> ${notice.usernick}</p>
            <p><strong>내용:</strong> ${notice.notiContent}</p>
            <p><strong>작성일:</strong> <fmt:formatDate value="${notice.notiDate}" pattern="yyyy.MM.dd HH:mm" /></p>
            <p><strong>조회수:</strong> ${notice.notiHit}</p>
        </div>
        <br>
        <a href="noticeList" class="btn btn-secondary">목록</a>
    </div>
</body>
</html>
