<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="${model['new'] ? 'model.add.title': 'model.update.title'}">
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <c:choose>
                    <c:when test="${model['new']}">
                        <h4><s:message code="model.add.title"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><s:message code="model.update.title"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <s:url value="/models" var="formActionUrl"/>

                <form:form class="form-horizontal" method="POST" modelAttribute="model"
                           action="${formActionUrl}" acceptCharset="UTF-8" enctype="multipart/form-data">

                    <form:hidden path="id"/>

                    <s:bind path="title">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="title.label" var="titleLabel"/>
                            <label class="col-sm-2 control-label">${titleLabel}:</label>
                            <div class="col-sm-7">
                                <form:input path="title" type="text" class="form-control " id="${titleLabel}"
                                               placeholder="${titleLabel}"/>
                                <form:errors path="title" class="control-label"/>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </s:bind>

                    <s:bind path="description">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="description.label" var="descLabel"/>
                            <label class="col-sm-2 control-label">${descLabel}:</label>
                            <div class="col-sm-7">
                                <form:textarea path="description" class="form-control " id="${descLabel}"
                                            placeholder="${descLabel}" rows="4"/>
                                <form:errors path="description" class="control-label"/>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </s:bind>

                    <s:bind path="objectivesDesc">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="objectives.label" var="objectivesLabel"/>
                            <label class="col-sm-2 control-label">${objectivesLabel}:</label>
                            <div class="col-sm-7">
                                <form:textarea path="objectivesDesc" class="form-control " id="${objectivesLabel}"
                                               placeholder="${objectivesLabel}" rows="4"/>
                                <form:errors path="objectivesDesc" class="control-label"/>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </s:bind>

                    <s:bind path="applicabilitiesDesc">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="applicabilities.label" var="appsLabel"/>
                            <label class="col-sm-2 control-label">${appsLabel}:</label>
                            <div class="col-sm-7">
                                <form:textarea path="applicabilitiesDesc" class="form-control " id="${appsLabel}"
                                               placeholder="${appsLabel}" rows="4"/>
                                <form:errors path="applicabilitiesDesc" class="control-label"/>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </s:bind>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="modelFile.label"/>: </label>
                        <div class="col-sm-7">
                            <input name="modelFile" type="file" class="form-control">
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <c:choose>
                                <c:when test="${model['new']}">
                                    <button type="submit" class="btn btn-primary pull-right"><s:message
                                            code="general.save"/></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary pull-right" style="margin-left: 5px"><s:message
                                            code="general.update"/></button>
                                    <c:url value="/" var="app_context" />
                                    <a href="${app_context}models/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>