<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
</head>

<body>

	<h1>Orders</h1>
	<table>

		<tr>
			<c:forEach items="${order.items}" var="lineItem">
				<tr bgcolor="#41bce6">
					<td>Username</td>
					<td>OrderIdentifier</td>
				</tr>
				<tr bgcolor="#92f3ef">
					<td><c:out value="${lineItem.username}" /></td>
					<td><c:out value="${lineItem.orderIdentifier}" /></td>
				</tr>
				<tr bgcolor="#69b418">
					<td>Name</td>
					<td>CurrentPrice</td>
					<td>Amount</td>
				</tr>
				<tr bgcolor="green">
					<c:forEach items="${lineItem.items}" var="items">
						<tr bgcolor="#a2e5a6">
							<td><c:out value="${items.name}" /></td>
							<td><c:out value="${items.currentPrice}" /></td>
							<td><c:out value="${items.amount}" /></td>
						</tr>
						<%-- 					<td><c:out value="${lineItem.items.amount}"/></td> --%>
					</c:forEach>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</c:forEach>
		</tr>
	</table>

	<c:forEach items="${order.navigationPages}" var="page">
		<c:if test="${page != -1 }">
			<a
				href="${requestScope['javax.servlet.forward.request_uri']}?page=${page}">${page}</a>
		</c:if>
		<c:if test="${page == -1 }">
			<span> ... </span>
		</c:if>
	</c:forEach>
	<br/>
	<a href="<c:url value='/logout' />">Log out</a>
</body>
</html>