<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/tour/static/styles/login.css">
</head>
<body>

<div class="login-page">
    <div class="form">
        <form:form action="/tour/mvc/register/" class="register-form" method="post" id="registration_form" modelAttribute="user">
            <form:input type="text" path="name" name="name" placeholder="имя"/>
            <form:input type="password" path="password" name="password" placeholder="пароль"/>
            <%--<input type="password" name="confirm_password" placeholder="подтвердите пароль"/>--%>
            <button>зарегистрироваться</button>
            <p class="message">Уже регистрировались? <a href="/tour/login.jsp">Вход</a></p>
        </form:form>
    </div>
</div>
</body>
</html>
