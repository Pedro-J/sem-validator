<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="evaluation.list.title" >
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
                    <strong><s:message code="${msgTitle}" />&nbsp;<s:message code="app.entity.the.evaluation"/>&nbsp;<s:message code="${msgContent}" /></strong>
                </div>
            </c:if>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h4><s:message code="evaluation.list.title" /></h4>
                </div>
                <div class="panel-body">

                    <table class="table table-bordered data-table-pagination">
                        <thead>
                        <tr>
                            <th><s:message code="title.label" /></th>
                            <th><s:message code="app.entity.model" /></th>
                            <th><s:message code="app.entity.checklist" /></th>
                            <th><s:message code="actions.label" /></th>
                        </tr>
                        </thead>

                        <c:forEach var="evaluation" items="${evaluations}">
                            <tr>
                                <td class="width260"><div class="long-text-column"> ${evaluation.title}</div></td>
                                <td class="width200"><div class="long-text-column">${evaluation.model.title}</div></td>
                                <td class="width200"><div class="long-text-column">${evaluation.checklist.title}</div></td>
                                <td class="actions-column-4">
                                    <s:message code="general.delete" var="delete"/>
                                    <s:message code="general.edit" var="edit"/>
                                    <s:message code="general.seeDetail" var="detail"/>
                                    <s:message code="answer.checklist.question" var="answerQuestions"/>


                                    <s:url value="/evaluations/${evaluation.id}" var="detailUrl" />
                                    <s:url value="/evaluations/${evaluation.id}/delete" var="deleteUrl" />
                                    <s:url value="/evaluations/${evaluation.id}/update" var="updateUrl" />
                                    <s:url value="/evaluations/${evaluation.id}/answers/form" var="evaluationAnswers" />

                                    <button class="btn btn-info glyphicon glyphicon-zoom-in"
                                            onclick="location.href='${detailUrl}'" title="${detail}"></button>
                                    <button class="btn btn-primary glyphicon glyphicon-edit"
                                            onclick="location.href='${updateUrl}'" title="${edit}"></button>

                                    <button class="btn btn-danger glyphicon glyphicon-remove"
                                            onclick="this.disabled=true;post('${deleteUrl}');" title="${delete}"></button>

                                    <button class="btn btn-primary glyphicon glyphicon-question-sign" title="${answerQuestions}"
                                            onclick="location.href='${evaluationAnswers}'" ></button>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>