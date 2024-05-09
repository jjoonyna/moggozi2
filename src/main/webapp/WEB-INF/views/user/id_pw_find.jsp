<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <jsp:include page="/resources/commons/header.jsp" />--%>
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="../css/id_pw_find.css">
<script type="text/javascript" src="/resources/js/find.js"></script>
<title>비밀번호 찾기</title>
</head>
<body>


	<div class="container" id="container">
		<div class="form-container sign-up">
			<form method="post" action="pw_ok">
				<h1>비밀번호 찾기</h1>
                <span>*가입 시 입력하셨던 이메일로 보내드립니다.</span>
                <input name="username" id="username" class="input_box" placeholder="아이디를 입력하세요" />
                <input name="userph" id="userph" class="input_box" placeholder="전화번호를 입력하세요" />
                <button type="submit">찾기  </button>
                <a href="signup">이미 알아요, 로그인하러 가기</a>
            </form>
		</div>

		<div class="form-container sign-in">
			<form method="post" action="id_ok">
                <h1>아이디 찾기</h1>
                <span>*가입 시 입력하셨던 이메일로 보내드립니다.</span>
                <input name="nomalname" id="nomalname" placeholder="이름을 입력하세요">
                <input name="userph" id="userph" placeholder="핸드폰번호를 입력하세요 ">
                <button type="submit">찾기</button>
                <a href="signup">이미 알아요, 로그인하러 가기</a>
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
						<p>아래를 클릭해주세요</p>
						<button class="hidden" id="idfind" >비밀번호 찾기</button>
					</div>
			</div>
        </div>
	</div>
     <script src="./js/find.js"></script>
     <script src="./js/usercheck.js"></script>


</body>
</html>