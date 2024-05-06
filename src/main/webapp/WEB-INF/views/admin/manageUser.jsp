<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>모꼬지 관리자 페이지</title>

    <!-- 폰트 및 css-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="admin/mainAdmin">
                <div class="sidebar-brand-icon ">
                    <i class="fas fa-laugh-wink"></i>
                    
                </div>
                <div class="sidebar-brand-text mx-3">모꼬지 <sup>관리자</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="index">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>메인</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- 대분류 카테고리명 -->
            <div class="sidebar-heading">
                관리 항목
            </div>

            <!-- 카테고리 목록1 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>설정</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">관리자</h6>
                        <a class="collapse-item" href="noticeList">공지 사항</a>
                        <a class="collapse-item" href="askList">1:1 문의</a>
                    </div>
                </div>
            </li>

            <!-- 카테고리 목록2 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>관리</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">항목</h6>
                        <a class="collapse-item" href="userManagement">회원 관리</a>
                        <a class="collapse-item" href="postMangement">게시물 관리</a>
                    </div>
                </div>
            </li>

            <!-- 사이드바 줄 -->
            <hr class="sidebar-divider">

            <!-- 대분류 카테고리명 -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- 카테고리 목록3 -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="login">Login</a>
                        <a class="collapse-item" href="register">Register</a>
                        <a class="collapse-item" href="forgot-password">Forgot Password</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Other Pages:</h6>
                        <a class="collapse-item" href="404">404 Page</a>
                        <a class="collapse-item" href="blank">Blank Page</a>
                    </div>
                </div>
            </li>

            <!-- 차트 -->
            <li class="nav-item">
                <a class="nav-link" href="charts">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>차트</span></a>
            </li>

            <!-- 테이블 -->
            <li class="nav-item">
                <a class="nav-link" href="tables">
                    <i class="fas fa-fw fa-table"></i>
                    <span>테이블</span></a>
            </li>

            <!-- 사이드바 줄 -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- 사이드바 토글 버튼 (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            

        </ul>
        <!-- 사이드바 끝 -->
        
        
        

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- 메인 -->
            <div id="content">

                <!-- 상단 바 -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- 상단 바 토글 (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    

                    <!-- 토글 바 nav바 -->
                    <ul class="navbar-nav ml-auto">

                        <!-- 검색 드롭다운 창 (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- 메세지 -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- 알람 -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- 알람 드롭다운 -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    새로운 알림
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                       
                        <!-- 프로필 -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${id} 님</span>
                                <img class="img-profile rounded-circle"
                                    src="./images/undraw_profile.svg">
                            </a>
                            <!-- 프로필 드롭다운 -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">

                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- 상단 바 끝 -->
                
                
                

                <!-- 카테고리 상세보기 -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4" style="margin: 0 10%;">
                        <h1 class="h3 mb-0 text-gray-800">1:1 문의</h1>
                         <a href="askWrite" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="text-white-50"></i> 글쓰기</a>
                    </div>
                    </div>

                    <!-- Content Row -->
                    <div class="veryrow">
						
						<!-- Board List Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
						    <div class="card border-left-primary shadow h-100 py-2">
						        <div class="card-body">
						            <div class="row no-gutters align-items-center">
						                <div class="col mr-2">
						                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
						                        * 회원 / 탈퇴 로 상태 구분되어 있습니다.</div>
						                    <div class="table-responsive">
						                        <table class="table table-striped">
						                            <thead>
						                                <tr>
						                                    <th>아이디</th>
						                                    <th>닉네임</th>
						                                    <th>이름</th>
						                                    <th>휴대폰번호</th>
						                                    <th>출생년도</th>
						                                    <th>우편번호</th>
						                                    <th>기본주소</th>
						                                    <th>상세주소</th>
						                                    <th>이메일</th>
						                                    <th>성별</th>
						                                    <th>회원상태</th>
						                                </tr>
						                            </thead>
						                            <tbody>
						                                <!-- 공지사항 목록을 반복해서 출력합니다 -->
						                                <c:forEach items="${userList}" var="notice">
						                                    <tr>
						                                        <td><c:out value="${notice.username}" /></td>
						                                        <td><c:out value="${notice.usernick}" /></td>
						                                        <td><a href="askDetail?id=${notice.normalname}"><c:out value="${notice.notiTitle}" /></a></td>
						                                        <td><c:out value="${notice.userph}" /></td>
						                                        <td><c:out value="${notice.useryear}" /></td>
						                                        <td><c:out value="${notice.userzip}" /></td>
						                                        <td><c:out value="${notice.useraddress1}" /></td>
						                                        <td><c:out value="${notice.useraddress2}" /></td>
						                                        <td><c:out value="${notice.useremail}" /></td>
						                                        <td><c:out value="${notice.usergender}" /></td>
						                                        <td><c:out value="${notice.role}" /></td>
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
						                                    <a class="page-link" href="/noticeList?page=${i}">${i + 1}</a>
						                                </li>
						                            </c:forEach>
						                        </ul>
						                    </nav>
						                </div>
						            </div>
						        </div>
						    </div>
						</div>


                      
                    <!-- Content Row -->

                    <div class="row">


                     
              
					
					<!-- 공백 만들기 -->
					<div class="empty-place">
					
					
					</div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                  
				         <div>
				            <br>
				            <p>
				               서울특별시 논현로7길 7 7층 703강의실(예진빌딩)&nbsp; |&nbsp; 현정폰 :
				               010-8642-4433&nbsp; &nbsp;|&nbsp; 스카웃 및 면접 문의 :
				               1dangness@gmail.com&nbsp;<br /> 발행&middot;배포 : 윤또잉&nbsp;
				               |&nbsp; 비난책임자 : 나원준&nbsp; |&nbsp; 등록번호 : 성동 파7647&nbsp; | 등록일
				               : 2024.6.22<br /> Copyright ⓒ 2024&nbsp;임정석. All rights reserved.
				            </p>
                    </div></div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">로그아웃</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">관리자님! 로그아웃 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" href="login">로그아웃</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.js"></script>

    <!-- Page level plugins -->
    <script src="../vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../js/demo/chart-area-demo.js"></script>
    <script src="../js/demo/chart-pie-demo.js"></script>

</body>

</html>

