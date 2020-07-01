<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Confirm" scope="page"/>
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

<div class="page-element"><fmt:message key="page.lang.confirm.message.remove.car"/></div>
<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="deleteCar">
    <input type="hidden" name="carId" value="${requestScope.carId}"><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.yes"/>">
    <form action="/controller" class="page-element">
        <input type="hidden" name="command" value="toCars">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.no"/>">
    </form>
</form>

<%-- Check permission --%>
<u:permit role="admin"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
