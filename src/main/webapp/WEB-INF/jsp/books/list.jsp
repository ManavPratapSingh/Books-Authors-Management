<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <h1>Books</h1>
    <a href="/books/new">Add New Book</a> | <a href="/authors">View Authors</a>
    <hr/>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Description</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.name}</td>
                    <td>${book.isbn}</td>
                    <td>${book.genre}</td>
                    <td>${book.author.name}</td>
                    <td>${book.description}</td>
                    <td>
                        <fmt:parseDate value="${book.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
                        <fmt:formatDate value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" />
                    </td>
                    <td>
                        <a href="/books/edit/${book.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
