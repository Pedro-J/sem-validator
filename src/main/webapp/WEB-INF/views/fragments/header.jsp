<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="navbar navbar-default" role="navigation" >
    <c:url value="/resources/images" var="imagesPath" />
    <c:url value="/" var="app_context"/>
    <div class="container">
        <div class="navbar-header">

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#js-navbar-collapse">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="${app_context}" style="padding-top: 17px">
                <s:message code="app.title.abbreviation" />
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('ADMIN')" >

                </sec:authorize>
                <sec:authorize access="hasRole('MANAGER')" >

                </sec:authorize>
                <sec:authorize access="hasRole('USER')" >
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.question" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${app_context}questions/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${app_context}questions/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.checklist" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${app_context}checklists/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${app_context}checklists/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.model" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${app_context}models/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${app_context}models/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                           role="button" aria-haspopup="true" aria-expanded="false">
                            <s:message code="app.entity.evaluation" />
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${app_context}evaluations/add"><s:message code="general.register" /></a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="${app_context}evaluations/list"><s:message code="general.list" /></a></li>
                        </ul>
                    </li>

                    <li><a href="${app_context}persona"> <s:message code="app.entity.persona" /></a></li>
                    <li><a href="${app_context}scenario" > <s:message code="app.entity.scenario" /></a></li>

                </sec:authorize>

            </ul>
            <ul class="nav navbar-nav navbar-right">

                <li>
                    <s:message code="menu.pt" var="lang_pt"/>
                    <a href="?locale=pt" rel="nofollow">
                        <img src="${imagesPath}/lang_pt.png" title="${lang_pt}" class="lang"/>
                    </a>
                </li>
                <li>
                    <s:message code="menu.en" var="lang_en"/>
                    <a href="?locale=en_US" rel="nofollow">
                        <img src="${imagesPath}/lang_en.png" title="${lang_en}" class="lang" />
                    </a>
                </li>
                <li>
                    <sec:authentication property="principal" var="usuario" />
                    <a href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        <s:message code="general.logged.as" /> <b>${usuario.username}</b>
                    </a>
                </li>
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