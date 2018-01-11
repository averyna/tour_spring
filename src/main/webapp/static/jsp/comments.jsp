<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE>
<html lang="en-us">
<head>
    <title>Отзывы</title>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="description" content="Отзывы о работе турагентства">
    <meta name="keywords" content="поиск туров, отдых, билеты">
    <link rel="stylesheet" href="static/styles/comments_style.css">
    <script src="static/scripts/layout_script.js"></script>
</head>
<body>
<div id="comments_list" class="comments_list">
    <my:comment id="comment_tag" comments="${requestScope['comments']}"/>
</div>

<div class="comment_box">
    <form:form action="/tour/mvc/comment/" method="post" id="comment_form" modelAttribute="comment">
        <div class="author_date">
            <label class="inner">Введите Ваше имя</label>
            <form:input class="comment_input" type="text" path="author" name="author" pattern="^[А-Я а-я]+$" />
        </div>
        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>
        <form:input type="hidden" path="date" name="date" value="${now}"/>
        <form:textarea class="comment" path="comment" name="comment"  rows="3"
                  placeholder="Введите Ваш отзыв..." />
        <input class="comment_submit_button" type="submit" name="comment_submit_form" value="Добавить отзыв"/>

    </form:form>
</div>
</body>
</html>


<%--<div class="comment_box">--%>
    <%--<form action="/tour/mvc/comment/" method="post" id="comment_form">--%>
        <%--<div class="author_date">--%>
            <%--<label class="inner">Введите Ваше имя</label>--%>
            <%--<input class="comment_input" type="text" name="author" pattern="^[А-Я а-я]+$" />--%>
        <%--</div>--%>
        <%--<jsp:useBean id="now" class="java.util.Date"/>--%>
        <%--<fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>--%>
        <%--<input type="hidden" name="date" value="${now}"/>--%>
        <%--<textarea class="comment" name="comment"  rows="3"--%>
                  <%--placeholder="Введите Ваш отзыв..." required></textarea>--%>
        <%--<input class="comment_submit_button" type="submit" name="comment_submit_form" value="Добавить отзыв"/>--%>

    <%--</form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
