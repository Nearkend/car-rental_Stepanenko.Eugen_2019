<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Reject" scope="page"/>
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

<%-- Reject form --%>
<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="rejectOrder">
    <input type="hidden" name="orderNumber" value="${requestScope.orderNumber}">
    <fmt:message key="page.lang.cause"/>:<br/>
    <input required minlength="16" maxlength="96" name="cause"><br/><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
</form>

<%-- Check permission --%>
<u:permit role="manager"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
