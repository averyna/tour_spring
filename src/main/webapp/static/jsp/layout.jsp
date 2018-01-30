<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE>
<html lang="ru-ru">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="description" content="Турагентство Сусанин Тур. Отдых, курорты, билеты. ">
    <meta name="keywords" content="отдых, туры, путевки">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="http://localhost:8080/tour/"/>
    <link rel="shortcut icon" href="static/icons/right-footprint.ico" type="image/x-icon">
    <link rel="stylesheet" href="static/styles/layout_style.css">
    <script src="static/scripts/layout_script.js"></script>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="header_box">
            <h2>Турагентство "СусанинТур" </h2>
            <ul>
                <li><i>Активный отдых</i></li>
                <li><i>Оздоровительные программы</i></li>
                <li><i>Экскурсии</i></li>
            </ul>
        </div>
        <div class="header_box"></div>
        <div class="header_box" id="right">
            <button onclick="callButtonClick()">Заказать звонок менеджера</button>
            <div id="panel">
                <form>
                    <input type="text" name="firstname" id="firstname" placeholder="Введите Ваше имя.." pattern="^[А-Яа-я]+$" required>
                    <input type="text" name="phone" id="phone" placeholder="Введите номер телефона .." required>
                    <input type="submit" value="Позвоните мне!" onclick="return callSubmitButtonClick(this.form)">
                </form>
            </div>
            <c:set var="user" value="${sessionScope['edu.olya.tour.model.User']}"/>

            <sec:authorize access="isAnonymous()">
                <ul>
                    <li><a href="/tour/login.jsp">Вход</a></li>
                    <li><a href="/tour/mvc/register/">Регистрация</a></li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <ul>
                    <li>
                        <h4>Добро пожаловать,
                        <sec:authentication property="name"/>
                        </h4>
                    </li>
                    <c:url var="logoutUrl" value="/do_logout"/>
                    <li>
                        <a href="${logoutUrl}">Log out</a>
                    </li>
                </ul>
            </sec:authorize>
        </div>
    </div>

    <div class="topnav">
        <ul>
            <li><a href="/tour/mvc/view?page=index.jsp">Главная</a></li>
            <li><a href="/tour/mvc/tourSearch/">Подбор тура</a></li>
            <li><a href="/tour/mvc/tourSearch/">Горящие туры</a></li>
            <li><a href="/tour/mvc/view?page=contacts.jsp">Контакты</a></li>
            <li><a href="/tour/mvc/comment/">Отзывы</a></li>
            <sec:authorize access="hasAuthority('admin')">
                <li><a href="/tour/mvc/addTour/">Добавить тур</a></li>
                <li><a href="/tour/mvc/delTour/">Удалить тур</a></li>
            </sec:authorize>
        </ul>
    </div>

    <div class= "central_part">
        <%
            String pageName = (String)request.getAttribute("page");
        %>
        <jsp:include page="<%=pageName%>"/>
    </div>

    <div id="subscription">
        <div id="mailing">
            <p>Узнавайте первыми о горящих турах по электронной почте!!!</p>
            <button onclick="mailingClick()">Подписаться на рассылку</button>
            <div id="mailingPanel">
                <form class="mailing_form" action="" method="post">
                    <input type="text" placeholder="Введите Ваше имя.." name="firstname" maxlength="20" pattern="^[А-Яа-я]+$" required>
                    <input type="email" name = "mail" placeholder="Введите email .." maxlength="50" required>
                    <input type="submit" value="Хочу получать рассылку!" onclick="return mailSubmitButtonClick(form)">
                </form>
            </div>
        </div>
    </div>

    <div class="footer">
        <div class= "footer_box">
            <h4>Наши контакты</h4>
            <ul>
                <li><i>8(017)2737373</i></li>
                <li><i>8(044)2222222</i></li>
                <li><p><i>
                    <a href="mailto:olga_averyna@mail.ru?subject=Заголовок&body=Текст_письма.">susanin_tur@gmail.com</a>
                </i> </p></li>
            </ul>
        </div>
        <div class= "footer_box">
            <h4 >Мы в социальных сетях</h4>
            <ul class="social">
                <li><a href = "http://twitter.com" title="Twitter">
                    <img src="static/icons/black-twitter-24.ico"></a></li>
                <li><a href="http://google.com" title="Google">
                    <img src="static/icons/black-google-plus-24.ico"> </a></li>
                <li><a href="http://facebook.com" title="Facebook">
                    <img src="static/icons/black-facebook-24.ico"></a></li>
                <li><a href = "http://instagram.com" title="Instagram">
                    <img src="static/icons/black-instagram-24.ico"></a></li>
            </ul>
        </div>
        <div class= "footer_box">
            <h4 >Ссылки</h4>
            <ul>
                <li>
                    <form action="http://www.google.com/search" class="searchform" method="get"
                          name="searchform" target="_blank">
                        <input autocomplete="on" class="form-controls search"
                               name="q" placeholder="Поиск в Google" required="required"  type="text">
                        <button class="button" type="submit">Поиск</button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
