<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 11.05.2020
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Item</title>
</head>
<body>

<jsp:include page="Header.jsp"/>

<h3>New Item</h3>
<form method="post">
    <label>Name</label><br>
    <input name="name"/><br><br>
    <label>Price</label><br>
    <input name="price"/><br><br>
    <input type="submit" value="Save" />
</form>
</body>

<jsp:include page="Footer.jsp"/>
</html>
