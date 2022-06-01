<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>changePassword</title>
</head>
<body>
	<form action="changePasswordRequest" method="post">
		${errmsg}
		<input type="hidden" name="userId" value="${user.id}">
		<table>
			<tr>
				<td>old password:</td><td><input type="text" name="oldPassword"></td>
			</tr>
			<tr>
				<td>new password:</td><td><input type="text" name="newPassword"></td>
			</tr>
		</table>
		<input type="submit">
	</form>
</body>
</html>