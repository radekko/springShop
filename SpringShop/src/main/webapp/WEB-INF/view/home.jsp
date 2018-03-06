<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Choose action</h1>
<a href="<c:url value="/login" />">Login</a>
<a href="<c:url value="/register" />">Register new user</a>
</body>
</html>