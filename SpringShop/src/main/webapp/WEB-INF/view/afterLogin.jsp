<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
</head>
<body>

<h1>You logged</h1>

<h2>List of Products</h2>  

    <table>
        <tr>
            <td>Name</td>
            <td>Price</td>
            <td>Amount</td>
            <td>Add to cart</td>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
            <form:form method="POST">
            		<td><c:out value="${product.name}"/></td>
            		<td><c:out value="${product.price}"/></td>
					<td><input type="text" name="amount" maxlength="4" size="4" /></td>
					<td colspan="2"><input type="submit" value="Add to cart" /></td>
					<td><input type="hidden" name="uniqueProductCode"  value="${product.uniqueProductCode}"/></td>
					<td><input type="hidden" name="price"  value="${product.price}"/></td>
					<td><input type="hidden" name="name"  value="${product.name}"/></td>
				</form:form>
			</tr>
        </c:forEach>
    </table>
    <br/>
    <c:if test="${not empty currentChosenName}">
    	You added ${currentChosenAmount} ${currentChosenName}.
    	<br/>
    	<a href="<c:url value='/after/displayCart' />">Display cart</a>
	</c:if>      
    
</body>
</html>