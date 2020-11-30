<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>장바구니 보기</title>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<style>
	h1,#link{ text-align:center;}
	table{ border:1px solid black; border-collapse: collapse; margin:0 auto;}
	th,td{padding:5px;}
	th { text-align: center; background-color: #ddd;}
	tr.total>td{border:none; text-align: right;}
	tr.total>td:nth-child(5){border-right:1px solid black;}
	td.num { text-align:right;}
	a{ text-decoration: none; }
	td:nth-child(5){text-align: center;}
</style>
<script>
	function cartEmpty(){
		if(!confirm('장바구니를 정말로 비우시겠어요?')) return;
		$.ajax({
			url:'cartMgr.jsp',
			method:'post',
			data:{'cmd':'empty'},
			dataType:'text',
			success:function(res){
				if(Boolean(res.trim())) {
					//$('#items').css('display','none');
					location.reload();
					alert('장바구니를 비웠습니다');
				}else{
					alert('장바구니 비우기 실패하였습니다');
				}
			},
			error:function(xhr,status,error){
				alert(status+', '+error);
			}
		});
	}
	
	function removeSome(){
		var len = $('input[type=checkbox]').length;
		var arr = new Array();
		$('input[type=checkbox]').each(function(){
			if(this.checked){
				arr.push($(this).val()); // check된 아이템을 배열에 저장
			}
		});

		$.ajax({
			url:'cartMgr.jsp',
			method:'post',
			data:{'cmd':'remove', 'items':arr},
			dataType:'text',
			success:function(res){
				if(Boolean(res.trim())){
					alert('장바구니에서 제거했습니다');
					location.reload();
				}
			},
			error:function(xhr,status,error){
				alert(status+', '+error);
			}
		});
		
	}
	
	function updateQty(itemName){
  		
		var cnt = $("input[name=\""+itemName+"\"]").val();
		
		$.ajax({
			url:'cartMgr.jsp',
			method:'post',
			data:{'cmd':'update',
				'itemName':itemName,
				'cnt':cnt},//넘겨줄 data선택
				dataType:'text',
				success:function(res){
					if(Boolean(res.trim())) {
						alert('수량이 변경되었습니다');
						location.reload();
					} else {
						alert('수량 변경에 실패했습니다')
					}	
				},
				error:function(xhr,status,error){
					alert(status+', '+error);
				}
		});
			
	} 
	
	function buy(){

		$.ajax({
			url:'cartMgr.jsp?',
			method: 'post',
			data: {'cmd':'check_login'},
			datatype: 'text',
			success:function(res){
				if(res.trim()=='true') {
					alert('결제페이지로 이동합니다');
					if(confirm("상품을 결제하시겠습니까?")){
					location.href = 'cartMgr.jsp?cmd=buy';// controller인 cartMgr로 보냄
					}
				}else {
					alert('로그인 페이지로 이동합니다');
					location.href = '../uss?cmd=login';
				}
			},
			error:function(xhr,status,error){
				alert(status+', '+error);
			}
		});
	}
	
</script>
</head>
<body>
<h1>장바구니 보기</h1>
<div>
<table border="1">
	<tr><th>카테고리</th><th>상품명</th><th>가 격</th><th>수 량</th>
		<th><a href="javascript:removeSome();">삭 제</a></th></tr>
	<tbody id="items">
	<c:forEach var="i" items="${itemList}">
		<tr>
			<td>${i.category}</td>
			<td>${i.itemName}</td>
			<td class="num">${i.price}</td>
			<td class="num">
				<input type="number" name="${i.itemName}" value="${i.cnt}" min=0>
				<a href="javascript:updateQty('${i.itemName}')">적용</a>
		 	</td>
			<td><input type="checkbox" name="rem" value="${i.itemName}"></td>
		</tr>
	</c:forEach>
	<tr class="total"><td></td><td>합 계</td>
		<td class="num">${total}</td><td></td><td></td></tr>
	</tbody>
</table>
</div>
<hr>
<div id="link">
[<a href="book.jsp">도서상품</a>]
[<a href="usb.jsp">USB 메모리</a>]
[<a href="earset.jsp">이어셋</a>]<br>
[<a href="javascript:removeSome();">삭제</a>]
[<a href="javascript:cartEmpty();">장바구니 비우기</a>]
[<a href="javascript:buy();">결제하기</a>] 
</div>
</body>
</html>
