<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://code.jquery.com/jquery-3.5.1.min.js'></script>

</head>
<body>
	<h1>로그인 페이지</h1>
	<form action = "/JavaTestWeb/uss" method="post">
		<input type="hidden" name="cmd" value="login">
		<label for="id">아이디를 입력하세요</label><br>
		<input type="text" id="uid" name="uid"><br>
		<label for="pwd">비밀번호를 입력하세요</label><br>
		<input type="text" id="pwd" name="pwd"><br>
	<p>
		<button type="submit">로그인</button>
		<button type="reset">취소</button>
	</p>
	</form>
</body>
</html>