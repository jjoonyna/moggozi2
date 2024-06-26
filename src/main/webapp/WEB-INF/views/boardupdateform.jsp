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
			            <h2 class="login-text">회원 정보</h2>
			            <p>어서오세요! ${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p>
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
                                    <a href="mymoim">모임 목록</a>
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
				<h1 class="sub-title">모임니당</h1>
				<!-- 글 수정 폼 -->
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