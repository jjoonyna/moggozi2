<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./js/snslogin.js"></script>
<link rel="stylesheet" href="./css/snslogin.css">
</head>
<body>
				<span id="sns_login">
					<button id="kakao_login_ok" onClick="kakao()">
						<img src="./icons/kakao.png" alt="kakao">
					</button>
					<button id="google_login_ok" onClick="google()">
						<img src="./icons/google.png" alt="goole">
					</button>
					<button id="naver_login_ok" onClick="naver()">
						<img src="./icons/naver.png" alt="naver">
					</button>
				</span>
</body>
</html>