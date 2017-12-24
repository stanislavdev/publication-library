<%@ page import="controller.JSPLoader" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: dvsta
  Date: 12.12.2017
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        h2 {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            font-size: 20px;
        }

        #publication {
            float: left;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            font-size: 13px;
            border-collapse: collapse;
            width: 49%;
        }

        #publication td, #publication th {
            border: 1px solid #ddd;
            padding: 6px;
        }

        #publication tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #publication tr:hover {
            background-color: #ddd;
        }

        #publication th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        #publication2 {
            float: right;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            font-size: 13px;
            border-collapse: collapse;
            width: 49%;
        }

        #publication2 td, #publication2 th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #publication2 tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #publication2 tr:hover {
            background-color: #ddd;
        }

        #publication2 th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        input[type=text] {
            width: 7%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 3px solid #ccc;
            -webkit-transition: 0.5s;
            transition: 0.5s;
            outline: none;
        }

        input[type=text]:focus {
            border: 3px solid #555;
        }

        #label{
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        }
    </style>
</head>
<body>
<% if (!(request.getParameter("id").equals(""))) { %>
<h2 id="label">References for publication №<%= request.getParameter("id") %> </h2>
<form action="second.jsp">
    <%! JSPLoader loader = new JSPLoader(); %>
    <table id="publication">
        <td>Digital publications</td>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Number of pages</th>
            <th>Date</th>
            <th>Internet link</th>
            <th>Size in bytes</th>
            <th>Key word</th>
        </tr>
        <%= loader.tableToHtmlSelectDigitalReferences(
                Integer.parseInt(request.getParameter("id"))) %>
    </table>
    <table id="publication2">
        <td>Paper publications</td>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Number of pages</th>
            <th>Date</th>
            <th>Name of magazine</th>
            <th>Key word</th>
        </tr>
        <%= loader.tableToHtmlSelectPaperReferences(
                Integer.parseInt(request.getParameter("id"))) %>
    </table>
</form>
<% } else { %>
    <h2>INPUT ID, PLEASE!</h2>
<% }%>
</body>
</html>
