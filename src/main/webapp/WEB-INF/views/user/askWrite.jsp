<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="../css/boardform.css">
    <link rel="icon"       type="image/x-icon" href="./images/m.png">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>모꼬지</title>
<jsp:include page="/resources/gnb.jsp" />
</head>
<body>
	<main>
			<!-- 메인 배너 -->
			<div class="main-banner">
			</div>
		
		<div class="container">
				<!-- 로그인 창 -->
			<aside>
	    	<div class="login-wrapper">
			    <c:choose>
			        <c:when test="${!empty username}">
			            <h2 class="login-text">회원 정보</h2>
			            <p>어서오세요! ${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p>
			            <a href="mypage?usernick=${usernick}">마이페이지</a>
			            <a href="logout">로그아웃</a>
			        </c:when>
			        <c:otherwise>
			            <h2 class="login-text">로그인</h2>
			            <form action="/loginProc" method="post" name="loginForm">
			                <input type="text" name="username" placeholder="아이디">
			                <input type="password" name="password" placeholder="비밀번호">
			                <input class="hover-color" type="submit" value="로그인">
			            </form>
			            <label for="login-menu" class="login-menu">
			                <a href="#" class="hover-color textsmall">아이디</a> <div style="font-size: 13px;"> / </div> 
			                <a href="#" class="hover-color textsmall">비밀번호 찾기</a> <div style="font-size: 13px;"> / </div> 
			                <a href="#" class="hover-color textsmall">회원가입</a>
			            </label>
			        </c:otherwise>
			    </c:choose>
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
                                    <a href="mymoim">모임 목록</a>
                                    <a href="myqnaList?usernick=${usernick}">문의 내역</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" onclick="return preparingPage()" class="hover-color">모일꼬지?</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color" href="boardlist?usernick=${usernick}">모임니당</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color">알림마당</a>
                                <div class="dropdown-content" id="myDropdown">
                                    <a href="noticeUserList?usernick=${usernick}">공지사항</a>
                                    <a href="askWrite?usernick=${usernick}">1:1 문의</a>
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
			
		
			<!-- 글작성 폼 -->
			<nav>
			<h1 class="sub-title">문의 하기</h1>
					<section>
						<div class="cards">
							<form action="/askWriteResult" method="post">
							    	<input type="hidden" name="notiAt" value="미답변"/>
							    	<input type="hidden" name="username" value="${username}"/>
							    	<input type="hidden" name="usernick" value="${usernick}"/>
							   		<input type="hidden" name="notiHit" value="0"/>
							        <input type="hidden" id="category" name="category" value="a">
							
							
								<table class="innerTable" border=1 width=500 align=center>
									<tr>
										<td>								
										    <span>제목</span>
										    <input type="text" name="notiTitle" class="subjectArea" required="required">
										</td>
									</tr>
									<tr>
										<td>
									    	<span class="contentTitle">내용</span>
									    	<textarea name="notiContent" class="contentArea" required="required"></textarea>
									    </td>
									</tr>
									<tr>
										<td>
											<input type="hidden" name="boardDate" value="<%= new java.sql.Timestamp(System.currentTimeMillis()) %>">
											
										</td>
									</tr>
	
								</table>
								<div class="btn">
								<input type="submit" value="등록" class="insBtn">
								<input type="button" value="취소" class="cancelBtn" onClick="goBack()">
							</div>
							</form>
						</div>
					</section>
				</nav>
			</div>
			
		
		
		
	<script>
	  function goBack() {
	    window.history.back();
	  }
	</script>
		
	</main>
	   <jsp:include page="/resources/footer.jsp" />
</body>
</html>