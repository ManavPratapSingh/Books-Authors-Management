<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${author.id == null ? 'Create Author' : 'Edit Author'}</title>
</head>
<body>
    <h1>${author.id == null ? 'Add New Author' : 'Edit Author'}</h1>
    <a href="/authors">Back to List</a>
    <hr/>
    <form:form action="${author.id == null ? '/authors' : '/authors/update'}" method="POST" modelAttribute="author">
        <form:hidden path="id"/>
        <div>
            <label>Name:</label><br/>
            <form:input path="name"/>
            <form:errors path="name" cssStyle="color: red;"/>
        </div>
        <br/>
        <div>
            <label>Bio:</label><br/>
            <form:textarea path="bio" rows="5" cols="30"/>
            <form:errors path="bio" cssStyle="color: red;"/>
        </div>
        <br/>
        <button type="submit">${author.id == null ? 'Create' : 'Update'}</button>
    </form:form>
</body>
</html>
