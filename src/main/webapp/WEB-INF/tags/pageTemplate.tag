<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>

<!doctype html>
    <head>
        <meta charset="UTF-8">
<%--    <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
        <c:url value="/resources/css" var="cssPath" />
        <c:url value="/resources/js" var="jsPath" />
        <link rel="stylesheet" href="${cssPath}/bootstrap.css">
        <link rel="stylesheet" href="${cssPath}/dataTable/dataTables.bootstrap.min.css">
        <link rel="stylesheet" href="${cssPath}/style.css">


        <script type="text/javascript" src="${jsPath}/jquery/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${jsPath}/bootstrap.js"></script>
        <script type="text/javascript" src="${jsPath}/dataTable/dataTables.min.js"></script>
        <script type="text/javascript" src="${jsPath}/dataTable/dataTables.bootstrap.min.js"></script>

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

    </body>
</html>
    
    
    
    




