<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="question.list.title" >

    <jsp:attribute name="extraScripts">
        <script type="text/javascript">

            $(document).ready(function() {
                $('.data-table-pagination').DataTable(getCurrentLanguage());
            });

        </script>
    </jsp:attribute>

    <jsp:body>
    <div class="container">
        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}" />&nbsp;<s:message code="app.entity.the.question"/>&nbsp;<s:message code="${msgContent}" /></strong>
            </div>
        </c:if>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="question.list.title" /></h4>
            </div>
            <div class="panel-body">

                <table class="table table-bordered data-table-pagination">
                    <thead>
                        <tr>
                            <th></th>
                            <th><s:message code="description.label" /></th>
                            <th><s:message code="criterion.label" /></th>
                            <th><s:message code="tip.label" /></th>
                            <th><s:message code="actions.label" /></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="question" items="${questions}">
                        <tr>
                            <td >${question.id}</td>
                            <td class="width600"><div class="long-text-column">${question.description}</div></td>
                            <td class="width200"><div class="long-text-column">${question.criterion.description}</div></td>
                            <td class="width140"><div class="long-text-column">${question.tip}</div></td>
                            <td class="actions-column">
                                <s:message code="general.delete" var="delete"/>
                                <s:message code="general.edit" var="edit"/>
                                <s:message code="general.seeDetail" var="detail"/>

                                <s:url value="/questions/${question.id}" var="detailUrl" />
                                <s:url value="/questions/${question.id}/delete" var="deleteUrl" />
                                <s:url value="/questions/${question.id}/update" var="updateUrl" />

                                <button class="btn btn-info glyphicon glyphicon-zoom-in"
                                        onclick="location.href='${detailUrl}'" title="${detail}"></button>
                                <button class="btn btn-primary glyphicon glyphicon-edit"
                                        onclick="location.href='${updateUrl}'" title="${edit}"></button>
                                <button class="btn btn-danger glyphicon glyphicon-remove"
                                        onclick="this.disabled=true;post('${deleteUrl}');" title="${delete}"></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    </jsp:body>
</tags:pageTemplate>