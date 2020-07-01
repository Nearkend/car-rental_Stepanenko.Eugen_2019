<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Car order" scope="page"/>
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
    ${requestScope.car.clazz}<br/>
    ${requestScope.car.mark}<br/>
    ${requestScope.car.name}<br/>
    ${requestScope.car.cost}<br/><br/>
</div>

<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="clientOrderAccept">
    <input type="hidden" name="class" value="${requestScope.car.clazz}">
    <input type="hidden" name="mark" value="${requestScope.car.mark}">
    <input type="hidden" name="name" value="${requestScope.car.name}">
    <input type="hidden" name="cost" value="${requestScope.car.cost}">
    <fmt:message key="page.lang.passport"/><br/>
    <input name="passport" value="${sessionScope.user.passport}" required minlength="8" maxlength="8"><br/><br/>
    <select name="driver" required>
        <option value="true"><fmt:message key="page.lang.with.driver"/></option>
        <option value="false"><fmt:message key="page.lang.without.driver"/></option>
    </select><br/><br/>
    <fmt:message key="page.lang.term"/><br/>
    <input type="date" name="term" required><br/><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
</form>

<%-- Check permission --%>
<u:permit role="client"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
