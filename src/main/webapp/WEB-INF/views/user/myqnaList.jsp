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
    <link rel="stylesheet" type="text/css" href="../css/mocozi.css">
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
                                    <a href="mymoim">모임 목록</a>
                                    <a href="#" onclick="return preparingPage()">문의 내역</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a  class="hover-color">모일꼬지?</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div href="boardlist?usernick=${usernick}" class="dropdown">
                                <a class="hover-color" href="boardlist?usernick=${usernick}">모임니당</a>
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
            
            
            
            </aside>
            
	        <nav>
                <h1 class="sub-title">내 문의 내역</h1>
                <!-- 일반 게시판 목록 -->
                <section>
                    <div class="cards">
                        <table class="navClass">
                            <tr class="navClasses">
                            	<td>문의번호</td>
                                <td>제목</td>
                                <td>작성자</td>
                                <td>작성일</td>
                                <td>상태</td>
                            </tr>
        
                            <c:if test="${empty askList}">
                                <tr>
                                    <td colspan="5">데이터가 없을꼬지</td>
                                </tr>
                            </c:if>
        
                            <c:if test="${not empty askList}">
                                <c:forEach items="${askList}" var="notice">
                                    <tr class="listArea">
                                        <td>${notice.notiNo}</td>
                                        <td><a href="myqnaDetail?id=${notice.notiNo}&notiNo=${notice.notiNo}" class="subject">${notice.notiTitle}</a></td>
                                        <td>${notice.usernick}</td>
                                        <td><fmt:formatDate value="${notice.notiDate}" pattern="yyyy.MM.dd HH:mm" /></td>
                                        <td>${notice.notiAt}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                        <br><br><br>
						
						<div class="page_container">
						    <div class="page_btn">
						        <div class="page_wrap">
						            <div class="page_nation">
						                <!-- 페이지가 1페이지를 초과하는 경우에만 '이전' 링크를 표시합니다. -->
						                <c:if test="${currentPage > 1}">
						                    <a class="arrow prev" href="#" onclick="prevPage()"></a>
						                </c:if>
						                
						                <!-- 페이지 번호 링크를 출력합니다. -->
						                <c:forEach begin="1" end="${totalPages}" var="pageNumber">
						                    <a class="page-link" href="noticeUserList?pageNum=${pageNumber}">${pageNumber}</a>
						                </c:forEach>
						                
						                <!-- 페이지가 총 페이지 수를 초과하지 않는 경우에만 '다음' 링크를 표시합니다. -->
						                <c:if test="${currentPage < totalPages}">
						                    <a class="arrow next" href="#" onclick="nextPage()"></a>
						                </c:if>
						            </div>
						        </div>
						    </div>
						</div>
						
						<script>
						    function prevPage() {
						        var prevPageNumber = ${currentPage - 1};
						        window.location.href = "noticeUserList?pageNum=" + prevPageNumber;
						    }
						    
						    function nextPage() {
						        var nextPageNumber = ${currentPage + 1};
						        window.location.href = "noticeUserList?pageNum=" + nextPageNumber;
						    }
						
						    // 페이지 번호를 클릭할 때 해당 페이지로 이동하는 함수
						    document.querySelectorAll('.page-link').forEach(item => {
						        item.addEventListener('click', event => {
						            event.preventDefault(); // 기본 이벤트 동작을 중지
						            var pageNumber = item.textContent; // 클릭한 페이지 번호 가져오기
						            window.location.href = "noticeUserList?pageNum=" + pageNumber; // 페이지 이동
						        });
						    });
						</script>
			                </nav>
			                </div>
    </main>
    
    <jsp:include page="/resources/footer.jsp" />

</body>
</html>
