<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add</title>
	<link rel="stylesheet" type="text/css" href="add.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		const replaceStr = (str, index, char) => {
			return str.substring(0, index) + char + str.substring(index + 1);
		}

		window.onload = function(){
			document.getElementById("ISBNButton").onclick = function (){

				$.ajax({
					type: "GET",
					url: "https://openlibrary.org/api/books",
					data: "bibkeys=ISBN:"+document.getElementById("ISBN").value+"&jscmd=details&format=json",
					dataType: "json",
					success: function (res) {
						document.getElementById("name").value = res['ISBN:'+document.getElementById("ISBN").value].details.title;
						document.getElementById("publication").value = res['ISBN:'+document.getElementById("ISBN").value].details.publish_date;
						try
						{
							document.getElementById("authorName").value = res['ISBN:'+document.getElementById("ISBN").value].details.authors[0].name;
						}
						catch (err)
						{
							document.getElementById("authorName").value = "not Found";
						}
						try
						{
							document.getElementById("subjectName").value = res['ISBN:'+document.getElementById("ISBN").value].details.subjects[0];
						}
						catch (err)
						{
							document.getElementById("subjectName").value = "not Found";
						}
						// $.ajax({
						// 	type: "GET",
						// 	url: "https://openlibrary.org/api/get?key="+key,
						// 	dataType: "json",
						// 	success: function (res) {
						// 		console.log(res);
						// 		document.getElementById("authorName").value = res.result.name;
						// 	},
						// 	error: function (result) {
						// 		alert(result);
						// 	}
						// });
					},
					error: function (result) {
						alert(result);
					}
				});
			}
		}
	</script>
</head>
<body>
	<div id = "addHead">

	</div>
	<div id = "add">
		<form action="addBookRequest">
			<input type = "hidden" name = "userId" value = "${user.id}"/>
			<table id = "informationTable">
				<tr>
					<td>ISBN:</td><td><input type = "text" name = "ISBN" id = "ISBN"/><input type = "button" id = "ISBNButton" value = "add by ISBN"></td>
				</tr>
				<tr>
					<td>title:</td><td><input type = "text" name = "name" id = "name"/></td>
				</tr>
				<tr>
					<td>publication date:</td><td><input type = "text" name = "publication" id = "publication"/></td>
				</tr>
				<tr>
					<td>author name:</td><td><input type = "text" name = "authorName" id = "authorName"/></td>
				</tr>
				<tr>
					<td>subject name:</td><td><input type = "text" name = "subjectName" id="subjectName"/></td>
				</tr>
				<tr>
					<td>location:</td><td><input type = "text" name = "location"/></td>
				</tr>
				<tr>
					<td>num:</td><td><input type = "text" name = "num"/></td>
				</tr>
			</table><font color = "red">${errmsg}</font>
			<div align="center">
			<input id = "submit" type = "submit" value = "add"/>
			</div>
		</form>
	</div>
</body>
</html>