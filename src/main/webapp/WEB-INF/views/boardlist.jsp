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
		<!-- <div class="main-banner">
			<h1><img src="./images/logo.png"></h1>
		</div> -->
		
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
                                    <a href="mymoim?usernick=${usernick}">모임 목록</a>
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
                                <a class="hover-color" href="#" onclick="return checkCurrentPage()">모임니당</a>
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