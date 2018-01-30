<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log in page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/styles/login.css">
</head>
<body>
<div class="login-page">
    <div class="form">
        <form name="f" action="do_login" class="login-form" method="post">
            <input type="text" name="username" placeholder="имя"/>
            <input type="password" name="password" placeholder="пароль"/>
            <button type="submit">Войти</button>
            <p class="message"><a href="/tour/mvc/register/">Регистрация</a></p>
            <p class="message">Введите <strong> admin / admin </strong> чтобы получить доступ ко всем страницам</p>
        </form>
    </div>
</div>
</body>
</html>