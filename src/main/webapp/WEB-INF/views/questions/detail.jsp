<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="question.detail.title">
    <div class="container">

        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}"/>
                    <s:message code="app.entity.question"/>&nbsp;<s:message code="${msgContent}" />
                </strong>
            </div>
        </c:if>
        <br/>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="question.detail.title"/></h4>
            </div>
            <div class="panel-body">

                <div class="row">
                    <label class="col-sm-2"><s:message code="description.label"/>:</label>
                    <div class="col-sm-10">${question.numeracao}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="description.label"/>:</label>
                    <div class="col-sm-10">${question.description}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="criterion.label"/>:</label>
                    <div class="col-sm-10">
                        <c:if test="${not empty question.criterion}">
                            <span style="margin-right: 20px;">${question.criterion.description}</span>


                            <s:message code="general.delete" var="delete"/>
                            <s:message code="general.edit" var="edit"/>

                            <s:url value="/criterions/${question.criterion.id}/delete" var="deleteUrl" />
                            <s:url value="/criterions/${question.criterion.id}/update" var="updateUrl" />

                            <img class="btn btn-primary glyphicon glyphicon-edit"
                                    onclick="location.href='${updateUrl}'" title="${edit}" />

                            <img class="btn btn-danger glyphicon glyphicon-remove"
                                    onclick="this.disabled=true;post('${deleteUrl}');" title="${delete}" />
                        </c:if>
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="requirement.label"/>:</label>
                    <div class="col-sm-10">
                        <c:if test="${not empty question.requirement}">
                            ${question.requirement.description}
                        </c:if>
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="tip.label"/>:</label>
                    <div class="col-sm-10">${question.tip}</div>
                </div>
            </div>
            <div class="panel-footer text-center" >
                <c:url value="/" var="app_context" />
                <a href="${app_context}questions/list" class="btn btn-default"><s:message code="general.back" /> </a>
            </div>
        </div>
    </div>
</tags:pageTemplate>
