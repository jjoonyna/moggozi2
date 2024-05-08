<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/id_pw_find.css">
<meta name="viewpoirt" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>회원 탈퇴 및아이디 패스워드 찾기</title>
</head>
<body>
	<div class="container" id="container">
		<div class="form-container sign-up">
			<form method="post" action="pw_ok">
				<h1>비번 찾자</h1>
			<span>다음을 기입해 주세요!</span>
                <input name="username" id="username" class="input_box" placeholder="아이디를 입력하세요" />
                <input name="userph" id="userph" class="input_box" placeholder="전화번호를 입력하세요" />
                <button type="submit">비번찾아주세요!  </button>
            </form>
		</div>
		 <div class="form-container sign-in">
            <form method="post" action="id_ok">
                <h1>나의 아이디가 뭘까?!</h1>
                <span>정보를 입력해 주세요옷!</span>
                <input name="nomalname" id="nomalname" placeholder="이름을 입력하세요">
                <input name="userph" id="userph" placeholder="핸드폰번호를 입력하세요 ">
                <button type="submit">아이디 찾아줘</button>
            </form>
	</div>
		
	    <div class="toggle-container">
            <div class="toggle">
					<div class="toggle-panel toggle-left">
						<h1>아이디를 찾고 싶어요?!</h1>
						<p>아래를 클릭해주세요</p>
						<button class="hidden" id="pwfind">아이디 찾기</button>
					</div>
					<div class="toggle-panel toggle-right">
						<h1>비밀번호를 찾고 싶어요?!</h1>
						<p>그럼 아래를 클릭해주세요</p>
						<button class="hidden" id="idfind">비밀번호 찾기</button>
					</div>
			</div>
        </div>
     </div>
     <script src="./js/id_pw_find.js"></script>
</body>
</html>