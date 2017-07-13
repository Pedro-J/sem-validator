<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="question.list.title" >
    <div class="container">
        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}" /><s:message code="app.entity.question"/>&nbsp;<s:message code="${msgContent}" /></strong>
            </div>
        </c:if>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="question.list.title" /></h4>
            </div>
            <div class="panel-body">

                <table class="table table-bordered">
                    <thead>
                        <tr>

                            <th><s:message code="description.label" /></th>
                            <th><s:message code="criterion.label" /></th>
                            <th><s:message code="requirement.label" /></th>
                            <th><s:message code="tip.label" /></th>
                            <th><s:message code="actions.label" /></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="question" items="${questions}">
                        <tr>
                            <td><div style="width:400px">${question.description}</div></td>
                            <td>${question.criterion.description}</td>
                            <td>${question.requirement.description}</td>
                            <td>${question.tip}</td>
                            <td>
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
</tags:pageTemplate>