<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>관리자 로그인</h1>
    <hr>
    <form action="/loginProc" method="post" name="loginForm">
        <input id="username" type="text" name="username" placeholder="id"/><br>
        <input id="password" type="password" name="password" placeholder="password"/>
        <input type="submit" value="login"/>
    </form>
    

</body>
</html>