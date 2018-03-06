<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h1>Witaj</h1>
		<table>
			<tr>
				<td>Login:</td>
				<td><c:out value="${user.username}"/></td>
				<td><form:input path="user.username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><c:out value="${user.password}"/></td>
			</tr>
		</table>

</body>
</html>