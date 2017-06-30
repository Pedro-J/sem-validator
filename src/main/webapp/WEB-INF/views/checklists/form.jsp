<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="${checklist['new'] ? 'checklist.add.title': 'checklist.update.title'}">
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <c:choose>
                    <c:when test="${checklist['new']}">
                        <h4><s:message code="checklist.add.title"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><s:message code="checklist.update.title"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <s:url value="/checklists" var="formActionUrl"/>

                <form:form class="form-horizontal" method="POST" modelAttribute="checklist"
                           action="${formActionUrl}" acceptCharset="UTF-8">

                    <form:hidden path="id"/>

                    <s:bind path="title">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="description.label"/>:</label>
                            <div class="col-sm-10">
                                <form:input path="title" type="text" class="form-control " id="title"
                                            placeholder="Title"/>
                                <form:errors path="title" class="control-label"/>
                            </div>
                        </div>
                    </s:bind>

                    <s:bind path="checklistType">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="checklist.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="checklistType" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <c:forEach items="${checklistTypes}" var="item" >
                                        <s:message code="${item.messageCode}" var="typeDescription"/>
                                        <form:option value="${item}" label="${typeDescription}" />
                                    </c:forEach>
                                </form:select>
                                <form:errors path="checklistType" class="control-label"/>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </s:bind>

                    <spring:bind path="requirements">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="questions.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="requirements" items="${availableRequirements}" itemValue="id" itemLabel="description" multiple="true"
                                             class="form-control" />
                                <form:errors path="requirements" class="control-label" />
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </spring:bind>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <c:choose>
                                <c:when test="${checklist['new']}">
                                    <button type="submit" class="btn btn-primary pull-right"><s:message
                                            code="general.save"/></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary pull-right" style="margin-left: 5px"><s:message
                                            code="general.update"/></button>
                                    <c:url value="/" var="app_context" />
                                    <a href="${app_context}checklists/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>