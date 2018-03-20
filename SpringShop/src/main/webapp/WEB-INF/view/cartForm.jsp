<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your cart</title>
</head>
<body>
	<table>
		<tr>
			<td>Code</td>
			<td>Name</td>
			<td>Price</td>
			<td>Amount</td>
			<td>Remove from cart</td>
		</tr>
		<c:forEach items="${orders}" var="order">
			<tr>
				<td><c:out value="${order.uniqueProductCode}" /></td>
				<td><c:out value="${order.name}" /></td>
				<td><c:out value="${order.currentPrice}" /></td>
				<td><c:out value="${order.amount}"/></td>
				<form:form method="POST">
					<td><input type="submit" value="remove item" /></td> 
					<td><input type="hidden" name="_method" value="delete"></td> 
				 	<td><input type="hidden" name="uniqueProductCode" value="${order.uniqueProductCode}"></td> 
				</form:form>
			</tr>
		</c:forEach>
	</table>

<form:form method="POST">
 	<table>
 		<tr>
			<td><input type="submit" name="order" value="Make order" /></td>
			<td><input type="submit" name="clear" value="Clear cart" /></td>
			<td><input type="submit" name="back" value="Back to offer" /></td>
		</tr>
	</table>
	</form:form>
</body>
</html>