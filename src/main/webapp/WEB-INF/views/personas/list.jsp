<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="persona.list.title" >
    <jsp:attribute name="extraScripts">
        <script type="text/javascript">
            $(document).ready(function() {
                $('.data-table-pagination').DataTable(getCurrentLanguage());
            } );
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <c:if test="${not empty msgContent}">
                <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong><s:message code="${msgTitle}" /><s:message code="app.entity.persona"/>&nbsp;<s:message code="${msgContent}" /></strong>
                </div>
            </c:if>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h4><s:message code="persona.list.title" /></h4>
                </div>
                <div class="panel-body">

                    <table class="table table-bordered data-table-pagination">
                        <thead>
                        <tr>
                            <th><s:message code="description.label" /></th>
                            <th><s:message code="actions.label" /></th>
                        </tr>
                        </thead>

                        <c:forEach var="persona" items="${personas}">
                            <tr>
                                <td><div class="long-text-column">${persona.description}</div></td>
                                <td>
                                    <s:message code="persona.tips.label" var="tipLabel"/>
                                    <s:url value="/personas/${persona.id}/tips" var="personaTips" />

                                    <button class="btn btn-primary glyphicon glyphicon-edit"
                                            onclick="location.href='${personaTips}'" title="${tipLabel}"></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>