<%@ tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@ attribute name="tagRole" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--<c:if test="${tagRole eq sessionScope['edu.olya.tour.model.User'].role}">--%>
<c:if test="${tagRole eq requestScope.SPRING_SECURITY_LAST_USERNAME}">
    <jsp:doBody/>
</c:if>
