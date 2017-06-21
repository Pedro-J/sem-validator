<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="model.list.title" >
    <div class="container">
        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}" /><s:message code="app.entity.model"/>&nbsp;<s:message code="${msgContent}" /></strong>
            </div>
        </c:if>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="model.list.title" /></h4>
            </div>
            <div class="panel-body">

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th><s:message code="title.label" /></th>
                        <th><s:message code="description.label" /></th>
                        <th><s:message code="objectives.label" /></th>
                        <th><s:message code="applicabilities.label" /></th>
                        <th><s:message code="checklist.verification.label" /></th>
                        <th><s:message code="checklist.validation.label" /></th>
                        <th><s:message code="actions.label" /></th>
                    </tr>
                    </thead>

                    <c:forEach var="model" items="${models}">
                        <tr>
                            <td>${model.title}</td>
                            <td>${model.description}</td>
                            <td>${model.objectivesDesc}</td>
                            <td>${model.applicabilitiesDesc}</td>
                            <td>${model.checklistVerification.title}</td>
                            <td>${model.checklistValidation.title}</td>
                            <td>
                                <s:message code="general.delete" var="delete"/>
                                <s:message code="general.edit" var="edit"/>
                                <s:message code="general.seeDetail" var="detail"/>
                                <s:message code="answer.validation.question" var="validation"/>
                                <s:message code="answer.verification.question" var="verification"/>

                                <s:url value="/models/${model.id}" var="detailUrl" />
                                <s:url value="/models/${model.id}/delete" var="deleteUrl" />
                                <s:url value="/models/${model.id}/update" var="updateUrl" />
                                <s:url value="/models/${model.id}/validation" var="validationAnswers" />
                                <s:url value="/models/${model.id}/verification" var="verificatonAnswers" />

                                <button class="btn btn-info glyphicon glyphicon-zoom-in"
                                        onclick="location.href='${detailUrl}'" title="${detail}"></button>
                                <button class="btn btn-primary glyphicon glyphicon-edit"
                                        onclick="location.href='${updateUrl}'" title="${edit}"></button>
                                <c:if test="${not empty model.checklistVerification}" >
                                    <button class="btn btn-primary glyphicon glyphicon-question-sign" title="${verification}"
                                            onclick="location.href='${verificatonAnswers}'" ></button>
                                </c:if>
                                <c:if test="${not empty model.checklistVerification}" >
                                    <button class="btn btn-danger glyphicon glyphicon-question-sign" title="${validation}"
                                            onclick="location.href='${validationAnswers}'" ></button>
                                </c:if>
<%--                                <button class="btn btn-danger glyphicon glyphicon-remove"
                                        onclick="this.disabled=true;post('${deleteUrl}');" title="${delete}"></button>--%>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
</tags:pageTemplate>