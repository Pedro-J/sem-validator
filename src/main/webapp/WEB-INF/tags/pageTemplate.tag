<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>

<!doctype html>
    <head>
      <meta charset="utf-8">
        <c:url value="/resources/css" var="cssPath" />
        <c:url value="/resources/js" var="jsPath" />
        <link rel="stylesheet" href="${cssPath}/bootstrap.min.css">
        <link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css">
        <link rel="stylesheet" href="${cssPath}/style.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="${jsPath}/bootstrap.js"></script>
        <script src="${jsPath}/scriptUtils.js"></script>

        <title>
            <c:if test="${not empty title and not title.contains('')}" >
                <s:message code="${titulo}" />
            </c:if>
        </title>
    </head>
    <body class="${bodyClass}">

        <%@include file="/WEB-INF/views/fragments/header.jsp" %>

        <jsp:doBody />

        <%@include file="/WEB-INF/views/fragments/footer.jsp" %>

        <jsp:invoke fragment="extraScripts" />

        <script type="text/javascript">
            //scripts for all pages
        </script>

    </body>
</html>
    
    
    
    




