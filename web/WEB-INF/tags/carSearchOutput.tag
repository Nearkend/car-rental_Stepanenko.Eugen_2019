<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ attribute name="cars" type="java.util.List" %>
<%@ attribute name="user" type="ua.nure.stepanenko.thesis.model.entyty.User" %>

<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>

<%-- Language settings --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<c:if test="${not empty cars}">
    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.class"/></th>
            <th><fmt:message key="page.lang.mark"/></th>
            <th><fmt:message key="page.lang.name"/></th>
            <th><fmt:message key="page.lang.cost"/></th>
        </tr>
        <c:forEach items="${cars}" var="car">
            <tr>
                <td>${car.clazz}</td>
                <td>${car.mark}</td>
                <td>${car.name}</td>
                <td>${car.cost}</td>
                <c:if test="${not empty user && !user.blocked}">
                    <td>
                        <form action="/controller">
                            <input type="hidden" name="command" value="toOrder">
                            <input type="hidden" name="carId" value="${car.id}">
                            <input type="hidden" name="carMark" value="${car.mark}">
                            <input type="hidden" name="carClass" value="${car.clazz}">
                            <input type="hidden" name="carName" value="${car.name}">
                            <input type="hidden" name="carCost" value="${car.cost}">
                            <input type="hidden" name="carThereIs" value="${car.thereIs}">
                            <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.order"/>">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>