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
<!-- 	<script src="./js/main.js"></script> -->
	<script src="./js/videoroomtest.js"></script>
</head>
<body>
	<main>
		<!-- 메인 배너 -->
		<div class="main-banner">
		</div>
		
		<div class="container">
	    	<!-- 로그인 창 -->
			<aside>
			    <c:choose>
			        <c:when test="${!empty usernick}">
	    	<div class="login-wrapper">
			            <h2 class="login-text">회원 정보</h2>
			            <p><fmt:formatDate value="${loginTime}" pattern="M월 d일 a h시 mm분"/> 등장!</p>
			            <p>어서오세요!<br>${usernick}님 ☆ﾐ(o*･ω･)ﾉ</p><br>
			            <a href="mypage">마이페이지</a>
			            <a href="logout">로그아웃</a>
			</div>
			        </c:when>
			        <c:otherwise>
	    	<div class="login-wrapper">
	    				<div class="login-menu">
			            <h2 class="login-text">(っ˘▿˘)(˘▿˘)˘▿˘ς)</h2><br><br><br>
			            
			           <label for="login-menu" >
						    <a href="signup" class="login-btn hover-color">로그인하러 가기!</a>
						</label>
						</div>
			</div>
			        </c:otherwise>
			    </c:choose>


		
		
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
			
			<nav>
			<section>
		    <!-- 게시판 목록 -->
		    <div class="cards">
		        <c:forEach items="${mokkojiList}" var="mokkoji">
		        
		            <div class="card">
		            
	<div class="card__image-holder">
                          <c:choose>
                          
                             <c:when test="${mokkoji.mokkojiCategory eq '스터디'}">
                                <img class="card__image" src="../images/cate_study.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '운동'}">
                                <img class="card__image" src="../images/cate_exercise.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '요리'}">
                                <img class="card__image" src="../images/cate_cooking.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '리뷰'}">
                                <img class="card__image" src="../images/cate_review.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '시사/재테크'}">
                                <img class="card__image" src="../images/cate_sisa.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '일상'}">
                                <img class="card__image" src="../images/cate_daily.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '반려동물'}">
                                <img class="card__image" src="../images/cate_pet.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '쇼핑'}">
                                <img class="card__image" src="../images/cate_shopping.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq 'DIY'}">
                                <img class="card__image" src="../images/cate_diy.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '노래'}">
                                <img class="card__image" src="../images/cate_music.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '키덜트'}">
                                <img class="card__image" src="../images/cate_kidult.jpg" alt="모꼬지이미지">
                             </c:when>
                             <c:when test="${mokkoji.mokkojiCategory eq '게임'}">
                                <img class="card__image" src="../images/cate_game.jpg" alt="모꼬지이미지">
                             </c:when>
                             
                             <c:otherwise>
                                <img class="card__image" src="../images/cate_cooking.jpg" alt="모꼬지이미지">
                             </c:otherwise>
                          </c:choose>
                      </div>
		                
		                <div class="card-title">
		                    <a href="#" class="toggle-info btn">
		                        <span class="left"></span>
		                        <span class="right"></span>
		                    </a>
		                    <h2>[${mokkoji.mokkojiCategory}]${mokkoji.mokkojiTitle}
		                        <small>닉네임 :${mokkoji.usernick}</small>
		                    </h2>
		                </div>
		                
		                <div class="card-flap flap1">
		                    <div class="card-description">
		                        ${mokkoji.mokkojiIntro }
		                    </div>
		                    
		                    <div class="card-flap flap2">
                     	 	 <div class="card-actions" id="buttonContainer">
	                           <a href="javascript:join('${mokkoji.mokkojiNo}','${mokkoji.mokkojiTitle}','${mokkoji.usernick}')"
	                          class="btn" autocomplete="off" >참가하기</a>
	                         </div>
					       </div>
		                </div>
		                
		            </div>
		            
		        </c:forEach>
		        
		     <!-- 페이지 링크를 표시합니다 -->
		     <div class="page_container">
		     	<div>
	            	<button id="writeBtn" class="hover-color">방 만들기</button>
		        </div>
		     
                <div class="page_btn">
                    <div class="page_wrap">
                        <div class="page_nation">
                            <a class="arrow pprev" href="#" onclick="getPage(0)"></a>
                            <a class="arrow prev" href="#" onclick="prevPage()"></a>
                            <c:forEach begin="0" end="${totalPages > 0 ? totalPages - 1 : 0}" var="pageNumber">
                                <a class="page-link" href="#">${pageNumber + 1}</a>
                            </c:forEach>
                            <a class="arrow next" href="#" onclick="nextPage()"></a>
                            <a class="arrow nnext" href="#" onclick="getPage(${totalPages - 1})"></a>
                        </div>
                    </div>
                </div>
                
              </div>
		        
		    </div>
		

			</section>
			</nav>
		</div>

	</main>
 <jsp:include page="/resources/footer.jsp" />
 
 
</body>
</html>