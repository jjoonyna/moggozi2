<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" >
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/css/result.css">
    <title>목록글쓰기</title>
</head>
<body>
    <main>
    <form id="uploadForm" enctype="multipart/form-data" action="/upload" method="post">
       <div class="card">
            <div class="card-header1">
                <h1><a href="/main">방만들기</a></h1>
            </div>
       </div>
       <div class="card-write">
            <div class="myinfo">
                <span>닉네임</span><input type="text" name="usernick" id="usernick" value="${usernick }"  placeholder="이름을 입력하세요." >
                <span>인원</span><select name="mokkojiPerson" id="mokkojiPerson">
                	<option value="" disabled selected>선택</option>
                    <option value="1">1명</option>
                    <option value="2">2명</option>
                    <option value="3">3명</option>
                    <option value="4">4명</option>
                    <option value="5">5명</option>
                    <option value="6">6명</option>
                </select>
                <span>카테고리</span><select name="mokkojiCategory" id="mokkojiCategory">
                	<option value="" disabled selected>선택</option>
                    <option value="운동">운동</option>
                    <option value="게임">게임</option>
                </select>
            </div>
            <div class="title-w" >
                <span>방이름</span><input type="text" name="mokkojiTitle" id="mokkojiTitle" placeholder="방이름을 입력하세요.">
            </div>
            <div class="msg">
                <span>소개글</span><textarea name="mokkojiIntro" id="mokkojiIntro" placeholder="소개글을 입력하세요." ></textarea>
                <div class="file_up"><span class="">파일업로드</span><input type="file" name="mokkojiImages" id="mokkojiImages" placeholder="파일선택" ></div>
            </div>
       </div>
       <div class="btn-w"><button type="submit" >작성</button></div>
       </form>
    </main>
</body>
</html>