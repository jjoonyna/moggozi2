<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>모꼬지</title>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/6.4.0/adapter.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.blockUI/2.70/jquery.blockUI.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/spin.js/2.3.2/spin.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="shortcut icon" href="#">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.4.0/cerulean/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css" />
<link rel="stylesheet" href="../css/video.css" />

</head>

<body>
	<main>
		<div class="container">
			<div class="col-md-12">
				<h1>
				<a href="javascript:registerUsername()">회의 시작</a> <br>
					<button class="btn btn-default" autocomplete="off" id="start">Start</button>
				</h1>
			</div>
			<div class="container hide" id="videojoin">
				<div class="moggzimain">
					<h2>모임 시작</h2>
					<div class="join">
						<div id="registernow">
							<div class="input-group margin-bottom-md ">
								<span class="input-group-addon">방 이름</span> <input
									 class="form-control" type="text" name="mokkojiTitle"
									value="${mokkojiTitle }" readonly="readonly" />		<!-- readonly -->
								 <input
									autocomplete="off" class="form-control" type="hidden"
									name="mokkojiNo" placeholder="Room Name" id="roomname"
									value="${mokkojiNo }" />		<!-- readonly -->
							</div>
						</div>
						<div class="join2">
							<span class="label label-info" id="you"></span>
							<div class="input-group margin-bottom-md ">
								<span class="input-group-addon">닉네임</span> <input
									autocomplete="off" class="form-control" type="text"
									placeholder="닉네임" id="usernick" name="usernick"
									onkeypress="return checkEnter(this, event);"
									value="${usernick }" />
							</div>
						</div>
						<span class="input-group-btn">
							<button class="btn btn-success" autocomplete="off" id="registertest" onclick="registerUsername()">대화방 참여</button>
						</span>
					</div>
				</div>
			</div>

			<!-- 참여방 입장 -->
			<div class="container hide" id="videos">
			<div class="joinmain">
				<div class="joinname">모임명:</div>
				<div id="joinroom">${ mokkojiTitle}</div>

				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								${mokkoji.usernick} <span class="label label-primary hide"
									id="publisher"></span>
								<div class="btn-group btn-group-xs pull-right hide">
									<div class="btn-group btn-group-xs">
										<button id="bitrateset" autocomplete="off"
											class="btn btn-primary dropdown-toggle"
											data-toggle="dropdown">
											Bandwidth<span class="caret"></span>
										</button>
										<ul id="bitrate" class="dropdown-menu" role="menu">
											<li><a href="#" id="0">No limit</a></li>
											<li><a href="#" id="128">Cap to 128kbit</a></li>
											<li><a href="#" id="256">Cap to 256kbit</a></li>
											<li><a href="#" id="512">Cap to 512kbit</a></li>
											<li><a href="#" id="1024">Cap to 1mbit</a></li>
											<li><a href="#" id="1500">Cap to 1.5mbit</a></li>
											<li><a href="#" id="2000">Cap to 2mbit</a></li>
										</ul>
									</div>
								</div>
							</h3>
						</div>
						<div class="panel-body" id="videolocal"></div>
					</div>
				</div>
<div class="video">
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #1 <span class="label label-info hide" id="remote1"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote1"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #2 <span class="label label-info hide" id="remote2"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote2"></div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #3 <span class="label label-info hide" id="remote3"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote3"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #4 <span class="label label-info hide" id="remote4"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote4"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #5 <span class="label label-info hide" id="remote5"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote5"></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								참여자 #6 <span class="label label-info hide" id="remote5"></span>
							</h3>
						</div>
						<div class="panel-body relative" id="videoremote6"></div>
					</div>
				</div>
			</div>	
			</div>
			
				<div class="footer">
					<!-- 음소거 버튼 -->
					<button class="toggleMute" onclick="toggleMute();">
						<img id="muteIcon" src="./icons/unmute.png" alt="mute">
					</button>

					<!-- 내 화면 공유 -->
					<button onclick="shareScreen()" id="shareScreenBtn">화면 공유</button>
					<video id="myVideo" autoplay muted style="display: none;"></video>

					<!-- 방 나가기 버튼 -->
					<div class="btn-group">
						<button class="btn btn-default" autocomplete="off"
							id="leaveRoomBtn">
							<img src="./icons/leave Room.png" alt="leave Room">
						</button>

						<script>
							// 방 나가기 버튼 클릭 시 모꼬지 페이지로 이동
							document
							.getElementById("leaveRoomBtn")
							.addEventListener(
								"click",
								function() {
									window.location.href = "http://localhost/main"; // 모꼬지 페이지의 URL로 변경
								});
						</script>

					</div>
				</div>
			</div>
		</div>
	</main>

</body>
</html>
<script type="text/javascript" src="/js/janus.js"></script>
<script type="text/javascript" src="/js/videoroomtest.js?ver=12"></script>