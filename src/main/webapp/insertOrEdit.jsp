<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Edit Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<br>

<form method="POST" action='meals'>
    Id : <input
        type="text" name="id" readonly="readonly"
        value="<c:out value="${meal.id}"/>"/> <br/>
    <br>

    Date Time : <input
        type="datetime-local" name="dateTime"
        value="<c:out value="${meal.dateTime}"/>"/> <br/>
    <br>
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}"/>"/> <br/>
    <br>
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}"/>"/> <br/>
    <br>
    <input type="submit" value="Save"/>
    <%--    <input type="button" value="Cancel"/>--%>
</form>
</body>
</html>