<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/4/26
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>addList</title>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.5/dist/JsBarcode.all.min.js"></script>
    <link rel="stylesheet" type="text/css" href="addList.css">
</head>
<body>

    <table>
        <c:forEach items="${idList}" var="id">
            <tr>
                <td>
                    <svg id="barcode${id}"></svg>
                    <script>JsBarcode("#barcode${id}", "${id}");</script>
                </td>
            </tr>
        </c:forEach>
        <a href="toSearchRequest?id=${user.id}">
            <input type="button" value="OK"/>
        </a>
    </table>
</body>
</html>
