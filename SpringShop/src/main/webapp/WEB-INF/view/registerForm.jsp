<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

</style>
<title>Register</title>
</head>
<body>
	<h1>Register page</h1>
	<form:form modelAttribute="userDTO" method="POST">
		<table>
			<tr>
				<td>Login:</td>
				<td><form:input path="username" /></td>
				<td><form:errors path="username" cssClass="error"/><td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
				<td><form:errors path="password" cssClass="error"/><td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error"/><td>
			</tr>
			<tr>
				<td><input type="submit" name="save" value="Save Changes" /></td>
			</tr>
		</table>
	</form:form>
	<c:if test="${not empty alreadyExist}">
		<td>User with chosen nickname already exist in database. Chose another.</td>
	</c:if>
	<br/>
	<a href="<c:url value="/" />">Back to login page</a><br/> 
</body>
</html>