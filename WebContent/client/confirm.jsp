<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认支付</title>
</head>
<body>
	<!-- 确认支付form -->
	<form action="${pageContext.request.contextPath }/orderstate" method="post">
		<h3>订单号：${orderid},付款金额 ：${money}</h3>
			<input type="hidden" name="orderid" value="${orderid}"/> 
			<input type="submit" value="确认支付" />
	</form>
</body>
</html>