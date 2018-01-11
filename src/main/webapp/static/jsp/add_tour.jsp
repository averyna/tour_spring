<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE>
<html lang="en-us">
<head>
    <title>Добавление тура</title>
	<meta charset="UTF-8">
	<meta name="description" content="Добавление туров. Доступ только для админа.">
	<link rel="stylesheet" href="static/styles/add_tour_style.css">
</head>
<body>

<div class="add_tour">
    <h4>Добавление нового тура</h4>

    <c:if test="${requestScope.invalidParams}">
       <b style="color: darkred;">Кажется, что-то пошло не так. <br/> Попробуйте ввести данные для добавления тура снова.<br/></b>
    </c:if>

    <c:set var = "cache" scope = "page" value = "${applicationScope['edu.olya.tour.utils.cache.Cache']}"/>

    <form:form class="filter_form" action="/tour/mvc/addTour/" method="post" modelAttribute="tour">

        <label for="countryId">Страна</label>
            <form:select id="countryId" path="countryId"  required = "required">
                <option></option>
                <c:forEach var="country" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllCountries()')}" >
                    <option value="${country['id']}">
                        <c:out value="${country['name']}"/>
                    </option>
                </c:forEach>
            </form:select>

        <label for="tourTypeId">Тип тура</label>
            <form:select id="tourTypeId" path="tourTypeId"  required="required">
                <option></option>
                <c:forEach var="type" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllTourTypes()')}" >
                    <option value="${type['id']}">
                        ${type.name}
                    </option>
                </c:forEach>
            </form:select>

        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>
        <label for="startDate">Дата начала тура</label>
        <form:input class="filter_input" type="date" id="startDate" path="startDate" value="${now}" min = "${now}" required="required"/>

        <label for="adults">Количество взрослых</label>
        <form:input class="filter_input" type="number" path="adults" id="adults" min="1" value="1" required="required"/>

        <label for="children">Количество детей</label>
        <form:input class="filter_input" type="number" path="children" id="children" min="0" value="0" required="required"/>

        <label for="nights">Количество ночей</label>
        <form:input class="filter_input" type="number" path="nights" id="nights" min="2" value="7" required="required"/>

        <label for="hotelId">Отель</label>
        <form:select id="hotelId" path="hotelId" required="required">
            <option></option>
                <c:forEach var="hotel" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllHotels()')}" >
                    <option value="${hotel['id']}">
                        ${hotel.name}
                    </option>
                </c:forEach>
        </form:select>

       <label for="mealTypeId">Тип питания</label>
       <form:select id="mealTypeId" path="mealTypeId"  required="required">
           <option></option>
                <c:forEach var="meal" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllMealTypes()')}" >
                    <option value="${meal['id']}">
                        ${meal.name}
                    </option>
                </c:forEach>
       </form:select>

        <label for="price">Цена, BYN</label>
        <form:input class="filter_input" type="number" step="any" path="price" id="price" min="0" value="0" placeholder="0"/>

        <input class="filter_input" type="submit" name="submit_button" value="Добавить тур"/>
    </form:form>
</div>
</body>
</html>
