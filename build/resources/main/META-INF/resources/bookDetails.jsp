<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/28
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>details</title>
    <link rel="stylesheet" type="text/css" href="bookDetails.css">
</head>
<body>
    <div id = "bookInformation">
        <table id = "bookTable"  border="1">
            <tr>
                <td>ISBN</td>
                <td>id</td>
                <td>name</td>
                <td>publication date</td>
                <td>author name</td>
                <td>subject name</td>
                <td>location</td>
                <c:if test = "${user.admin == 49}">
                    <td>delete</td>
                </c:if>
            </tr>
            <c:forEach items = "${books}" var = "book">
                <tr>
                    <td>${book.ISBN}</td>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.publication}</td>
                    <td>${book.authorName}</td>
                    <td>${book.subjectName}</td>
                    <td>${book.location}</td>
                    <c:if test = "${user.admin == 49}">
                        <td>
                            <a href = "removeOneBookRequest?id=${book.id}&userId=${user.id}">
                                delete
                            </a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
