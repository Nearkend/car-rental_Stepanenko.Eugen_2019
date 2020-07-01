<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Order" scope="page"/>
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

<div class="page-element">
    <fmt:message key="page.lang.car"/>:<br/>
    <fmt:message key="page.lang.class"/>: ${car.clazz}<br/>
    <fmt:message key="page.lang.mark"/>: ${car.mark}<br/>
    <fmt:message key="page.lang.name"/>: ${car.name}<br/>
    <fmt:message key="page.lang.cost"/>: ${car.cost}<br/><br/>
</div>

<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="order">
    <input type="hidden" name="carId" value="${requestScope.car.id}">
    <input type="hidden" name="carClass" value="${requestScope.car.clazz}">
    <input type="hidden" name="carMark" value="${requestScope.car.mark}">
    <input type="hidden" name="carName" value="${requestScope.car.name}">
    <input type="hidden" name="carCost" value="${requestScope.car.cost}">
    <input type="hidden" name="carThereIs" value="${requestScope.car.thereIs}">
    <select name="driver">
        <option value="true" selected><fmt:message key="page.lang.with.driver"/></option>
        <option value="false"><fmt:message key="page.lang.without.driver"/></option>
    </select><br/><br/>
    <fmt:message key="page.lang.term"/>:<br/>
    <input type="date" name="term" required><br/><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
</form>

<%-- Check permission --%>
<u:permit role="client"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
