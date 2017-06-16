<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="navbar navbar-default" role="navigation" >
    <div class="container">
        <div class="navbar-header">

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#js-navbar-collapse">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="/" style="padding-top: 17px">
                <s:message code="app.title.abbreviation" />
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('USER')" >

                </sec:authorize>
                <sec:authorize access="hasRole('MANAGER')" >

                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')" >
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.question" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/questions/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/questions/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.criterion" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/criterions/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/criterions/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.requirement" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/requirements/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="/requirements/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Logged as User</a></li>
                <li>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <a href="javascript:document.getElementById('logout').submit()">
                            <s:message code="general.logout" />
                        </a>
                    </c:if>
                    <c:url value="/logout" var="logoutUrl" />
                    <form id="logout" action="${logoutUrl}" method="post" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>

                </li>

            </ul>
        </div>
    </div>
</div>