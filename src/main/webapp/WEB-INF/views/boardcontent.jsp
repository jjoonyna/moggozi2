<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
<link rel="stylesheet" type="text/css" href="./css/boardcontent.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모꼬지</title>
    <jsp:include page="/resources/gnb.jsp" />
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
							<td><a class="hover-color" href="boardlist">모임니당</a></td>
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
			
			<!-- 상세 게시판 -->
			<nav>
				<h1 class="sub-title">모임니당</h1>
				<section>
					<div class="cards">
						<c:choose>
							<c:when test="${not empty CommonBoard}">
								<h1 class="cards_subject">${CommonBoard.boardSubject}</h1>
								<table class="navClass">
									<tr class="classLeft">
										<td class="spanNicks"><span class="spanNick1">작성자:
												${CommonBoard.usernick}</span> <span class="spanNick2">등록일: <fmt:formatDate
													value="${CommonBoard.boardDate}" pattern="yyyy.MM.dd HH:mm" /></span> <span
											class="spanNick4">조회수: ${CommonBoard.boardCnt}</span></td>
									</tr>
									<tr class="classLeft">
										<td class="taContent">
											<!-- 수정된 부분 --> <textarea id="noticeContent" readonly>${CommonBoard.boardContent}</textarea>
										</td>
									</tr>
								</table>
								<div class="btn">
							<input type="button" value="수정" class="updateBtn" onClick="location.href='boardupdateform?boardNo=${boardNo}&page=${page}'">
							<input type="button" value="삭제" class="delBtn" onClick="location.href='boarddelete?boardNo=${boardNo}&page=${page}'">
							<input type="button" value="목록" class="listBtn" onClick="location.href='boardlist?page=${page}'">
						</div>
							</c:when>
						</c:choose>
					</div>
				</section>
			</nav>

			<script>
				// 자바스크립트를 사용하여 텍스트 영역의 높이를 동적으로 조절하는 함수
				function autoResizeTextArea(textarea) {
					textarea.style.height = 'auto'; // 높이를 자동으로 설정
					textarea.style.height = (textarea.scrollHeight) + 'px'; // 실제 내용의 높이로 설정
				}

				// 문서가 로드될 때 모든 텍스트 영역의 높이를 조절
				window.addEventListener('DOMContentLoaded', function() {
					var textAreas = document.querySelectorAll('textarea');
					textAreas.forEach(function(textArea) {
						autoResizeTextArea(textArea);
					});
				});

				// 텍스트 영역의 내용이 변경될 때마다 높이를 다시 조절
				document.addEventListener('input', function(event) {
					var target = event.target;
					if (target.tagName.toLowerCase() === 'textarea') {
						autoResizeTextArea(target);
					}
				});
			</script>			
			
			
			
		</div>
		
	</main>
	<jsp:include page="/resources/footer.jsp" />
</body>
</html>