<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Edit car" scope="page"/>
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

<%-- Add car form --%>
<form action="/controller" method="post" class="page-element">
    <input type="hidden" name="command" value="editCar">
    <input type="hidden" name="carId" value="${requestScope.carId}">
    <fmt:message key="page.lang.class"/>:<br/>
    <select name="class" required>
        <option value="BUSINESS">Business</option>
        <option value="SPORT">Sport</option>
        <option value="ECONOMY">Economy</option>
        <option value="MEDIUM">Medium</option>
    </select><br/><br/>
    <fmt:message key="page.lang.mark"/>:<br/>
    <select name="mark" required>
        <option value="AUDI">Audi</option>
        <option value="BMW">BMW</option>
        <option value="PEUGEOT">Peugeot</option>
        <option value="HYUNDAI">Hyundai</option>
        <option value="KIA">KIA</option>
        <option value="LEXUS">Lexus</option>
        <option value="MAZDA">Mazda</option>
        <option value="NISAN">Nisan</option>
        <option value="OPEL">Opel</option>
        <option value="RENAULT">Renault</option>
        <option value="PORSCHE">Porsche</option>
        <option value="MERCEDES">Mercedes</option>
        <option value="MITSUBISHI">Mitsubishi</option>
    </select><br/><br/>
    <fmt:message key="page.lang.name"/>:<br/>
    <input name="name" minlength="2" maxlength="16" required><br/><br/>
    <fmt:message key="page.lang.cost"/>:<br/>
    <input type="number" name="cost" required><br/><br/>
    <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.accept"/>">
</form>

<%-- Check permission --%>
<u:permit role="admin"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
