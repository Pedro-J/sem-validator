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
                            <label class="col-sm-2 control-label"><s:message code="description.label" var="titleLabel"/>:</label>
                            <div class="col-sm-10">
                                <form:textarea path="description" type="text" class="form-control " id="${titleLabel}"
                                               placeholder="${titleLabel}"/>
                                <form:errors path="description" class="control-label"/>
                            </div>
                        </div>
                    </s:bind>

                    <s:bind path="description">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="description.label" var="descLabel"/>:</label>
                            <div class="col-sm-10">
                                <form:input path="description" type="text" class="form-control " id="${descLabel}"
                                            placeholder="${descLabel}"/>
                                <form:errors path="description" class="control-label"/>
                            </div>
                        </div>
                    </s:bind>

                    <s:bind path="objectivesDesc">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="objectives.label" var="objectivesLabel"/>:</label>
                            <div class="col-sm-10">
                                <form:textarea path="objectivesDesc" class="form-control " id="${objectivesLabel}"
                                               placeholder="${objectivesLabel}"/>
                                <form:errors path="objectivesDesc" class="control-label"/>
                            </div>
                        </div>
                    </s:bind>

                    <s:bind path="applicabilitiesDesc">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="applicabilities.label" var="appsLabel"/>:</label>
                            <div class="col-sm-10">
                                <form:textarea path="applicabilitiesDesc" class="form-control " id="${appsLabel}"
                                               placeholder="${appsLabel}"/>
                                <form:errors path="applicabilitiesDesc" class="control-label"/>
                            </div>
                        </div>
                    </s:bind>

                    <s:bind path="checklist">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="checklist.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="checklist.id" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <c:forEach items="${checklists}" var="item" >
                                        <form:option value="${item.id}" label="${item.description}" />
                                    </c:forEach>
                                </form:select>
                                <form:errors path="checklist" class="control-label"/>
                            </div>
                            <div class="col-sm-5"></div>
                        </div>
                    </s:bind>

                    <div class="form-group">
                        <label>Sumário</label>
                        <input name="sumario" type="file" class="form-control">
                    </div>

                    <s:bind path="checklistVerification">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label class="col-sm-2 control-label"><s:message code="criterion.label"/>:</label>
                            <div class="col-sm-5">
                                <form:select path="checklistVerification" class="form-control">
                                    <form:option value="0" label="--- Select ---"/>
                                    <form:options items="${criterions}" itemLabel="description" itemValue="id"/>
                                </form:select>
                                <form:errors path="checklistVerification" class="control-label"/>
                            </div>
                            <div class="col-sm-5"></div>
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

                                    <a href="/models/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>