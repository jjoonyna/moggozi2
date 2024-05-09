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
	<link rel="stylesheet" type="text/css" href="./css/myinfoupdate.css">
	    <link rel="icon" type="image/x-icon" href="./images/m.png">
	
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>모꼬지</title>
<script type="text/javascript" src="./js/mypage.js"></script>
<jsp:include page="/resources/gnb.jsp" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>

<body>
    <script src="../js/main.js"></script>

	<main>
	        <!-- 메인 배너 -->
	        <div class="main-banner">
	        </div>
		
		<div class="container">
			<!-- 로그인 창 -->
			<aside>
		    	<div class="login-wrapper">
		            <h2 class="login-text">회원 정보</h2>
		            <p>어서오세요!<br>${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p><br>
		            <a href="mypage">마이페이지</a>
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
                                    <a href="#" onclick="return checkCurrentPage()" >내 정보 수정</a>
                                    <a href="mypwdchange">비밀번호 변경</a>
                                    <a href="mydelete">회원 탈퇴</a>
                                    <!-- 사이드바 줄 -->
                                    <hr class="sidebar-divider">
                                    <a href="mymoim">내 모꼬지</a>
                                    <a href="myqnaList">문의 내역</a>
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
                                <a class="hover-color" href="boardlist">모임니당</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color">알림마당</a>
                                <div class="dropdown-content" id="myDropdown">
                                    <a href="noticeUserList">공지사항</a>
                                    <a href="askWrite">1:1 문의</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
			
			
			<!-- hot 모꼬지! -->
			<div class="hot-mocozi">
					<table>
					
					</table>
				</div>
			</aside>
			
			
			
			
		
			
			
			
			<nav>
                <h1 class="sub-title">내 정보 수정</h1>
				<!-- 내 정보 -->
				<section>
				    <div class="cards">
				        <div class="mypage-mymoim">
							<form method="post" action="/infoUpdate" class="info-form">
						    <table>
						        <tr>
						            <td><label for="username" class="label-username">ID</label></td>
						            <td><input type="text" name="username" id="username" value="${username}" readonly="readonly" class="input-username"></td>
						        </tr>
						        <tr>
						            <td><label for="usernick" class="label-usernick">닉네임</label></td>
						            <td><input type="text" name="usernick" id="usernick" value="${usernick}" readonly="readonly" class="input-usernick"></td>
						        </tr>
						        <tr>
						            <td><label for="password" class="label-password">PW</label></td>
						            <td><input type="password" name="password" id="password" class="input-password"></td>
						        </tr>
						        <tr>
						            <td><label for="userph" class="label-userph">전화번호</label></td>
						            <td><input type="text" name="userph" id="userph" value="${userph}" class="input-userph"></td>
						        </tr>
						        <tr>
						            <td><label for="useryear" class="label-useryear">출생년도</label></td>
						            <td><input type="text" name="useryear" id="useryear" value="${useryear}" readonly="readonly" class="input-username"></td>
						        </tr>
						        <tr>
						            <td><label for="useremail" class="label-useremail">이메일</label></td>
						            <td><input type="text" name="useremail" id="useremail" value="${useremail}" class="input-useremail"></td>
						        </tr>
						        <tr>
						            <td><label for="userzip" class="label-userzip">우편번호</label></td>
						            <td><input type="text" id="userzip" name="userzip" value="${userzip}" class="input-userzip"></td>
						            <td><input type="button" value="우편번호검색" onclick="openDaumPostcode()" class="button-search"></td>
						        </tr>
						        <tr>
						            <td><label for="useraddress1" class="label-useraddress1">주소</label></td>
						            <td><input type="text" name="useraddress1" id="useraddress1" value="${useraddress1}" readonly onclick="post_search()" class="input-useraddress1"></td>
						        </tr>
						        <tr>
						            <td><label for="useraddress2" class="label-useraddress2">상세주소</label></td>
						            <td><input type="text" name="useraddress2" id="useraddress2" value="${useraddress2}" class="input-useraddress2"></td>
						        </tr>
						    </table>
					    	<input type="submit" value="수정" class="button-submit">
							</form>
				        </div>
				    </div>
				</section>
			</nav>
			
			
			
			
			<!-- 내 정보 -->
			<div class="mypage">
				
				
				<div class="mypage-mymoim">
				



					
					
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/resources/footer.jsp" />

</body>
</html>