<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Permission" scope="page"/>
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

<%-- Search --%>
<form action="/controller" class="page-element">
    <input type="hidden" name="command" value="findUser">
    <fmt:message key="page.lang.login"/>:
    <input type="search" name="looking">
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.find"/>">
</form>

<%-- Search output --%>
<c:if test="${not empty requestScope.orders}">
    <table class="page-element" id="info-table">
        <tr>
            <th><fmt:message key="page.lang.login"/></th>
            <th><fmt:message key="page.lang.full.name"/></th>
            <th><fmt:message key="page.lang.blocked"/></th>
            <th><fmt:message key="page.lang.role"/></th>
            <th><fmt:message key="page.lang.orders"/></th>
        </tr>

        <tr>
            <c:forEach items="${requestScope.orders}" var="order" end="0"> <%-- TODO thing about foreach --%>
                <td>${order.user.login}</td>
                <td>${order.user.fullName}</td>
                <td>${order.user.blocked}</td>
                <td>${order.user.role}</td>

                <td>
                    <c:forEach items="${requestScope.orders}" var="innerOrder">
                        <fmt:message key="page.lang.order.number"/> - ${innerOrder.number}<br/>
                        <fmt:message key="page.lang.state"/> - ${innerOrder.state.name}<br/>
                        <fmt:message key="page.lang.penalty"/> -

                        <c:if test="${not empty innerOrder.penalty.cause}">
                            ${innerOrder.penalty}<br/>
                        </c:if>

                        <c:if test="${empty innerOrder.penalty.cause}">
                            <fmt:message key="page.lang.have.no.penalty"/><br/>
                        </c:if>
                        <hr>
                    </c:forEach>
                </td>

                <td>
                    <c:if test="${order.user.blocked == 'true'}">
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="unlock">
                            <input type="hidden" name="login" value="${order.user.login}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.unblock"/>">
                        </form>
                    </c:if>

                    <c:if test="${order.user.blocked == 'false'}">
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="block">
                            <input type="hidden" name="login" value="${order.user.login}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.block"/>">
                        </form>
                    </c:if>

                    <form action="/controller" method="post">
                        <input type="hidden" name="command" value="setPermission">
                        <input type="hidden" name="login" value="${order.user.login}">
                        <select name="role" required>
                            <option value="CLIENT">Client</option>
                            <option value="MANAGER">Manager</option>
                            <option value="ADMIN">Admin</option>
                        </select><br/><br/>
                        <input type="submit" class="btn btn-sm btn-primary"
                               value="<fmt:message key="page.lang.accept"/>">
                    </form>
                </td>
            </c:forEach>
        </tr>
    </table>
</c:if>

<%-- Check permission --%>
<u:permit role="admin"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
