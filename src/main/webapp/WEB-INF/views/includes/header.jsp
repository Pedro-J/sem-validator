<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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

            <a class="navbar-brand" href="#/home" style="padding-top: 17px">
                SE Model Gen
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        User
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#/user/add">novo</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#/users">List</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        Tips
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/tip/add">Register</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#/tips">List</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                       role="button" aria-haspopup="true" aria-expanded="false">
                        Area
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/area/add">Register</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#/areas">List</a></li>
                    </ul>
                </li>

                <!--<li class="active"><a href="#/area">Area</a></li>-->
                <li class="pull-right">
                    <c:url value="/logout" var="logoutUrl" />
                    <form id="logout" action="${logoutUrl}" method="post" >
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <a href="javascript:document.getElementById('logout').submit()">Logout</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </div>
</div>