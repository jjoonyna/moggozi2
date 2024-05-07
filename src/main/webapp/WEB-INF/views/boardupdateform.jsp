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
		
		<div class="container">
				<!-- 로그인 창 -->
			<aside>
	    	<div class="login-wrapper">
			    <c:choose>
			        <c:when test="${!empty username}">
			            <h2 class="login-text">로그인 상태</h2>
			            <p>로그인한 사용자: ${username}</p>
			            <a href="mypage">마이페이지</a>
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
			
			
			
			
				<!-- 글작성 폼 -->
				<section>
					<div class="cards">
						<form id="upload1" method="Post" action="/boardupdate" enctype="multipart/form-data">
							<input type="hidden" name="page" value="${page }">
							
							<table class="innerTable" border=1 width=500 align=center>
								<tr>
									<td style="font-size: 40px; text-align: center;">글 수정</td>
								</tr>
								<tr>
									<td><span class="categoryTitle">카테고리</span>
									    <select class="classLeft" name="category">
						                    <option value="">선택</option>
						                    <option value="요리" ${commonBoard.category eq '요리' ? 'selected' : ''}>요리</option>
						                    <option value="리뷰" ${commonBoard.category eq '리뷰' ? 'selected' : ''}>리뷰</option>
						                    <option value="시사/재테크" ${commonBoard.category eq '시사/재테크' ? 'selected' : ''}>시사/재테크</option>
						                    <option value="일상" ${commonBoard.category eq '일상' ? 'selected' : ''}>일상</option>
						                    <option value="반려동물" ${commonBoard.category eq '반려동물' ? 'selected' : ''}>반려동물</option>
						                    <option value="쇼핑" ${commonBoard.category eq '쇼핑' ? 'selected' : ''}>쇼핑</option>
						                    <option value="DIY" ${commonBoard.category eq 'DIY' ? 'selected' : ''}>DIY</option>
						                    <option value="노래" ${commonBoard.category eq '노래' ? 'selected' : ''}>노래</option>
						                    <option value="키덜트" ${commonBoard.category eq '키덜트' ? 'selected' : ''}>키덜트</option>
						                    <option value="게임" ${commonBoard.category eq '게임' ? 'selected' : ''}>게임</option>
									    </select>
									</td>
								</tr>
								<tr>
									<td>								
									    <span>제목</span>
									    <input value="${commonBoard.boardSubject }" type="text" name="boardSubject" class="subjectArea" required="required">
									</td>
								</tr>
								<tr>
									<td>
								    	<span class="contentTitle">내용</span>
								    	<textarea name="boardContent" class="contentArea">${commonBoard.boardContent }</textarea>
								    </td>
								</tr>
								<tr>
									<td>
								    	<div>
								    		<span class="uploadTitle">파일업로드</span>
								    		<input class="uploadBtn" type="file" name="boardFile">
								    	</div>
								    </td>
								</tr>
								<tr>
									<td>
								    	<div>
								    		<input type="hidden" name="boardDate" value="<%=new Timestamp(System.currentTimeMillis())%>">
								    	</div>
								    </td>
								    <td>
								    	<div>
								    		<input type="hidden" name="boardNo" value="${commonBoard.boardNo}">
								    	</div>
								    </td>
								</tr>

							</table>
							<div class="btn">
							<input type="submit" value="수정" class="insBtn">
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