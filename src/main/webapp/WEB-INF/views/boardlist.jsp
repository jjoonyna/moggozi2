<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<jsp:include page="/resources/header.jsp" />
    <link rel="icon" type="image/x-icon" href="./images/m.png">
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
    <link rel="stylesheet" type="text/css" href="./css/footer.css">
    <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/boardlist.css">
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
						<p>어서오세요!<br><c:out value="${usernick}" />님 ☆ﾐ(o*･ω･)ﾉ</p>
					<br> 
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
										<a href="myinfoupdate">내 정보 수정</a> 
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
							<td><a href="main" class="hover-color">모일꼬지?</a></td>
						</tr>
						<tr>
							<td><a class="hover-color" href="#" onclick="return checkCurrentPage()">모임니당</a></td>
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

			
			
			<h1 class="sub-title">모임니당</h1>
				<!-- 일반 게시판 목록 -->
				<section>
					<div class="cards">
						<table class="navClass">
							<tr class="navClasses">
								<td>글번호</td>
								<td>작성자</td>
								<td>카테고리</td>
								<td>제목</td>
								<td>작성일</td>
								<td>조회수</td><br>
							</tr>
		
							<c:if test="${empty boardlist}">
								<tr>
									<td colspan="5">데이터가 없을꼬지</td>
								</tr>
							</c:if>
		
							<c:if test="${not empty boardlist}">
								<c:set var="no1" value="${boardNo }"></c:set>
								<c:forEach var="board" items="${boardlist }">
									<tr class="listArea">
										<c:if test="${board.boardState =='n' }">
											<td colspan="4">삭제된 데이터 입니다</td>
										</c:if>
										<c:if test="${board.boardState != 'n' }">		<!-- 글번호 / 페이지번호 -->
											<td>${board.boardNo}</td>
											<td>${board.usernick }</td>
											<td>${board.category}</td>
											<td>
												<span>
													<c:if test="${board.boardCnt > 30 }">
														<img src="images/hot.gif" class="hot">
													</c:if>
												</span>
												<a href="boardcontent?boardNo=${board.boardNo}&page=${page}" class="subject"> 
													<%-- <c:if test="${boardRe_level >0 }">
														<img alt="" src="images/level.gif" height="2" width="${boardRe_level *5 }">
														<img alt="" src="images/re.gif">
													</c:if>  --%>
													${board.boardSubject} <span class="boardRe">[${board.boardRe }]</span>
													
													<span>
														<c:if test="${not empty board.boardFile}">
															<img src="images/clip1.png" class="clip">
														</c:if>
													</span>
												</a>
											</td>
											<td>${board.boardDate}</td>
											<td>${board.boardCnt }</td>
										</c:if>
									</tr>
									<c:set var="no1" value="${no1 - 1}"/>
								</c:forEach>
							</c:if>
						</table> <br><br><br>
						
				<form action="boardlist.do" class="search_form"> <!-- get방식으로 넘긴다 -->
					<input type="hidden" name="pageNum" value="1"> 
						<select	name="search" class="selectBtn">
							<option value="subcon"	<c:if test="${search=='subcon'}" >selected="selected"</c:if>>제목+내용</option>
							<option value="subject"	<c:if test="${search=='boardSubject'}">selected="selected"</c:if>>제목</option>
							<option value="content"	<c:if test="${search=='boardContent'}">selected="selected"</c:if>>내용</option>
							<option value="writer"	<c:if test="${search=='usernick'}" >selected="selected"</c:if>>작성자</option>
						</select> 
					<input type="text" name="keyword" class="keyword"> 
					<input type="submit" value="확인" class="confBtn">
				</form>
	
				<ul class="pagination">
					<!-- 검색 했을 경우의 페이징 처리 -->
					<c:if test="${not empty keyword}">
						<c:if test="${pp.startPage > pp.pagePerBlk }">
							<li>
								<a href="list.do?pageNum=${pp.startPage - 1}&search=${search}&keyword=${keyword}">이전</a>
							</li>
						</c:if>
						<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
							<li <c:if test="${pp.currentPage==i}">class="active"</c:if>>
								<a href="list.do?pageNum=${i}&search=${search}&keyword=${keyword}">${i}</a>
							</li>
						</c:forEach>
						<c:if test="${pp.endPage < pp.totalPage}">
							<li>
								<a href="list.do?pageNum=${pp.endPage + 1}&search=${search}&keyword=${keyword}">다음</a>
							</li>
						</c:if>
					</c:if>
					
					
					
					<!-- 전체 목록의 페이징 처리 -->
					<c:if test="${empty keyword}">
						<c:if test="${pp.startPage > pp.pagePerBlk }">
							<li><a href="boardlist.do?pageNum=${pp.startPage - 1}">이전</a></li>
						</c:if>
						<c:forEach var="i" begin="${pp.startPage}" end="${pp.endPage}">
							<li <c:if test="${pp.currentPage==i}">class="active"</c:if>>
								<a href="boardlist.do?pageNum=${i}">${i}</a>
							</li>
						</c:forEach>
						<c:if test="${pp.endPage < pp.totalPage}">
							<li>
								<a href="boardlist.do?pageNum=${pp.endPage + 1}">다음</a>
							</li>
						</c:if>
					</c:if>
				</ul>
				<div align="center">
				<a href="boardinsertform" class="insBtn">글쓰기</a>
				</div><br><br><br>
		
		
					</div>
				</section>
			</nav>
		</div>


		
		
	</main>
	   <jsp:include page="/resources/footer.jsp" />
</body>
</html>