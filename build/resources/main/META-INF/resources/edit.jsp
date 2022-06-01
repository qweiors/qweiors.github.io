<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>edit</title>
	<link rel="stylesheet" type="text/css" href="edit.css">
</head>
<body>
	<div id = "editHead">
		<font>Edit Page</font>
	</div>
	<div id = "edit">
		<form action="editBookRequest">
			<input type = "hidden" name = "userId" value = "${user.id}"/>
			<input type = "hidden" name = "ISBN" value = "${book.ISBN}"/>
			<table id = "informationTable">
				<tr>
					<td>title:</td><td><input type = "text" name = "name" value = "${book.name}"/></td>
				</tr>
				<tr>
					<td>publication date:</td><td><input type = "text" name = "publication" value = "${book.publication}"/></td>
				</tr>
				<tr>
					<td>author name:</td><td><input type = "text" name = "authorName" value = "${book.authorName}"/></td>
				</tr>
				<tr>
					<td>subject name:</td><td><input type = "text" name = "subjectName" value = "${book.subjectName}"/></td>
				</tr>
				<tr>
					<td>location:</td><td><input type = "text" name = "location" value = "${book.location}"/></td>
				</tr>
			</table>
			<c:if test="${errmsg != null}">
	            <script>
	            	alert("edit error!");
	            </script>
            </c:if>
			<br><input id = "submit" type = "submit" value = "edit"/>
		</form>
	</div>
</body>
</html>