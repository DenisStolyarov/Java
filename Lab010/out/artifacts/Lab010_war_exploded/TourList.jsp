<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 11.05.2020
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<h2>Item List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <tr><th>Name</th><th>Price</th><th></th></tr>
    <c:forEach var="item" items="${items}">
        <tr><td>${item.name}</td>
            <td>${item.price}</td>
            <td>
                <a href='<c:url value="/edit?id=${item.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${item.id}">
                    <input type="submit" value="Delete">
                </form>
            </td></tr>
    </c:forEach>
</table>
<form action="index.jsp">
    <input type="submit" value="Log out">
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
