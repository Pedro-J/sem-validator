<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="criterion.detail.title">
    <div class="container">

        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}"/>
                    <s:message code="app.entity.criterion"/>&nbsp;<s:message code="${msgContent}" />
                </strong>
            </div>
        </c:if>
        <br/>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="criterion.detail.title"/></h4>
            </div>
            <div class="panel-body">

                <div class="row">
                    <label class="col-sm-2"><s:message code="requirement.label"/>:</label>
                    <div class="col-sm-10">
                        <c:if test="${not empty criterion.requirement}">
                            ${criterion.requirement.description}
                        </c:if>
                    </div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="description.label"/>:</label>
                    <div class="col-sm-10">${criterion.description}</div>
                </div>
                <div class="row">
                    <label class="col-sm-2"><s:message code="criterion.questions.label"/>:</label>
                    <div class="col-sm-10">
                        <c:forEach var="question" items="${questions}" >
                            *${question.number} - ${question.description}<br/>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="panel-footer text-center" >
                <a href="/criterions/list" class="btn btn-default"><s:message code="general.back" /> </a>
            </div>
        </div>
    </div>
</tags:pageTemplate>
