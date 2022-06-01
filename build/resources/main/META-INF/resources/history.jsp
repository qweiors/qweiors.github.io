<%@ page import="com.example.librarybackend.service.UserServe" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/30
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>history</title>
    <link rel="stylesheet" type="text/css" href="history.css">
</head>
<body>
<div id="borrow">
    <table id = "bookTable"  border="1">
        <tr align="center">
            <th colspan="10">borrow</th>
        </tr>
        <tr>
            <td>ISBN</td>
            <td>id</td>
            <td>name</td>
            <td>publication date</td>
            <td>author name</td>
            <td>subject name</td>
            <td>borrow date</td>
            <td>borrow day</td>
            <td>return</td>
            <td>renew</td>
        </tr>
        <c:forEach items = "${borrows}" var = "borrow">
            <tr>
                <td>${borrow.ISBN}</td>
                <td>${borrow.id}</td>
                <td>${borrow.name}</td>
                <td>${borrow.publicationDate}</td>
                <td>${borrow.authorName}</td>
                <td>${borrow.subjectName}</td>
                <td>${borrow.borrowDate}</td>
                <td>${borrow.borrowDay}</td>
                <td>
                    <a href="returnRequest?bookId=${borrow.id}&userId=${user.id}">return</a>
                </td>
                <td>
                    <a href="renewRequest?bookId=${borrow.id}&userId=${user.id}">renew</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="div_empty"></div>
<div id="reserve2">
    <div style="text-align: center; margin-top: 30px">${errmsg}</div>
    <table id = "reserve"  border="1">
        <tr align="center">
            <th colspan="9">reserve</th>
        </tr>
        <tr>
            <td>ISBN</td>
            <td>id</td>
            <td>name</td>
            <td>publication date</td>
            <td>author name</td>
            <td>subject name</td>
            <td>reserve time</td>
            <td>reserve hour</td>
            <td>borrow</td>
        </tr>
        <c:forEach items = "${reserves}" var = "reserve">
            <tr>
                <td>${reserve.ISBN}</td>
                <td>${reserve.id}</td>
                <td>${reserve.name}</td>
                <td>${reserve.publicationDate}</td>
                <td>${reserve.authorName}</td>
                <td>${reserve.subjectName}</td>
                <td>${reserve.reserveTime}</td>
                <td>${reserve.reserveHour}</td>
                <td>
                    <a href = "borrowReserveBookRequest?bookId=${reserve.id}&userId=${user.id}">
                        borrow
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="div_empty"></div>
<div id="history2">
    <table id="history" border="1">
        <tr align="center">
            <th colspan="4">history</th>
        </tr>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>borrowDate</td>
            <td>returnDate</td>
        </tr>
        <c:forEach items="${logs}" var="log">
            <tr>
                <td>${log.bookId}</td>
                <td>${log.name}</td>
                <td>${log.borrowDate}</td>
                <td>${log.returnDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
