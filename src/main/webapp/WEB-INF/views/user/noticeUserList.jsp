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
    <link rel="stylesheet" type="text/css" href="../css/login.css">
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
                                    <a href="#" onclick="return preparingPage()">내 정보 수정</a>
                                    <a href="#" onclick="return preparingPage()">비밀번호 변경</a>
                                    <a href="#" onclick="return preparingPage()">회원 탈퇴</a>
                                    <!-- 사이드바 줄 -->
                                    <hr class="sidebar-divider">
                                    <a href="#" onclick="return preparingPage()">모임 목록</a>
                                    <a href="myqnaList">문의 내역</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" class="hover-color">모일꼬지?</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="dropdown">
                                <a class="hover-color">알림마당</a>
                                <div class="dropdown-content" id="myDropdown">
                                    <a href="#" onclick="return checkCurrentPage()">공지사항</a>
                                    <a href="askWrite">1:1 문의</a>
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
            
            <nav>
                <section>
                    <!-- 게시판 목록 -->
                     <h2 style="margin-right: auto; font-size: 30px;">1:1문의 내역</h2>
                    <div class="cards">
                           <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
		                                <tr>
		                                    <th>분류</th>
		                                    <th>번호</th>
		                                    <th>제목</th>
		                                    <th>작성자</th>
		                                    <th>날짜</th>
		                                    <th>조회수</th>
		                                </tr>
		                            </thead>
                                    <tbody>
                                         <!-- 공지사항 목록을 반복해서 출력합니다 -->
		                                 <c:forEach items="${noticeList}" var="notice">
		                                    <tr>
		                                        <td><c:out value="${notice.notiImpt}" /></td>
		                                        <td><c:out value="${notice.notiNo}" /></td>
		                                        <td><a href="noticeDetail?id=${notice.notiNo}"><c:out value="${notice.notiTitle}" /></a></td>
		                                        <td><c:out value="${notice.usernick}" /></td>
		                                        <td><fmt:formatDate value="${notice.notiDate}" pattern="yyyy.MM.dd HH:mm" /></td>
		                                        <td><c:out value="${notice.notiHit}" /></td>
		                                    </tr>
		                                 </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- Pagination -->
							<nav aria-label="Page navigation example">
							    <ul class="pagination">
							        <c:forEach begin="0" end="${totalPages - 1}" var="i">
							            <li class="page-item ${currentPage == i ? 'active' : ''}">
							                <a class="page-link" href="/noticeUserList?page=${i}">${i + 1}</a>
							            </li>
							        </c:forEach>
							    </ul>
							</nav>

                    </div>
    
            </section>
        </nav>
        </div>
    </main>
    
    <jsp:include page="/resources/footer.jsp" />

</body>
</html>
