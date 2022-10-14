<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<h3><a href="insertOrEdit.jsp" >Add Meal</a></h3>
<table  border="1" cellspacing="14" cellpadding="10" style="border-collapse:collapse  ">
    <thead>
    <tr>
        <th>#</th>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
                <th></th>
                <th></th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="mealTos" items="${mealTos}">
        <tr>
            <td>${mealTos.id}</td>
<%--            <td style="display: none">${mealTos.id}</td>--%>
            <td style="color:${mealTos.excess ? 'red' : 'green'}">${f:formatLocalDateTime(mealTos.dateTime, 'dd-MM-yyyy HH:mm:ss')}</td>
            <td style="color:${mealTos.excess ? 'red' : 'green'}">${mealTos.description}</td>
            <td style="color:${mealTos.excess ? 'red' : 'green'}">${mealTos.calories}</td>
            <td style="color:blue"><a href="meals?action=edit&id=<c:out value="${mealTos.id}"/>">Update</a></td>
            <td style="color:blue"><a href="meals?action=delete&id=<c:out value="${mealTos.id}"/>">Delete</a></td>
        </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
