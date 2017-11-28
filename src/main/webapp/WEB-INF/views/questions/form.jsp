<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="${question['new'] ? 'question.add.title': 'question.update.title'}">
    <div class="container">
        <c:url value="/" var="app_context"/>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <c:choose>
                    <c:when test="${question['new']}">
                        <h4><s:message code="question.add.title"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><s:message code="question.update.title"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <s:url value="/questions" var="formActionUrl"/>

                <form:form class="form-horizontal" method="POST" modelAttribute="question"
                           action="${formActionUrl}" acceptCharset="UTF-8"  >

                    <form:hidden path="id"/>

<%--                    <s:bind path="numeration">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="numeration.label" var="numeration"/>
                            <label class="col-sm-2 control-label">${numeration}:</label>
                            <div class="col-sm-8">
                                <form:input path="numeration" type="text" class="form-control " id="description"
                                            placeholder="${numeration}"/>
                                <form:errors path="numeration" class="control-label"/>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                    </s:bind>--%>

                    <s:bind path="description">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="description.label" var="qDesc"/>
                            <label class="col-sm-2 control-label">${qDesc}:</label>
                            <div class="col-sm-8">
                                <form:input path="description" type="text" class="form-control " id="description"
                                            placeholder="${qDesc}"/>
                                <form:errors path="description" class="control-label"/>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                    </s:bind>

                    <s:bind path="tip">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <s:message code="tip.label" var="qTip"/>
                            <label class="col-sm-2 control-label">${qTip}:</label>
                            <div class="col-sm-8">
                                <form:input path="tip" class="form-control" id="tip" placeholder="${qTip}"/>
                                <form:errors path="tip" class="control-label"/>
                            </div>
                            <div class="col-sm-2"></div>
                        </div>
                    </s:bind>

                    <s:bind path="criterion">
                        <div class="form-group ${status.error ? 'has-error' : ''}">

                            <label class="col-sm-2 control-label"><s:message code="criterion.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="criterion" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <form:options items="${criterions}" itemLabel="description" itemValue="id"/>
                                </form:select>
                                <form:errors path="criterion" class="control-label"/>
                            </div>
                            <div class="col-sm-1">
                                <s:message code="criterion.add.title" var="addCriterion"/>
                                <a class="btn btn-primary glyphicon glyphicon-plus"
                                        href="${app_context}criterions/add" title="${addCriterion}"></a>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                    </s:bind>

                    <s:bind path="requirement">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="criterion.category.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="requirement" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <form:options items="${requirements}" itemLabel="description" itemValue="id"/>
                                </form:select>
                                <form:errors path="requirement" class="control-label"/>
                            </div>
                            <div class="col-sm-1">
                                <s:message code="criterion.category.add.title" var="addCriterionCategory"/>
                                <a class="btn btn-primary glyphicon glyphicon-plus"
                                        href="${app_context}requirements/add" title="${addCriterionCategory}"></a>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                    </s:bind>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <c:choose>
                                <c:when test="${question['new']}">
                                    <button type="submit" class="btn btn-primary pull-right">
                                        <s:message code="general.save"/></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary pull-right" style="margin-left: 5px"><s:message code="general.update"/></button>
                                    <a href="${app_context}questions/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>