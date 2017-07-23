<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

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

        <title><s:message code="app.title"/></title>
    </head>
    <body class="container">
        <div class="body-vertical-center">
            <div class="container-fluid ">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading text-center">
                                <s:message code="login.painel.title"/>
                            </div>
                            <div class="panel-body">

                                <c:if test="${not empty error}">
                                    <span class="label label-danger" >${error}</span>
                                </c:if>
                                <c:if test="${not empty logoutSuccess}">
                                    <span class="label label-info">${logoutSuccess}</span>
                                </c:if>

                                <form name="login" action="<c:url value='/login'/>" method="POST">
                                    <div class="form-group">
                                        <label for="username"><s:message code="login.form.login"/></label>
                                        <input type="text" class="form-control" required formControlName="username"
                                               name="username" id="username" placeholder="username">
                                    </div>
                                    <div class="form-group">
                                        <label for="password"><s:message code="login.form.password"/></label>
                                        <input type="password" class="form-control" required formControlName="password"
                                               name="password" id="password" placeholder="password">
                                    </div>
                                    <button type="submit" class="btn btn-primary"><s:message code="login.form.confirm"/></button>
                                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>
                                </form>
                            </div>
<%--                            <div class="panel-footer text-center">
                                <a href=""><s:message code="login.message.newAccount"/></a>
                            </div>--%>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>







