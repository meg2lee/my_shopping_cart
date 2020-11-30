/**
 * 
 */
function orderItem(cmd,cat,item,price){
	$.ajax({
		url : 'cartMgr.jsp',
		method:'post',
		data:{'cmd':cmd, 'category':cat, 'itemName':item, 'price':price, 'cnt':1},
		dataType:'text',
		success:function(res){
			var msg = res.trim();
			if(msg=='saved'){
				alert('장바구니에 저장했습니다');
			}else{
				alert('장바구니에 저장 중에 오류가 발생했습니다');
			}
		},
		error:function(xhr,staus,error){
			alert(status+', '+error);
		}
	});
}
