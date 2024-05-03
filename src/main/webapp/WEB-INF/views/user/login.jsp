<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>log in page 입니다 </title>
<link rel="stylesheet" href="style.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
	
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

</script>
</head>
<body>

	<div class="login_wrapper">
		<form method="post" action="user_login_ok.do" onsubmit="returncheck()">
			<h2>로그인</h2>

			<div class="input-box">

				<div class="input-field">
					<input name="join_id" id="join_id" class="input_box" placeholder="아이디"  />
						<i class='bx bx-user-circle' ></i>
				</div>

				<div>
					<input name="join_passwd1" id="join_passwd" class="input_box" placeholder="패스워드"  />
						<i class='bx bxs-lock-alt' ></i>
				</div>

				

		    <div id="css다시 먹여야">
    <input type="submit" value="로그인" class="input_button" />
    <input type="reset" value="취소" class="input_button"
          onclick="$('#id').focus();" />
    <input type="button" value="회원가입" class="input_button"
          onclick="location='signup'" />
    <input type="button" value="ID찾기" class="input_button"
          onclick="location='id_find'" />
    <input type="button" value="비번찾기" class="input_button"
          onclick="location='pw_find'" />
		</form>
	</div>
</body>
</html>