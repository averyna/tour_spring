<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en-us">
<head>
    <title>Удаление тура</title>
	<meta charset="UTF-8">
	<meta name="description" content="Удаление туров. Доступ для админа.">
	<link rel="stylesheet" href="static/styles/del_tour_style.css">
</head>
<body>

<div class="central sideform">

    <h4>Выберите параметры для удаления тура </h4>

    <c:if test="${requestScope.invalidParams}">
       <b style="color: darkred;">Что-то пошло не так. <br/> Попробуйте ввести данные для удаления тура снова.<br/></b>
    </c:if>

    <c:set var = "cache" scope = "page" value = "${applicationScope['edu.olya.tour.utils.cache.Cache']}"/>

    <form class="filter_form" action="/tour/mvc/delTour/" method="post">

        <label for="country">Страна</label>
            <select id="country" name="country">
                <option></option>
                    <c:forEach var="country" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllCountries()')}" >
                        <option >
                            <c:out value="${country['name']}"/>
                        </option>
                    </c:forEach>
            </select>

        <label for="tourType">Тип тура</label>
            <select id="tourType" name="tourType">
                <option></option>
                    <c:forEach var="type" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllTourTypes()')}" >
                        <option >
                            ${type.name}
                        </option>
                    </c:forEach>
            </select>

        <label for="startDate">Дата начала тура</label>
        <input class="filter_input" type="date" id="startDate" name="startDate"/>

        <label for="adults">Количество взрослых</label>
        <input class="filter_input" type="number" name="adults" id="adults" min="0"  />

        <label for="children">Количество детей</label>
        <input class="filter_input" type="number" name="children" id="children" min="0"  />

        <label for="nights">Количество ночей</label>
        <input class="filter_input" type="number" name="nights" id="nights" min="0" />

        <label for="hotel">Отель</label>
        <select id="hotel" name="hotel">
            <option></option>
                <c:forEach var="hotel" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllHotels()')}" >
                    <option>
                        ${hotel.name}
                    </option>
                </c:forEach>
        </select>

       <label for="mealType">Тип питания</label>
               <select id="mealType" name="mealType">
                   <option></option>
                        <c:forEach var="meal" items="${cache.getValue('List edu.olya.tour.service.FilterService.getAllMealTypes()')}" >
                            <option value="${meal['id']}">
                                ${meal.name}
                            </option>
                        </c:forEach>
               </select>

        <label for="price">Цена, BYN</label>
        <input class="filter_input" type="number" step="any" name="price" id="price" min="0" />

        <input class="filter_input" type="submit" name="submit_button" value="Выбрать параметры"/>
    </form>
</div>

<div class="central content">
    <c:if test="${requestScope.tours != null}">
          <my:tour-table-delete tours="${requestScope['tours']}"/>
    </c:if>

    <c:if test="${requestScope.deleted != null}">
           <b style="color: darkred;"> Количество удаленных туров:  <c:out value="${requestScope.deleted}" /><br/></b>
    </c:if>
</div>
</body>
</html>
