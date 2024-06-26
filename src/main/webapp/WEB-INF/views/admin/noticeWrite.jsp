<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 작성 폼</title>
</head>
<body>
    <h1>공지사항 작성 폼</h1>
    <form action="/noticeWriteResult" method="post">
    
    	<input type="hidden" name="notiHit" value="0"/>
    	<input type="hidden" name="category" value="n"/>
    	<input type="hidden" name="username" value="${username}"/>
           
      
        
        <!-- 작성자 입력 -->
        <label for="usernick">작성자 : </label>
        <input type="text" id="usernick" name="usernick" value="${usernick}">
        <br><br>
        
        <!-- 제목 입력 -->
        <label for="notiTitle">제목 : </label>
        <input type="text" id="notiTitle" name="notiTitle">
        <br><br>
        
        <!-- 중요표시 선택 -->
        <label>중요도 : </label>
        <label><input type="radio" name="notiImpt" value="중요">중요</label>
        <label><input type="radio" name="notiImpt" value="일반">일반</label>
        <br><br>
        
        <!-- 내용 입력 -->
        <label for="notiContent">내용 : </label><br>
        <textarea id="notiContent" name="notiContent" rows="4" cols="50"></textarea>
        <br><br>
        
        <!-- 제출 버튼 -->
        <input type="submit" value="작성 완료">
     	<!-- 취소 버튼 -->
        <button type="button" onclick="window.location.href='noticeList'" class="btn btn-secondary">취소</button>    </form>
</body>
</html>
