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

<h1>You are logged</h1>

 <c:if test="${not empty message}">
	${message ? "Order successfull!" : "Empty cart"}
	<br/> <br/> 
</c:if>  


Select category:

<form:form modelAttribute="category" action="displayOffer" method="GET">
  <form:select  path="categoryName">
    <form:options items="${categoriesList}"></form:options>
  </form:select>
  <input type="submit" value="Get items" />
</form:form>


	
<h2>List of Products in ${categoryName}</h2>  

    <table>
        <tr>
            <td>Name</td>
            <td>Price</td>
            <td>Amount</td>
            <td>Add to cart</td>
        </tr>
        <c:forEach items="${offer.items}" var="lineItem">
            <tr>
				<form:form method="POST" modelAttribute="lineItem">
            		<td><c:out value="${lineItem.name}"/></td>
            		<td><c:out value="${lineItem.currentPrice}"/></td>
					<td><form:input path="amount" value="3"/></td>
					<td colspan="2"><input type="submit" value="Add to cart" /></td>
					<td><form:input type="hidden" path="uniqueProductCode"  value="${lineItem.uniqueProductCode}"/></td>
					<td><form:input type="hidden" path="currentPrice"  value="${lineItem.currentPrice}"/></td>
					<td><form:input type="hidden" path="name"  value="${lineItem.name}"/></td>
				</form:form>
			</tr>
        </c:forEach>
    </table>

    <c:forEach items="${offer.navigationPages}" var = "page">
       <c:if test="${page != -1 }">
         <a href="${requestScope['javax.servlet.forward.request_uri']}?categoryName=${categoryName}&page=${page}">${page}</a>
       </c:if>
       <c:if test="${page == -1 }">
             <span> ... </span>
       </c:if>
    </c:forEach>
   
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