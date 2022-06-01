<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/29
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>borrow</title>
    <link rel="stylesheet" type="text/css" href="search.css">
</head>
<body>
    <div id = "option">
        <table id = "optionTable">
            <tr>
                <td>
                    <form action = "BsearchByPublicationRequest">
                        <input type = "text" name = "publication" value = "${publication}">
                        <input type = "hidden" name = "id" value = "${user.id}">
                        <input type = "submit" value = "searchByPublication">
                    </form>
                </td>
                <td>
                    <form action = "BsearchBySubjectRequest">
                        <input type = "text" name = "subjectName" value = "${subjectName}">
                        <input type = "hidden" name = "id" value = "${user.id}">
                        <input type = "submit" value = "searchBySubject">
                    </form>
                </td>
                <td>
                    <form action = "BsearchByAuthorRequest">
                        <input type = "text" name = "authorName" value = "${authorName}">
                        <input type = "hidden" name = "id" value = "${user.id}">
                        <input type = "submit" value = "searchByAuthor">
                    </form>
                </td>
                <td>
                    <form action = "BsearchByTitleRequest">
                        <input type = "text" name = "name" value = "${name}">
                        <input type = "hidden" name = "id" value = "${user.id}">
                        <input type = "submit" value = "searchByTitle">
                    </form>
                </td>
            </tr>
        </table>
        <a class="search" href = "BsearchAllRequest?id=${user.id}">
            <input type = "button" value = "searchAll">
        </a>
    </div>
    <div style="text-align: center; margin-top: 30px">${errmsg}</div>
    <div id = "bookInformation">
        <table id = "bookTable"  border="1">
            <tr>
                <td>ISBN</td>
                <td>name</td>
                <td>publication date</td>
                <td>author name</td>
                <td>subject name</td>
                <td>location</td>
                <td>not borrow num</td>
                <td>borrow</td>
                <td>reserve</td>
            </tr>
            <c:forEach items = "${books}" var = "book">
                <tr>
                    <td>${book.ISBN}</td>
                    <td>${book.name}</td>
                    <td>${book.publication}</td>
                    <td>${book.authorName}</td>
                    <td>${book.subjectName}</td>
                    <td>${book.location}</td>
                    <td>${book.num}</td>
                    <td>
                        <a href = "borrowBookRequest?ISBN=${book.ISBN}&userId=${user.id}">
                            borrow
                        </a>
                    </td>
                    <td>
                        <a href = "reserveBookRequest?ISBN=${book.ISBN}&userId=${user.id}">
                            reserve
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
