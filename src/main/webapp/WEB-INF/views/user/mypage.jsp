<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/resources/header.jsp" />
<meta charset="UTF-8">
<title>모꼬지</title>
<link rel="stylesheet" type="text/css" href="./css/login.css">
<link rel="stylesheet" type="text/css" href="./css/mypage.css">
<script type="text/javascript" src="./js/mypage.js"></script>
<jsp:include page="/resources/gnb.jsp" />
</head>
<body>
	<main>
		<div class="container">
			<!-- 로그인 창 -->
			<aside>
	    	<div class="login-wrapper">
		        <h2 class="login-text">Login</h2>
		        <form method="post" action="/login" id="login-form">
		            <input type="text" name="user_id" placeholder="ID">
		            <input type="password" name="user_pw" placeholder="PW">
		            <input class="hover-color" type="submit" value="Login">
		        </form>
		            <label for="login-menu" class="login-menu">
		            	 <a href="#" class="hover-color textsmall">ID</a> <div style="font-size: 13px;"> / </div> 
		            	 <a href="#" class="hover-color textsmall">PW 찾기</a> <div style="font-size: 13px;"> / </div> 
		            	 <a href="#" class="hover-color textsmall">회원가입</a>
		            </label>
	    	</div>
		
		
			<!-- 사이드 카테고리 -->
			<div class="table-container">
				<table>
				    <tr>
				        <td>
				            <a href="#" class="hover-color">모일꼬지?</a>
				        </td>
				    </tr>
				    <tr>
				        <td>
				            <a href="#" class="hover-color">알림마당</a>
				        </td>
				    </tr>
				    <tr>
				        <td>
				            <a href="mypage" class="hover-color">마이페이지</a>
				        </td>
				    </tr>
				</table>
			</div>
		</aside>
		
		
			<!-- 내 정보 -->
			<div class="mypage">
				<div class="mypage-btn">
					<button class="myinfo-btn" onClick="myinfo()" style="background-color: #3EFA97">내 정보</button>
					<button class="mymoim-btn" onClick="mymoim()">내 모임</button>
					<button class="myboard-btn" onClick="myboard()">내 게시글</button>
				</div>
				
				<div class="mypage-myinfo">

					<h3>${username }님 환영합니다</h3>
					<div class=mypage-myinfo-table>
						<table class="myinfo-table">
							<caption>내 활동</caption>
							<tr>
								<td><br>모임 수</td>
								<td><br>0</td>
							</tr>
							<tr>
								<td><br>게시글 수</td>
								<td><br>0</td>
							</tr>
						</table>				
					</div>

				</div>
			</div>
			
			
			
			
			
			
		</div>
		
	</main>
		<jsp:include page="/resources/footer.jsp" />
</body>
</html>