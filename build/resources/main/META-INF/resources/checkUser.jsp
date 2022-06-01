<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add</title>
	<script>
        window.onload = function ()
        {
            let check = document.getElementById("check");
            let mainWindow = document.getElementById("mainWindow");
            check.onclick = function ()
            {
                mainWindow.src = "toHistory?id="+document.getElementById("userId").value;
            }
        }
    </script>
</head>
<body>
	userId:<input type="text" id="userId" value="${userId}">
    <input type="button" id="check" value="check">
    <br>
    <iframe id="mainWindow" style="margin-top: 3%;height: 700px;width: 100%">

    </iframe>
</body>
</html>