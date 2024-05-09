<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="/resources/header.jsp" />
    <link rel="stylesheet" type="text/css" href="../css/reset.css">
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
        <link rel="stylesheet" type="text/css" href="../css/login.css">
    <link rel="stylesheet" type="text/css" href="../css/boardlist.css">
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
                                        <a href="myinfoupdate">내 정보 수정</a>
                                        <a href="mypwdchange">비밀번호 변경</a>
                                        <a href="mydelete">회원 탈퇴</a>
                                        <!-- 사이드바 줄 -->
                                        <hr class="sidebar-divider">
                                        <a href="#" onclick="return checkCurrentPage()">모임 목록</a>
                                        <a href="myqnaList">내 모꼬지</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a class="hover-color" href="main">모일꼬지?</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a class="hover-color" href="boardlist">모임니당</a>
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
            </aside>
            
            <nav>
                <h1 class="sub-title">내 모꼬지 목록</h1>
                <!-- 게시판 리스트 -->
                <section class="board-list">
                    <div class="cards">
                        <table class="navClass">
                            <tr class="navClasses">
                                <td></td>
                                <td>제목</td>
                                <td>인원</td>
                                <td>만든날짜</td>
                                <td>작성자</td>
                            </tr>
        
                           <c:choose>
							    <c:when test="${empty mokkoji}">
							        <tr>
							            <td colspan="5">데이터가 없습니다.</td>
							        </tr>
							    </c:when>
							    <c:otherwise>
							        <c:forEach items="${mokkoji}" var="mokkojiItem">
							            <tr class="listArea">
							                <td><input type="checkbox"></td>
							                <td>${mokkojiItem.mokkojiTitle}</td>
							                <td>${mokkojiItem.mokkojiPerson}</td>
							                <td>${mokkojiItem.mokkojiDate}</td>
							                <td>${mokkojiItem.usernick}</td>
							            </tr>
							        </c:forEach>
							    </c:otherwise>
							</c:choose>

                        </table>
                        <!-- 삭제 버튼 -->
                        <form action="/listDelete" method="post">
                            <input type="submit" class="button-submit" value="삭제">
                        </form>
                    </div>
                </section>
            </nav>
        </div>
    </main>
    
    <jsp:include page="/resources/footer.jsp" />
</body>
</html>
