<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>

<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        header {
            background: #343a40;
            color: white;
            min-height: 70px;
            width: 100%;
        }
        .blockHeader {
            color: white;
            min-height: 70px;
            width: 50%;
            position: absolute;
            right: 0;
            left: 0;
            margin: auto;
        }
        .a1 {
            background: #e9ecef;
            width: 100%;
            position: absolute;
            min-height: 100%;
        }
        .aa {
            color: black;
            min-height: 70px;
            width: 50%;
            position: absolute;
            right: 0;
            left: 0;
            margin: auto;
        }
        .text-center {
            text-align: center!important;
        }

        .divSection {
            color: black;
            background: white;
        }

        .b0{
            border-color: black;
            border-style: solid;
        }

        .b1 {
            flex-wrap: wrap;
            display: flex;
        }

        .b3 {
            min-height: 40px;
            background: #ececec;
        }

        .b2 {
            border-color: black;
            border-style: solid;
            margin: 10px;
            flex-wrap: wrap;
            display: flex;
        }

        .col1 {
            margin: 10px;
            max-width: 20%;
            word-wrap: break-word;
        }

        .col2 {
            margin: 10px;
            margin-right: 10px;
            margin-left: auto;
            max-width: 25%;
            word-wrap: break-word;
        }
    </style>
</head>
<body>
<header>
    <div class="blockHeader">
        <h2><a style="text-decoration: none; color:white" href="index.html">Подсчёт калорий</a></h2>
    </div>
</header>
<div class="a1">

    <div class="aa">

        <div class="divSection">
            <h2 class="text-center" style="margin-top: 0">Моя еда</h2>
            <form method="post" action="meals">
            <div class=b0>
                <div class="b1">
                    <div class=col1>
                        От даты (включая)
                        <input type="datetime-local" size=15>
                    </div>
                    <div class=col1>
                        До даты (включая)
                        <input type="datetime-local" size=15>
                    </div>
                    <div class=col2>
                        От времени (включая)<br>
                        <input type="time" size=15>
                    </div>
                    <div class=col2>
                        До времени (исключая)<br>
                        <input type="time" size=15>
                    </div>
                </div>
                <div class="b3">
                    <button type="submit">Отфильтровать</button>
                </div>
            </div>
            </form>
        </div>
    <a href="meals?action=create">Add Meal</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
</body>
</html>