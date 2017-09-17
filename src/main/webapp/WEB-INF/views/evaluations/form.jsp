<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="${evaluation['new'] ? 'evaluation.add.title': 'evaluation.update.title'}">
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <c:choose>
                    <c:when test="${evaluation['new']}">
                        <h4><s:message code="evaluation.add.title"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><s:message code="evaluation.update.title"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <s:url value="/evaluation" var="formActionUrl"/>

                <form:form class="form-horizontal" method="POST" modelAttribute="evaluation"
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

                    <s:bind path="model">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="app.entity.model"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="model" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <form:options items="${models}" itemLabel="description" itemValue="id"/>
                                </form:select>
                                <form:errors path="model" class="control-label"/>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </s:bind>

                    <s:bind path="checklist">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="app.entity.checklist"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="checklist" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <form:options items="${checklists}" itemLabel="description" itemValue="id"/>
                                </form:select>
                                <form:errors path="checklist" class="control-label"/>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </s:bind>

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
                                    <a href="${app_context}evaluation/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>