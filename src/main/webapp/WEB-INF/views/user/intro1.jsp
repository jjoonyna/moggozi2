<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title> = M o g g o z i = </title>
<link rel="stylesheet" href="/css/intro1.css">
<link rel="stylesheet" href="/css/start.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/gsap@3.12.5/dist/gsap.min.js"></script>


</head>
<body>

	<div class="container">
		<div class="overlay">
			<p class="screen">Moggozi</p>
			<div class="intro">
				<button class="myBtn" onclick="fadeOut()">Moggozi</button>
			</div>
		</div>
	</div>
	<div class="overlay-2"></div>
	<div class="img">
		<img src="./images/moggo.png"/>
	</div>
<!-- 	<form method="post" action="member/login" id="login-form">
		<input type="text" name="userName" placeholder="ID" required>
		<input type="password" name="userPassword" placeholder="Password"
			required> <input type="submit" value="Login">
		<button type="button" id="signUpButton" 
				onclick="location.href='signup'">SIGN UP</button>
	</form> -->
	



	<script>
	TweenMax.to(".myBtn", 1, {
		y: -100,
		opacity: 0
	});

	TweenMax.to(".screen", 2, {
		y: -400,
		opacity: 0,
		ease: Power2.easeInOut,
		delay: 2
	});

	TweenMax.to(".overlay", 2, {
		ease: Power2.easeInOut,
	});

	TweenMax.to(".overlay", 2, {
		delay: 2.6,
		top: "-110%",
		ease: Expo.easeInOut
	});

	TweenMax.to(".overlay-2", 2, {
		delay: 3,
		top: "-110%",
		ease: Expo.easeInOut
	});

	TweenMax.to(".content", 2, {
		delay: 3.2,
		top: "0",
		ease: Power2.easeInOut
	});

	TweenMax.to(".content", 2, {
		opacity: 1,
		y: -300,
		delay: 3.2,
		ease: Power2.easeInOut
	});
</script>


</body>
</html>