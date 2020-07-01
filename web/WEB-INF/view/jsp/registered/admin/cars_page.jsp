<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Cars" scope="page"/>
<%@include file="/WEB-INF/view/jspf/common/head.jspf" %>
<body>

<%-- Menu --%>
<ul>
    <li><a href="/controller?command=toMainPage"><fmt:message key="page.lang.main"/></a></li>
    <%@include file="/WEB-INF/view/jspf/registrated/client/personal_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/registrated/manager/manage_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/registrated/admin/admin_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/language_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/logout_menu.jspf" %>
</ul>
<hr/>

<%-- All cars --%>
<c:if test="${not empty cars}">
    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.class"/></th>
            <th><fmt:message key="page.lang.mark"/></th>
            <th><fmt:message key="page.lang.name"/></th>
            <th><fmt:message key="page.lang.cost"/></th>
        </tr>

        <c:forEach items="${requestScope.cars}" var="car">
            <tr>
                <td>${car.clazz}</td>
                <td>${car.mark}</td>
                <td>${car.name}</td>
                <td>${car.cost}</td>

                <td>
                    <form action="/controller">
                        <input type="hidden" name="command" value="toEditCat">
                        <input type="hidden" name="carId" value="${car.id}">
                        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.edit"/>">
                    </form>
                </td>

                <td>
                    <form action="/controller">
                        <input type="hidden" name="command" value="toDeleteConfirm">
                        <input type="hidden" name="carId" value="${car.id}">
                        <input type="submit" class="btn btn-sm btn-primary"
                               value="<fmt:message key="page.lang.delete"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if><br/>

<%-- Add car --%>
<form action="/controller" class="page-element">
    <input type="hidden" name="command" value="toAddCar">
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.add"/>">
</form>

<%-- Check permission --%>
<u:permit role="admin"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
