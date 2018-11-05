<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
</head>

<h3>Select user:</h3>

<form:form modelAttribute="userDTO" action="order" method="GET">
  <form:select path="username">
  	<form:option value="" label="--All--"/>
    <form:options items="${usersList}"></form:options>
  </form:select>
  <input type="submit" value="Get orders" />
</form:form>

<body>
	<h1>Orders</h1>
	<c:if test="${not empty username}">
		 <h3>made by: ${username}</h3>
	</c:if> 
	<h3>Total: ${totalOrders}</h3>
	<table>
		<tr>
			<c:forEach items="${orderDTO}" var="order">
				<tr bgcolor="#41bce6">
					<td>Username</td>
					<td>OrderIdentifier</td>
				</tr>
				<tr bgcolor="#92f3ef">
					<td><c:out value="${order.username}" /></td>
					<td><c:out value="${order.orderIdentifier}" /></td>
				</tr>
				<tr bgcolor="#69b418">
					<td>Name</td>
					<td>CurrentPrice</td>
					<td>Amount</td>
				</tr>
				<tr bgcolor="green">
					<c:forEach items="${order.lineItemDTO}" var="items">
						<tr bgcolor="#a2e5a6">
							<td><c:out value="${items.name}" /></td>
							<td><c:out value="${items.currentPrice}" /></td>
							<td><c:out value="${items.amount}" /></td>
						</tr>
					</c:forEach>
				</tr>
				<form:form method="POST">
					<td><input type="submit" value="Accomplished" /></td> 
				 	<td style="display:none"><input type="hidden" name="orderIdentifier" value="${order.orderIdentifier}"></td> 
				</form:form>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</tr>
	</table>

	<c:forEach items="${navigationPages}" var="page">
		<c:if test="${page != -1 }">
			<a
				href="${requestScope['javax.servlet.forward.request_uri']}?username=${username}&&page=${page}">${page}</a>
		</c:if>
		<c:if test="${page == -1 }">
			<span> ... </span>
		</c:if>
	</c:forEach>
	<br/>
	<a href="<c:url value='/logout' />">Log out</a>
</body>
</html>