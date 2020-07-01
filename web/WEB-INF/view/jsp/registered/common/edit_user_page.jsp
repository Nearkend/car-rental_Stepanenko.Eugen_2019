<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Edit personal data" scope="page"/>
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

<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="editUser">
    <fmt:message key="page.lang.new"/> <fmt:message key="page.lang.login"/>:<br/>
    <input name="login" required minlength="5" maxlength="16"><br/><br/>
    <fmt:message key="page.lang.new"/> <fmt:message key="page.lang.password"/>:<br/>
    <input name="password" required minlength="5" maxlength="16"><br/><br/>
    <fmt:message key="page.lang.new"/> <fmt:message key="page.lang.full.name"/>:<br/>
    <input name="fullName" required minlength="2" maxlength="32"><br/><br/>

    <%-- Check user passport --%>
    <c:if test="${not empty sessionScope.user.passport}">
        <fmt:message key="page.lang.new"/> <fmt:message key="page.lang.passport"/>:<br/>
        <input name="passport" required minlength="8" maxlength="8"><br/><br/>
    </c:if>

    <fmt:message key="page.lang.old"/> <fmt:message key="page.lang.login"/>:<br/>
    <input name="oldLogin" required minlength="5" maxlength="16"><br/><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.change"/>">
</form>

<%-- Check permission --%>
<u:permit role="client"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
