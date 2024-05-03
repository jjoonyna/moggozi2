<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원 가입</title>
</head>
<body>

<form action="/joinProc" method="post" name="joinForm">
    <input type="text" name="username" placeholder="ID"/>
    <input type="text" name="usernick" placeholder="닉네임"/>
    <input type="password" name="password" placeholder="password"/>
		<input type="submit" value="Join"/>
</form>


</body>
</html>