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
	<jsp:include page="/resources/gnb.jsp" />
</head>
<body>
	<main>
		<!-- 메인 배너 -->
		<div class="main-banner">
			<h1>굉장히 엄청난 타이포 그래피</h1>
		</div>
		
		<div class="container">
			<!-- 로그인 창 -->
			<aside>
	    	<div class="login-wrapper">
			    <c:choose>
			        <c:when test="${!empty usernick}">
			            <h2 class="login-text">로그인 상태</h2>
			            <p>어서오세요! ${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p>
			            <a href="mypage?usernick=${usernick}">마이페이지</a>
			            <a href="logout">로그아웃</a>
			        </c:when>
			        <c:otherwise>
			        	<div class="table-container">
			            <h2 class="login-text">로그인</h2>
			            <form action="/loginProc" method="post" name="loginForm">
			                <input type="text" name="username" placeholder="아이디">
			                <input type="password" name="password" placeholder="비밀번호">
			                <input class="hover-color" type="submit" value="로그인">
			            </form>
			            </div>
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
                                    <a href="#" onclick="return preparingPage()">모임 목록</a>
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
			
	
	
	        <nav>
			<!-- 검색 폼 -->
	        <form method="get" action="/search">
			    <input type="text" name="keyword" class="keyword" id="keyword" value="${keyword }" placeholder="검색어를 입력하세요">
			    <select class="category" id="category" name="category" onchange="search()">
			        <option value=""	<c:if test="${category==''}">selected="selected" </c:if>>카테고리선택</option>
					<option value="운동"	<c:if test="${category=='운동'}">selected="selected" </c:if>>운동</option>
					<option value="게임"	<c:if test="${category=='게임'}">selected="selected" </c:if>>게임</option>
			    </select>
			    <button type="submit" class="searchBtn">검색</button>
			</form>
			
			<section>
<!-- 게시판 목록 -->
					<div class="cards">
					<c:forEach items="${mokkojiList}" var="mokkoji">
					  <div class="card">
					    <div class="card__image-holder">
					      <img class="card__image" src="${mokkojiImages}" alt="모꼬지이미지" />
					    </div>
					    <div class="card-title">
					      <a href="#" class="toggle-info btn">
					        <span class="left"></span>
					        <span class="right"></span>
					      </a>
					      <h2>${mokkojiTitle}
					          <small>닉네임 : ${usernick}</small>
					          </h2>
					    </div>
						    <div class="card-flap flap2">
	                     	 <div class="card-actions" id="buttonContainer">
	                      <%--     <a href="javascript:join('${mokkojiNo}','${mokkojiTitle}','${usernick}')"
	                         class="btn" autocomplete="off" >참가하기</a> --%>
	    
	                        <a href="/video" class="btn">참가하기</a>
	                        
	                        </div>
					      </div>
					    </div>
					</c:forEach>
					  </div>
				
	
			<!-- 페이지 링크를 표시합니다 -->
			<div class="page_container">
			    <div>
		            	<button id="writeBtn" class="hover-color">방 만들기</button>
		        </div>
				
			    <!-- 페이지 -->
			    <div class="page_btn">
			        <div class="page_wrap">
			            <div class="page_nation">
			                <a class="arrow pprev" href="#" onclick="pprevPage()"></a>
			                <a class="arrow prev" href="#" onclick="prevPage()"></a>
			                <!-- 서버에서 생성된 페이지 버튼 -->
			                <c:forEach begin="0" end="${totalPages - 1}" var="pageNumber">
							    <a class="page-link" href="/main?page=${pageNumber}">${pageNumber + 1}</a>
							</c:forEach>
			                <a class="arrow next" href="#" onclick="nextPage()"></a>
			                <a class="arrow nnext" href="#" onclick="getPage(${totalPages - 1})"></a>
			            </div>
			        </div>
			    </div>
			</div>
		</section>
	</nav>
	</div>
</div>

	</main>
 <jsp:include page="/resources/footer.jsp" />
 
 
</body>
</html>