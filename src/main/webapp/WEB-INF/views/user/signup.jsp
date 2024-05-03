<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<link rel="stylesheet" href="style.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
	
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=request.getContextPath()%>/js/member.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function openDaumPostcode() {
   new daum.Postcode({
      oncomplete : function(data) {            
         document.getElementById('userzip').value = data.zonecode;
         document.getElementById('useraddress1').value = data.address;            
      }
   }).open();
}
</script>
</head>
<body>

	<div class="wrapper">
		<form method="post" action="/join">
			<h2>회원가입 하세요</h2>

			<div class="input-box">

				<div class="input-field">
					<input name="normalname" id="normalname" class="input_box" placeholder="이름을 입력하세요 "  />
						<i class='bx bx-user'></i>
				</div>


				<div>
					<input name="username" id="username" class="input_box"
						placeholder="아이디"  />
						<i class='bx bx-user-circle' ></i>
				</div>

				<div>
					<input name="password" id="password"
						class="input_box" placeholder="패스워드"  />
						<i class='bx bxs-lock-alt' ></i>
				</div>

				<div>
					<input name="join_passwd2" id="join_passwd" class="input_box" placeholder="패스워드 중복 확인"  />
					<i class='bx bx-lock-alt' ></i>
				</div>

				<div>
					<input name="userph" id="userph" class="input_box" placeholder="휴대폰번호는 - 빼고 입력" />
					<i class='bx bxs-phone-call' ></i>
					
				</div>

				<div>
					<input name="useryear" id="useryear"  class="input_box" placeholder="출생년도" />
					<i class='bx bxs-baby-carriage' ></i>
				</div>
				
				<div>
					<input name="userzip" id="userzip" size="5" class="input_box" readonly onclick="post_search()" />
      				<input type="button" value="우편번호검색" class="input_button" onclick="openDaumPostcode()" />
					<i class='bx bxs-analyse' ></i>
				</div>
				
				<div>
				    <input name="useraddress1" id="useraddress1" class="input_box"  placeholder="주소를 입력하세요 " readonly onclick="post_search()" />
					<!-- <input name="join_address1" id="join_address1" class="input_box" placeholder="주소1" readonly onclick="post_search()" /> -->
					<i class='bx bxs-home-alt-2' ></i>
				</div>

				<div>
					<input name="useraddress2" id="useraddress2" class="input_box" placeholder="나머지 주소 입력 하세요 " />
					<i class='bx bxs-home-alt-2' ></i>
				</div>

				<div>
					<input name="useremail" id="join_email" class="input_box" placeholder="이메일 주소" />
					<i class='bx bxs-envelope' ></i>
				</div>
				
				<input name="usergender" id="usergender" type="radio" name="gender" value="man">남
				<input name="usergender" id="usergender" type="radio" name="gender" value="woman">여

			</div>

<label><input type="checkbox"> 위 사실에 반 강제 동의하고 난 회원가입을 합니다아~~~</label>
			<input type="submit" value="등록" class="input_button" /> 
			<input type="reset" value="취소" class="input_button" />
		</form>
	</div>
</body>
</html>