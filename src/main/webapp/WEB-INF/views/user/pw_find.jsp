<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <jsp:include page="/resources/commons/header.jsp" />
<link rel="stylesheet" href="css/find.css"> --%>
<script type="text/javascript" src="/resources/js/find.js"></script>
<title>비밀번호 찾기</title>
</head>
<body>
   <div id="pw_find">
      <div id="table">
         <table>
            <caption id="title">비밀번호 찾기</caption>
            <tr>
               <td class="tr">id</td>
               <td class="td"><input type="text" name="user_id" id="user_id"
                  class="textbox"></td>
            </tr>
            <tr>
               <td class="tr">전화번호</td>
               <td class="td"><input type="text" name="user_tel"
                  id="user_tel" class="textbox"></td>
            </tr>
         </table>
         <button id="user_pw_find">확인</button>
      </div>
   </div>


   <div id="pw_change">
      <div id="table">
         <table>
            <caption id="title">비밀번호 변경</caption>
            <tr>
               <td class="tr">id</td>
               <td class="td"><input type="text" name="user_id" id="user_id"
                  class="textbox"></td>
            </tr>
            <tr>
               <td class="tr">새비밀번호</td>
               <td class="td"><input type="password" name="user_pw"
                  id="user_pw" class="textbox"></td>
            </tr>
            <tr>
               <td class="tr">새비밀번호 확인</td>
               <td class="td"><input type="password" name="user_pw2"
                  id="user_pw2" class="textbox"></td>
            </tr>
         </table>
         <div id="pwdcheck"></div>
         <button id="user_pw_change">확인</button>
      </div>
   </div>

</body>
</html>