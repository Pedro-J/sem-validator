<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="scenario.list.title" >
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
                    <strong><s:message code="${msgTitle}" /><s:message code="app.entity.scenario"/>&nbsp;<s:message code="${msgContent}" /></strong>
                </div>
            </c:if>
            <div style="margin-top:20px">
                <p style="text-align: left;"><s:message code="scenario.description.text.p1" /></p>
                <p style="text-align: left;"><s:message code="scenario.description.text.p2" /></p>
                <p style="text-align: left;"><s:message code="scenario.description.text.p3" /></p>
                <br/>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h4><s:message code="scenario.list.title" /></h4>
                </div>
                <div class="panel-body">

                    <table class="table table-bordered data-table-pagination">
                        <thead>
                        <tr>
                            <th>Nº</th>
                            <th><s:message code="description.label" /></th>
                            <th><s:message code="tip.label" /></th>
                        </tr>
                        </thead>

                        <c:forEach var="scenario" items="${scenarios}">
                            <tr>
                                <td><div class="long-text-column width10">${scenario.id}</div></td>
                                <td><div class="long-text-column" style="width:990px;" >${scenario.description}</div></td>
                                <td>
                                    <div>
                                        <s:message code="scenario.tips.label" var="tipLabel"/>
                                        <s:url value="/scenarios/${scenario.id}/tips" var="scenarioTips" />

                                        <button class="btn btn-primary glyphicon glyphicon-zoom-in"
                                                onclick="location.href='${scenarioTips}'" title="${tipLabel}"></button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>