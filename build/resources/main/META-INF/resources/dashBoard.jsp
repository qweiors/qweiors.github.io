<%--
  Created by IntelliJ IDEA.
  User: 17567
  Date: 2022/5/16
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dashboard</title>
    <link rel="stylesheet" type="text/css" href="dashBoard.css">
</head>
<body>
    <div class="mainDashBoard">
        <div>
                <strong>
                    Total Registered Readers
                </strong>
                ${memberNum}
        </div>
        <br>
        <div>
            <strong>
                Total Books:
            </strong>
            ${bookNum}
        </div>
        <br>
        <div>
            <strong>
                Total Book Copies:
            </strong>
            ${bookNodistinctNum}
        </div>
        <br>
        <div>
            <strong>
                Total Borrows:
            </strong>
            ${bookBorrows}
        </div>
        <br>
        <div>
            <strong>
                Total Fine (Unpaid):
            </strong>
            ${finedNum}
        </div>
        <br>
        <div>
            <strong>
                Total Lost Books:
            </strong>

        </div>
        <br>
        <div>
            <strong>
                Total Damage Books:
            </strong>

        </div>
        <br>
        <div>
            <strong>
                Total Fine Collected:
            </strong>

        </div>
    </div>
</body>
</html>