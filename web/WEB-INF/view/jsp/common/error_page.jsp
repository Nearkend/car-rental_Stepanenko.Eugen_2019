<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Error" scope="page"/>
<%@include file="/WEB-INF/view/jspf/common/head.jspf" %>
<body>
<h3 class="page-element">Error page</h3><hr>
<div class="page-element">${sessionScope.error}</div><br/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
