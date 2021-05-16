<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 16.05.2021
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Book edit</title>
</head>
<body>

Book
<form:form method="post" modelAttribute="book" action="/admin/books/edit">

    <form:hidden path="id"/>

    <label for="isbn">isbn</label>
    <form:input path="isbn" id="isbn"/>
    <form:errors path="isbn" cssClass="error"/>
    <br>

    <label for="title">title</label>
    <form:input path="title" id="title"/>
    <form:errors path="title" cssClass="error"/>
    <br>

    <label for="author">author</label>
    <form:input path="author" id="author"/>
    <form:errors path="author" cssClass="error"/>
    <br>

    <label for="publisher">publisher</label>
    <form:input path="publisher" id="publisher"/>
    <form:errors path="publisher" cssClass="error"/>
    <br>

    <label for="type">type</label>
    <form:input path="type" id="type"/>
    <form:errors path="type" cssClass="error"/>
    <br>

    <input type="submit" value="Save">
</form:form>

</body>
</html>
