<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="model.detail.title">
    <div class="container">

        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}"/>
                    <s:message code="app.entity.model"/>&nbsp;<s:message code="${msgContent}" />
                </strong>
            </div>
        </c:if>
        <br/>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="model.detail.title"/></h4>
            </div>
            <div class="panel-body">
                <div class="row">
                    <label class="col-sm-2"><s:message code="title.label"/>:</label>
                    <div class="col-sm-10">${model.title}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="description.label"/>:</label>
                    <div class="col-sm-10">${model.description}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="objectives.label"/>:</label>
                    <div class="col-sm-10">${model.objectivesDesc}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="applicabilities.label"/>:</label>
                    <div class="col-sm-10">${model.applicabilitiesDesc}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="checklist.validation.label"/>:</label>
                    <div class="col-sm-10">${model.checklistValidation.title}</div>
                </div>

                <div class="row">
                    <label class="col-sm-2"><s:message code="checklist.verification.label"/>:</label>
                    <div class="col-sm-10">${model.checklistVerification.title}</div>
                </div>



                <div class="row">
                    <label class="col-sm-2"><s:message code="modelFile.label"/>:</label>
                    <div class="col-sm-10">
                        <c:if test="${not empty model.modelFileUrl}" >
                            <c:url value="/" var="contextPath" />
                            <img src="${contextPath}${model.modelFileUrl}" class="img-responsive" />
                        </c:if>
                    </div>
                </div>


                
            </div>
            <div class="panel-footer text-center" >
                <a href="/models/list" class="btn btn-default"><s:message code="general.back" /> </a>
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h5><s:message code="answers.label"/></h5>
            </div>
            <div class="panel-body ">
                <div id="questionsContainer" class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th colspan="3"><s:message code="app.entity.question" /> </th>
                            <th colspan="1"><s:message code="app.entity.answer" /> </th>
                            <th colspan="1"><s:message code="app.entity.requirement" /> </th>
                            <th colspan="1"><s:message code="app.entity.criterion" /> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="answer" items="${answers}" >
                            <tr>
                                <td colspan="3">* ${answer.question.number} - ${answer.question.description}</td>
                                <td colspan="1"><s:message code="${answer.value.messageCode}" /></td>
                                <td colspan="1">${answer.requirement.description}</td>
                                <td colspan="1">${answer.question.criterion.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>



    </div>
</tags:pageTemplate>
