<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
</head>
<style>
.error {
	color: #ff0000;
}

</style>
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
        <c:forEach items="${offer}" var="lineItem">
            <tr>
            <form:form method="POST" modelAttribute="validAmountBean">
            		<td><c:out value="${lineItem.name}"/></td>
            		<td><c:out value="${lineItem.currentPrice}"/></td>
					<td><input type="text" name="amount" maxlength="4" size="4" /></td>
					<td colspan="2"><input type="submit" value="Add to cart" /></td>
					<td><input type="hidden" name="uniqueProductCode"  value="${lineItem.uniqueProductCode}"/></td>
					<td><input type="hidden" name="price"  value="${lineItem.currentPrice}"/></td>
					<td><input type="hidden" name="name"  value="${lineItem.name}"/></td>
				</form:form>
			</tr>
        </c:forEach>
    </table>
    <br/>
    <c:if test="${not empty error}">
		 <td>${error}</td>
	</c:if>   
    <br/>
    <c:if test="${not empty currentChosenName}">
    	You added ${currentChosenAmount} ${currentChosenName}.
	</c:if>     
	<br/> 
    <a href="<c:url value='/main/displayCart' />">Display cart</a>
</body>
</html>