<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:1문의 작성 폼</title>
</head>
<body>
    <h1>1:1문의 글쓰기</h1>
    <form action="/askWriteResult" method="post">
    
    	<input type="hidden" name="notiAt" value="미답변"/>
    	<input type="hidden" name="username" value="${username}"/>
   		<input type="hidden" name="notiHit" value="0"/>
        <input type="hidden" id="category" name="category" value="a">
        
        <!-- 작성자 입력 -->
        <label for="notiWriter">작성자 : </label>
        <input type="text" id="usernick" name="usernick" value="${usernick}">
        <br><br>
        
        <!-- 제목 입력 -->
        <label for="notiTitle">제목 : </label>
        <input type="text" id="notiTitle" name="notiTitle">
        <br><br>
        
        <!-- 내용 입력 -->
        <label for="notiContent">내용 : </label><br>
        <textarea id="notiContent" name="notiContent" rows="4" cols="50"></textarea>
        <br><br>
        
        <!-- 제출 버튼 -->
        <input type="submit" value="작성 완료">
     	<!-- 취소 버튼 -->
        <button type="button" onclick="window.location.href='myqnaList'" class="btn btn-secondary">취소</button>    </form>
</body>
</html>
