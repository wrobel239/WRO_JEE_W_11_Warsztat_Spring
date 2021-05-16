<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 16.05.2021
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book details</title>
</head>
<body>

<table border="1">
    <thead>
    <th>id</th>
    <th>isbn</th>
    <th>title</th>
    <th>author</th>
    <th>publisher</th>
    <th>type</th>
    </thead>
    <tbody>
    <tr>
        <td><c:out value="${book.id}"/></td>
        <td><c:out value="${book.isbn}"/></td>
        <td><c:out value="${book.title}"/></td>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.publisher}"/></td>
        <td><c:out value="${book.type}"/></td>
    </tr>
    </tbody>
</table>
<a href="<c:out value="/admin/books/all"/>">Lista książek</a>
</body>
