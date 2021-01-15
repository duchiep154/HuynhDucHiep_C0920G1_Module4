<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: No Name VN
  Date: 1/5/2021
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/student/edit" method="post" modelAttribute="student">
    <div>
        <label>ID:</label>
        <form:input type="text" name="id"  path="id"/>
    </div>
    <div>
        <label>Name:</label>
        <form:input type="text" name="id"  path="name"/>
    </div>
    <div>
        <label>Gender:</label>
        <form:radiobutton path="gender" value="0" label="Female" />
        <form:radiobutton path="gender" value="1" label="Male" />
        <form:radiobutton path="gender" value="2" label="LGBT" />
    </div>
    <div>
        <label>Languages:</label>
        <form:checkboxes path="languages" items="${languageList}" />
    </div>
    <input type="submit" value="Edit">
</form:form>

</body>
</html>
