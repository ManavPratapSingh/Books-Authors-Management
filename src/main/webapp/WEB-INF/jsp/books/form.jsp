<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${book.id == null ? 'Create Book' : 'Edit Book'}</title>
</head>
<body>
    <h1>${book.id == null ? 'Add New Book' : 'Edit Book'}</h1>
    <a href="/books">Back to List</a>
    <hr/>
    <form:form action="${book.id == null ? '/books' : '/books/update'}" method="POST" modelAttribute="book">
        <form:hidden path="id"/>
        <div>
            <label>Title:</label><br/>
            <form:input path="name"/>
            <form:errors path="name" cssStyle="color: red;"/>
        </div>
        <br/>
        <div>
            <label>ISBN:</label><br/>
            <form:input path="isbn"/>
            <form:errors path="isbn" cssStyle="color: red;"/>
        </div>
        <br/>
        <div>
            <label>Genre:</label><br/>
            <form:input path="genre"/>
            <form:errors path="genre" cssStyle="color: red;"/>
        </div>
        <br/>
        <div>
            <label>Author:</label><br/>
            <form:select path="author.id">
                <form:option value="" label="-- Select Author --"/>
                <form:options items="${authors}" itemValue="id" itemLabel="name"/>
            </form:select>
            <form:errors path="author.id" cssStyle="color: red;"/>
        </div>
        <br/>
        <div>
            <label>Description:</label><br/>
            <form:textarea path="description" rows="5" cols="30"/>
            <form:errors path="description" cssStyle="color: red;"/>
        </div>
        <br/>
        <button type="submit">${book.id == null ? 'Create' : 'Update'}</button>
    </form:form>
</body>
</html>
