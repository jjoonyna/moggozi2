<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" 
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>회원가입 및 로그인페이지 2</title>
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
	<div class="container" id="container">
		<div class="form-container sign-up">
			<form method="post" action="/joinProc">
				<h1>회원가입을 하세요!</h1>
			<span>다음을 기입해 주세요!</span>
                <input name="username" id="username" class="input_box" placeholder="아이디" />
                <input name="normalname" id="normalname" class="input_box" placeholder="이름" />
                <input name="usernick" id="usernick" class="input_box" placeholder="닉네임 " />
                <input name="useremail" id="useremail" placeholder="Email">
                <input type="password" name="password" id="password" class="input_box" placeholder="패스워드"  />
                <input type="password" name="join_passwd2" id="join_passwd" class="input_box" placeholder="패스워드 중복 확인"  />
                <input name="userph" id="userph" class="input_box" placeholder="휴대폰번호는 - 빼고 입력" />
                <input name="useryear" id="useryear"  class="input_box" placeholder="출생년도" />
      			<input type="button" value="우편번호검색" class="input_button" onclick="openDaumPostcode()" />
                <input name="userzip" id="userzip" size="5" class="input_box" readonly onclick="post_search()" />
                <input name="useraddress1" id="useraddress1" class="input_box"  placeholder="주소를 입력하세요 " readonly onclick="post_search()" />
                <input name="useraddress2" id="useraddress2" class="input_box" placeholder="나머지 주소 입력 하세요 " />
				<div class="radio-container">
				<input name="usergender" id="usergender" type="radio" name="gender" value="man">남 
                <input name="usergender" id="usergender" type="radio" name="gender" value="woman">여
               </div >
                <button>등록!하기! </button>
            </form>
		</div>
		
		 <div class="form-container sign-in">
				<form method="post" action="/loginProc">
				<h1>로그인 하세욧!</h1>
				<div class="social-icons">
					<a href="/google_login" class="icon"><i class="fa-brands fa-google-plus-g"></i></a>
					<a href="/naver_login" class="icon"><i class="fa-brands fa-neos"></i></a> <a
						href="/kakao_login" class="icon"><i class="fa-brands fa-kaggle"></i></a>
				</div>
				<span>정보를 입력해 주세요옷!</span>
				    <input type="text" name="username" placeholder="아이디">
				    <input type="password" name="password" placeholder="패스워드">
				    <button type="submit">로그인</button>

				<a href="#">패스워드를 잊어뿟슴꽈?</a>
				</form>				
		</div>
	    <div class="toggle-container">
            <div class="toggle">
                <div class="toggle-panel toggle-left">
                    <h1>모꼬지들 하이여!! </h1>
                    <p>개인정보 입력해주세요.</p>
                    <button class="hidden" id="login">로그인 </button>
                </div>
                <div class="toggle-panel toggle-right">
                    <h1>반갑습니당 </h1>
                    <p>회원가입을 위해 다음을 입력해주세요 </p>
                    <button class="hidden" id="register">회원 가입하러 가기 </button>
                </div>
            </div>
        </div>
     </div>
     <script src="./js/signer.js"></script>
</body>
</html>