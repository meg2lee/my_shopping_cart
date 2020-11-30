<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>이어셋 구입 페이지</title>
<style>
	div{border:1px solid black; padding:10px;}
	a{ text-decoration: none; }
</style>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script src="ajax_order.js"></script>
</head>
<body>
<h1>다음 이어셋 중에서 하나를 선택해주세요</h1>
<div>
<a href="javascript:orderItem('order','이어셋','프리미엄 골드', 12000);">프리미엄 골드(12000)</a><br>
<a href="javascript:orderItem('order','이어셋','화이트 사운드', 27000);">화이트 사운드(27000)</a><br>
<a href="javascript:orderItem('order','이어셋','리얼 사운드', 34000);">리얼 사운드(34000)</a><br>
</div>
<hr>
[<a href="book.jsp">USB 메모리</a>]
[<a href="usb.jsp">USB 메모리</a>]
[<a href="earset.jsp">이어셋</a>]
[<a href="cartMgr.jsp?cmd=show">장바구니 보기</a>]
</body>
</html>
