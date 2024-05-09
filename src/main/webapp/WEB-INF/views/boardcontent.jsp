<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css">
    <link rel="stylesheet" type="text/css" href="./css/boardcontent.css">
    <link rel="icon" type="image/x-icon" href="./images/m.png">
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
				<div class="hot-mocozi">
					<table>
					
					</table>
				</div>
			</aside>
			
			<nav>
			<div class="search-wrap">
				<!-- 카테고리 -->
				<div class="search-category">
				 <label for="category" style="margin-right: 5px;">모임</label>
				 <select id="category" name="category" onchange="getCategory()">
				 	<option value="" disabled selected>선택</option>
				 	<!-- 모임 분류 -->
				 	<option value="스터디">스터디</option>
                    <option value="운동">운동</option>
                    <option value="요리">요리</option>
                    <option value="리뷰">리뷰</option>
                    <option value="시사/재테크">시사/재테크</option>
                    <option value="일상">일상</option>
                    <option value="반려동물">반려동물</option>
                    <option value="쇼핑">쇼핑</option>
                    <option value="DIY">DIY</option>
                    <option value="노래">노래</option>
                    <option value="키덜트">키덜트</option>
                    <option value="게임">게임</option>
				 </select>
				</div>
				
				<!-- 검색 범위 -->
				<div class="search-boundary">
				 <label for="boundary"></label>
				 <select id="boundary" name="boundary" onchange="getBoundary()">
				 	<option value="" disabled selected>선택</option>
				 	<!-- 모임 분류 -->
				 	<option value="제목만">제목만</option>
                    <option value="게시글+댓글">게시글+댓글</option>
                    <option value="내용">내용</option>
                    <option value="글작성자">글작성자</option>
                    <option value="댓글내용">댓글내용</option>
                    <option value="댓글작성자">댓글작성자</option>
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
			
			
			
			
			
			
			<!-- 상세 게시판 -->
				<section>
					<div class="cards">
						<c:choose>
						    <c:when test="${not empty CommonBoard.category}">
						        <h3 class="cards_category">[${CommonBoard.category}]</h3>
						    </c:when>
						    <c:otherwise>
						    <br><br><br>
						    </c:otherwise>
						</c:choose>
						<h1 class="cards_subject">${CommonBoard.boardSubject}</h1>
						<table class="navClass">
							<tr class="classLeft">
								<td class="spanNicks">
									<span class="spanNick1">작성자: ${CommonBoard.usernick}</span>
									<span class="spanNick2">등록일: ${CommonBoard.boardDate}</span>
									<span class="spanNick3">댓글: ${CommonBoard.boardRe}</span>
									<span class="spanNick4">조회: ${CommonBoard.boardCnt}회</span>
									<!-- <img src="../images/undraw_profile.svg" class="myImage"> -->
								</td>
							</tr>
							<tr class="classLeft">
								<td class="taContent">
									<textarea readonly>${CommonBoard.boardContent}</textarea>
								</td>
							</tr>
						</table>
						<div class="btn">
							<input type="button" value="수정" class="updateBtn" onClick="location.href='boardupdateform?boardNo=${boardNo}&page=${page}'">
							<input type="button" value="삭제" class="delBtn" onClick="location.href='boarddelete?boardNo=${boardNo}&page=${page}'">
							<input type="button" value="목록" class="listBtn" onClick="location.href='boardlist?page=${page}'">
						</div>
					</div>
				</section>
			</nav>
		</div>
		
	</main>
	<jsp:include page="/resources/footer.jsp" />
</body>
</html>