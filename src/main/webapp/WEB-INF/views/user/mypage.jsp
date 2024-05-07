<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>모꼬지</title>
<link rel="stylesheet" type="text/css" href="./css/mypage.css">
<script type="text/javascript" src="./js/mypage.js"></script>
	<jsp:include page="/resources/gnb.jsp" />
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
    <script src="../js/main.js"></script>


	<main>
		<!-- 메인 배너 -->
		<div class="main-banner">
			<h1>굉장히 엄청난 타이포 그래피</h1>
		</div>
		
		<div class="container">
			<!-- 로그인 창 -->
			<aside>
	    	<div class="login-wrapper">
			            <h2 class="login-text">회원 정보</h2>
			            <p>어서오세요! ${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p>
			            <a href="#" onclick="return checkCurrentPage()">마이페이지</a>
			            <a href="logout">로그아웃</a>
			</div>

		
		
 
            <!-- 사이드 카테고리 -->
            <div class="table-container">
                <table>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color">마이페이지</a>
                                <div class="dropdown-content" id="myDropdown">
                                    <a href="myinfoupdate?usernick=${usernick}">내 정보 수정</a>
                                    <a href="mypwdchange?usernick=${usernick}">비밀번호 변경</a>
                                    <a href="mydelete?usernick=${usernick}">회원 탈퇴</a>
                                    <!-- 사이드바 줄 -->
                                    <hr class="sidebar-divider">
                                    <a href="#" onclick="return preparingPage()">모임 목록</a>
                                    <a href="myqnaList?usernick=${usernick}">문의 내역</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="main" class="hover-color">모일꼬지?</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color">알림마당</a>
                                <div class="dropdown-content" id="myDropdown">
                                    <a href="noticeUserList?usernick=${usernick}">공지사항</a>
                                    <a href="askWrite?usernick=${usernick}">1:1 문의</a>
                                    <!-- 사이드바 줄 -->
                                    <hr class="sidebar-divider">
                                    <a href="#" onclick="return preparingPage()">모임후기</a>
                                    <a href="#" onclick="return preparingPage()">커뮤니티</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
			
			
			<!-- hot 모꼬지! -->
			

			
			
			</aside>
			
			
			<!-- 내 정보 -->
			<div class="mypage">
				
				
				<div class="mypage-mymoim">
				<h3>내 정보</h3>
						<br>ID<input type="text" name="username" value="${ username}" readonly="readonly">
						<br>닉네임<input type="text" name="usernick" value="${usernick }" readonly="readonly">
						<br>PW<input type="password" name="password">
						<br>전화번호<input type="text" name="userph" value="${userph }">
						<br>출생년도<input type="text" name="useryear" value="${useryear }">
						<br>이메일<input type="text" name="useremail" value="${useremail }">
						<br>우편번호<input type="text" id="userzip" name="userzip" value="${userzip }">
						<br>주소<input type="text" name="useraddress1" value="${useraddress1 }" readonly onclick="post_search()" id="useraddress1" >
						<br>상세주소<input type="text" name="useraddress2" value="${useraddress2 }" >
					
					
				</div>
			</div>
	</div>

	</main>
	
	
	<jsp:include page="/resources/footer.jsp" />

</body>
</html>