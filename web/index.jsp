<%@ page import="controller.JSPLoader" %><%--
  Created by IntelliJ IDEA.
  User: dvsta
  Date: 03.12.2017
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<form action="index.jsp">
    <%! JSPLoader loader = new JSPLoader(); %>
    <table>
        <%= loader.tableToHtmlSelect() %>
    </table>
</form>
</body>
</html>
