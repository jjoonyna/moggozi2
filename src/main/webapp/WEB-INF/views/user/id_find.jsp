<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <jsp:include page="/resources/commons/header.jsp" />
<link rel="stylesheet" href="./css/find.css"> --%>
<script type="text/javascript" src="/resources/js/find.js"></script>
<title>ID찾기 페이지</title>
</head>
<body>
   <div id="table">
      <table>
         <h2>ID찾기</h2>
			<div>
				<input name="username" id="username" class="input_box" placeholder="이름" /> 
					<i class='bx bxs-baby-carriage'></i>
			</div>

			<div>
				<input name="userph" id="userph" class="input_box" placeholder="전화번호" /> 
					<i class='bx bxs-baby-carriage'></i>
					
			</div>
		</table>
      <button id="user_id_find">확인</button>
   </div>
   <div id="findid"></div>
</body>
</html>