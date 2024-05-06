<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:1문의 상세보기</title>
    <!-- 필요한 CSS 파일들을 로드합니다 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>1:1문의 상세보기</h1>
        <div>
            <p><strong>번호:</strong> ${notice.notiNo}</p>
            <p><strong>제목:</strong> ${notice.notiTitle}</p>
            <p><strong>작성자:</strong> ${notice.usernick}</p>
            <p><strong>내용:</strong> ${notice.notiContent}</p>
            <p><strong>상태:</strong> ${notice.notiAt}</p>
        </div>
        <br>
        <a href="askList" class="btn btn-secondary">목록</a>
        <a href="replyWrite?id=${id}" class="btn btn-secondary">답변하기</a>
    </div>
</body>
</html>
