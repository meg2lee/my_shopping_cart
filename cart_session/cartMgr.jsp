<%@page import="java.util.Enumeration"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.tj.session.cart.*" %>
<jsp:useBean id="cart" class="com.tj.session.cart.CartMgr" scope="session"></jsp:useBean>
<jsp:useBean id="item" class="com.tj.session.cart.ItemVO"></jsp:useBean>
<jsp:useBean id="dao" class="com.tj.session.cart.OrderDAO"></jsp:useBean>
<jsp:setProperty name="item" property="*"/>
<%
	String cmd = request.getParameter("cmd");
	String resMsg = "failed";
	if(cmd.equals("order")){
		if(cart.add(item)) resMsg = "saved";
		out.print(resMsg);
	}else if(cmd.equals("show")){
		ArrayList<ItemVO> itemList = cart.getCartItems();
		request.setAttribute("itemList", itemList); //해당 정보를 forward할 경우에는 request에 담아 전달
		request.setAttribute("total", cart.getTotal());// 총 금액 구하는 식
		RequestDispatcher rd = request.getRequestDispatcher("showCart.jsp");//장바구니 페이지로 전달
		rd.forward(request, response); // request, response 객체 모두 전달(제어권 전달)
	}else if(cmd.equals("empty")){
		out.print(cart.empty());
	}else if(cmd.equals("remove")){
		String[] items = request.getParameterValues("items[]");
		out.print(cart.removeSome(items));
	}else if(cmd.equals("update")){ // 'cmd':'update'
		String itemName = request.getParameter("itemName"); //'itemName':itemName
		Integer cnt = Integer.parseInt(request.getParameter("cnt")); // 'cnt':cnt
		boolean updated = cart.updateQty(itemName,cnt); // carMgr servlet
		out.print(updated);
	}else if(cmd.equals("check_login")){
		Object tmpObj = session.getAttribute("uid"); // session영역에 해당 id가 있는지 확인
		String msg = null;
		if(tmpObj!=null) msg ="true";
		else msg = "false";
		out.print(msg);
	}
	else if(cmd.equals("buy")){
		String uid = (String) session.getAttribute("uid");
		boolean saved = dao.save(cart.getCartItems(), cart.getTotal(), uid);
		out.print(saved);
	}

%>
