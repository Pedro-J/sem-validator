<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="checklist.detail.title">
    <div class="container">

        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}"/>
                    <s:message code="app.entity.checklist"/>&nbsp;<s:message code="${msgContent}" />
                </strong>
            </div>
        </c:if>
        <br/>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="checklist.detail.title"/></h4>
            </div>
            <div class="panel-body">

                <div class="row">
                    <div class="col-sm-1"></div>
                    <label class="col-sm-2"><s:message code="title.label"/>:</label>
                    <div class="col-sm-9">${checklist.title}</div>
                </div>

                <div class="row">
                    <div class="col-sm-1"></div>
                    <label class="col-sm-2"><s:message code="checklistType.label"/>:</label>
                    <div class="col-sm-9">
                        <c:if test="${not empty checklist.checklistType}">
                            <s:message code="${checklist.checklistType.messageCode}"/>
                        </c:if>
                    </div>
                </div>

                <div class="row"><br/></div>

                <c:if test="${not empty answers}">
                    <div class="row">
                        <div class="panel panel-primary" style="width: 90%;margin: 0 auto;">
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
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="answer" items="${answers}" >
                                            <tr>
                                                <td colspan="3">${answer.question.description}</td>
                                                <td colspan="1"><s:message code="${answer.value.messageCode}" /></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="panel-footer text-center" >
                <c:url value="/" var="app_context" />
                <a href="${app_context}checklists/list" class="btn btn-default"><s:message code="general.back" /> </a>
            </div>
        </div>
    </div>
</tags:pageTemplate>
