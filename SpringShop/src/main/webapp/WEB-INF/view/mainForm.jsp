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

<c:if test="${success eq true}">
  Order successfull!
</c:if>

<h2>List of Products</h2>  

    <table>
        <tr>
            <td>Name</td>
            <td>Price</td>
            <td>Amount</td>
            <td>Add to cart</td>
        </tr>
        <c:forEach items="${offer.list}" var="lineItem">
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
    
    
<%--     <c:if test="${offer.totalPages > 1}"> --%>
<%--           <c:forEach items="${offer.navigationPages}" var = "page"> --%>
<%--                 <a href="${pageContext.request.contextPath}/productList?page=${page}">${page}</a> --%>
<%--           </c:forEach> --%>
<%--    </c:if> --%>
   
    <c:if test="${offer.totalPages > 1}">
<!--        <div class="page-navigator"> -->
          <c:forEach items="${offer.navigationPages}" var = "page">
<%--               <c:if test="${page != -1 }"> --%>
                <a href="${pageContext.request.contextPath}/${currentPath}/productList?page=${page}" class="nav-item">${page}</a>
<%--               </c:if> --%>
<%--               <c:if test="${page == -1 }"> --%>
<!--                 <span class="nav-item"> ... </span> -->
<%--               </c:if> --%>
          </c:forEach>
          
<!--        </div> -->
   </c:if>
   
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