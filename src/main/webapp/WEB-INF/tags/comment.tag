<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="comments" type="java.util.Collection" required="true" rtexprvalue="true" %>
<%@ attribute name="id" type="java.lang.String" required="true" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${comments != null}">
    <c:forEach var="comment" items="${comments}" >
        <div class="comment_box">

            <div class="author_date">
                <div class="inner">
                    <c:out value="${comment['author']}" />
                </div>
                <div class="inner">
                    <c:out value="${comment['date']}" />
                </div>
                     <sec:authorize access="hasAuthority('admin')">
                        <form class="del_comment">
                            <input type="hidden" class="comment_tag_input" id="comment_id" name="comment_id" value="${comment.id}">
                            <input type="submit" name="comment_submit_form" value="Удалить отзыв" onclick="return confirm(this.form)">
                        </form>
                    </sec:authorize>
            </div>

            <div class="comment">
                <c:out value="${comment['comment']}"/>
            </div>

        </div>
    </c:forEach>
</c:if>



<%--<c:if test="${comments != null}">--%>
    <%--<c:forEach var="comment" items="${comments}" >--%>
        <%--<div class="comment_box">--%>

            <%--<div class="author_date">--%>
              <%--<div class="inner">--%>
                <%--<c:out value="${comment['author']}" />--%>
              <%--</div>--%>
              <%--<div class="inner">--%>
                <%--<c:out value="${comment['date']}" />--%>
              <%--</div>--%>
            <%--</div>--%>

            <%--<div class="comment">--%>
              <%--<c:out value="${comment['comment']}" />--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</c:forEach>--%>
<%--</c:if>--%>
