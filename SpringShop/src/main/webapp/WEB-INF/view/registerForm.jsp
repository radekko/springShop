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
<title>Insert title here</title>
</head>
<body>
	<h1>Register page</h1>
	<form:form modelAttribute="user" method="POST">
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
				<td colspan="2"><input type="submit" value="Save Changes" /></td>
			</tr>
		</table>
	</form:form>
	  <c:if test="${not empty alreadyExist}">
		 <td>${alreadyExist}</td>
	</c:if>   
</body>
</html>