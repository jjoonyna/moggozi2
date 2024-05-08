<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
   <script src="../js/main.js"></script>
   
	<main>
	  <!-- 메인 배너 -->
        <div class="main-banner">
            <h1>굉장히 엄청난 타이포 그래피</h1>
        </div>
		
        <div class="container">
            <!-- 로그인 창 -->
            <aside>
            <div style="width: 300px; padding: 40px; box-sizing: border-box; border: 0.5px solid #ccc; border-radius: 5px;">
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


<!-- 상세 게시판 -->
<nav>
    <h1 class="sub-title">내 문의 상세 보기</h1>
    <section>
        <div class="cards">
            <c:choose>
                <c:when test="${not empty notice}">
                    <h1 class="cards_subject">${notice.notiTitle}</h1>
                    <table class="navClass">
                        <tr class="classLeft">
                            <td class="spanNicks">
                                <span class="spanNick1">작성자: ${notice.usernick}</span>
                                <span class="spanNick4">등록일: <fmt:formatDate value="${notice.notiDate}" pattern="yyyy.MM.dd HH:mm" /></span>
                            </td>
                        </tr>
                        <tr class="classLeft">
                            <td class="taContent">
                                <!-- 수정된 부분 -->
                                <textarea id="noticeContent" readonly>${notice.notiContent}</textarea>
                            </td>
                        </tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <table class="navClass">
                        <tr class="classLeft">
                            <td class="taContent">
                                <!-- 수정된 부분 -->
                                <textarea id="noticeContent" readonly>문의 정보를 찾을 수 없습니다.</textarea>
                            </td>
                        </tr>
                    </table>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${not empty replies}">
                    <h1 class="cards_subject">답변</h1>
                    <c:forEach items="${replies}" var="reply">
                        <table class="navClass">
                            <tr class="classLeft">
                                <td class="spanNicks">
                                    <span class="spanNick1">작성자: ${reply.usernick}</span>
                                    <span style="position: relative; right: -160px;">등록일: <fmt:formatDate value="${reply.replyDate}" pattern="yyyy.MM.dd HH:mm" /></span>
                                </td>
                            </tr>
                            <tr class="classLeft">
                                <td class="taContent">
                                    <!-- 수정된 부분 -->
                                    <textarea readonly>${reply.replyContent}</textarea>
                                </td>
                            </tr>
                        </table>
                    </c:forEach>
                </c:when>
                <c:otherwise>
	                 <table class="navClass">
	                        <tr class="classLeft">
	                            <td class="taContent">
	                                <!-- 수정된 부분 -->
	                                <textarea id="noticeContent" readonly>답변이 없습니다.</textarea>
	                            </td>
	                        </tr>
	                    </table>
                </c:otherwise>
            </c:choose>
            <div class="btn">
                <input type="button" value="목록" class="listBtn" onclick="window.location.href='myqnaList?usernick=${usernick}'">
            </div>
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



        
</main>
	<jsp:include page="/resources/footer.jsp" />
</body>
</html>