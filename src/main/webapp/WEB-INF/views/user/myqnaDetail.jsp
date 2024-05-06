<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

		<!-- 문의 정보 출력 -->
		<c:choose>
		    <c:when test="${!empty notice}">
		        <h3>문의</h3>
		        <p>작성자: ${notice.usernick}</p>
		        <p>작성일: <fmt:formatDate value="${notice.notiDate}" pattern="yyyy.MM.dd HH:mm" />
		        </p>
		        <p>내용: ${notice.notiContent}</p>
		    </c:when>
		    <c:otherwise>
		        <p>문의 정보를 찾을 수 없습니다.</p>
		    </c:otherwise>
		</c:choose>
		
		<!-- 답변 정보 출력 -->
		<c:choose>
		    <c:when test="${!empty replies}">
		        <h3>답변</h3>
		        <c:forEach items="${replies}" var="reply">
		            <p>작성자: ${reply.usernick}</p>
		            <p>작성일: <fmt:formatDate value="${reply.replyDate}" pattern="yyyy.MM.dd HH:mm" /></p>
		            <p>내용: ${reply.replyContent}</p>
		        </c:forEach>
		    </c:when>
		    <c:otherwise>
		        <p>답변이 없습니다.</p>
		    </c:otherwise>
		</c:choose>

		



        
        <a href="myqnaList" class="btn btn-secondary">목록</a>
    </div>
</body>
</html>
