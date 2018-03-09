<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

}
</style>

<title>Insert title here</title>

</head>
<body>

	<h1>Login page</h1>
	<form:form modelAttribute="user" method="POST">
		<table>
			<tr>
				<td>Login:</td>
				<td><form:input path="username" id="username"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" id="password"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
			<tr>
				<td>${username}<td>
			</tr>
		  </table>
	</form:form>
	<script>
		document.getElementById('username').value = 'asd';
		document.getElementById('password').value = 'asd';
	</script>
</body>
</html>