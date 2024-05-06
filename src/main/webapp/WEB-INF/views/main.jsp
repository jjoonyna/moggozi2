<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="./css/reset.css">
    <link rel="stylesheet" type="text/css" href="./css/mocozi.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css">
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
				            <a href="#" class="hover-color">마이페이지</a>
				        </td>
				    </tr>
				</table>
			</div>
			
			
			<!-- hot 모꼬지! -->
			

			
			
			</aside>
			
			<nav>
			<div class="search-wrap">
				<!-- 카테고리 -->
				<div class="search-category">
				 <label for="category" style="margin-right: 5px;">모임</label>
				 <select id="category" name="category" onchange="getCategory()">
				 	<option value="" disabled selected>선택</option>
				 	<!-- 모임 분류 -->
				 	<option value="운동">운동</option>
                    <option value="게임">게임</option>
				 </select>
				</div>
				
				   <!-- 검색창 -->
	               <div class="search-wrap searchBox">
	                     <label for="search" id="searchBar">검색</label>
	                     <input type="text" id="search" name="search" placeholder="모임 명을 검색해주세요.">
	               </div>
	
	
	
	
	               <!-- 검색 버튼 -->
	               <div>
	                  <button id="searchBtn" class="hover-color">검색</button>
	               </div>
	               <br> <br> <br> <br>
					</div>
			
			
					<section>
<!-- 					게시판 목록 -->
					<div class="cards">
					<c:forEach items="${mokkojiList}" var="mokkoji">
					  <div class="card">
					    <div class="card__image-holder">
					      <img class="card__image" src="${mokkoji.mokkoji_images}" alt="모꼬지이미지" />
					    </div>
					    <div class="card-title">
					      <a href="#" class="toggle-info btn">
					        <span class="left"></span>
					        <span class="right"></span>
					      </a>
					      <h2>${mokkoji.mokkoji_title}
					          <small>닉네임 : ${mokkoji.user_nickname}</small>
					          </h2>
					    </div>
					    <div class="card-flap flap1">
					      <div class="card-description">
					        ${mokkoji.mokkoji_intro }
					      </div>
					      <div class="card-flap flap2">
					        <div class="card-actions">
					          <a href="#" class="btn">참가하기</a>
					        </div>
					      </div>
					    </div>
					  </div>
					</c:forEach>
				
	
<!-- 			페이지 링크를 표시합니다 -->
			<div class="page_container">
			    <div>
		            	<button id="writeBtn" class="hover-color">방 만들기</button>
		        </div>
				
<!-- 			    페이지 -->
			    <div class="page_btn">
			        <div class="page_wrap">
			            <div class="page_nation">
			                <a class="arrow pprev" href="#" onclick="pprevPage()"></a>
			                <a class="arrow prev" href="#" onclick="prevPage()"></a>
<!-- 			                서버에서 생성된 페이지 버튼 -->
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

	</main>
 <jsp:include page="/resources/footer.jsp" />
 
 
</body>
</html>