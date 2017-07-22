<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="${checklist['new'] ? 'checklist.add.title': 'checklist.update.title'}">
    <jsp:attribute name="extraScripts">
        <script type="text/javascript" src="/resources/js/components/questionsSelectAndSearchTable.js"></script>
    </jsp:attribute>
    <jsp:body>
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <c:choose>
                    <c:when test="${newChecklist}">
                        <h4><s:message code="checklist.add.title"/></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><s:message code="checklist.update.title"/></h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">

                <form class="form-horizontal" >

                    <div class="form-group ">
                        <label class="col-sm-2 control-label"><s:message code="description.label"/>:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="checklist-desc" />
                            <span class="form-control alert-danger desc-message" style="display: none">
                                <s:message code="form.notEmpty.description" />
                            </span>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="checklistType.label"/>:</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="checklist-type" >
                                <option value="" label="--- Select ---"/>
                                <c:forEach items="${checklistTypes}" var="item" >
                                    <s:message code="${item.messageCode}" var="typeDescription"/>
                                    <option value="${item}" label="${typeDescription}" />
                                </c:forEach>
                            </select>
                            <span class="form-control alert-danger type-message" style="display: none">
                                <s:message code="form.type.required" />
                            </span>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="app.entity.model"/>:</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="checklist-model" >
                                <option value="" label="--- Select ---"/>
                                <c:forEach items="${models}" var="item" >
                                    <option value="${item.id}" label="${item.title}" />
                                </c:forEach>
                            </select>
                            <span class="form-control alert-danger model-message" style="display: none">
                                <s:message code="form.model.required" />
                            </span>
                        </div>
                        <div class="col-sm-5"></div>
                    </div>

                    <div class="panel panel-primary panel-questions">
                        <div class="panel-heading text-center">
                            <h5><s:message code="questions.checklist.select"/></h5>
                        </div>
                        <div class="panel-body text-center">
                            <div style="margin:20px 0">
                                <select class="form-control width200 input-search select-criterion" >
                                    <option value="0" label="--- Select ---"/>
                                    <c:forEach items="${criterions}" var="item" >
                                        <option value="${item.id}" label="${item.description}" />
                                    </c:forEach>
                                </select>

                                <select class="form-control width200 input-search select-requirement" >
                                    <option value="0" label="--- Select ---"/>
                                    <c:forEach items="${requirements}" var="item" >
                                        <option value="${item.id}" label="${item.description}" />
                                    </c:forEach>
                                </select>

                                <s:message code="questions.label" var="questionLabel" />
                                <input type="text" class="form-control input-search input-description"
                                       style="width:50%;" placeholder="${questionLabel}" />

                                <button type="button" class="btn btn-primary btn-search glyphicon glyphicon-search width60"> </button>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th><s:message code="criterion.label" /></th>
                                    <th><s:message code="requirement.label" /></th>
                                    <th><div style="width:250px"><s:message code="questions.label" /></div></th>
                                </tr>
                                </thead>

                                <tbody class="ss-table-content" >

                                </tbody>
                            </table>
                        </div>

                        <div class="form-group text-center">
                            <nav aria-label="..." >
                                <ul class="pagination">
                                    <li class="page-item page-prev btn-page disabled">
                                        <span class="page-link">Previous</span>
                                    </li>
                                    <li class="page-item page-left btn-page active">
                                        <span class="page-link" >1</span>
                                    </li>
                                    <li class="page-item page-middle btn-page">
                                        <span class="page-link">2</span>
                                    </li>
                                    <li class="page-item page-right btn-page">
                                        <span class="page-link" >3</span>
                                    </li>
                                    <li class="page-item page-next btn-page">
                                        <span class="page-link" >Next</span>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="form-group"></div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <c:choose>
                                <c:when test="${newChecklist}">
                                    <button type="submit" class="btn btn-primary pull-right btn-save"><s:message
                                            code="general.save"/></button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary pull-right btn-update" style="margin-left: 5px"><s:message
                                            code="general.update"/></button>
                                    <c:url value="/" var="app_context" />
                                    <a href="${app_context}checklists/list" class="btn btn-default pull-right"><s:message code="general.back" /> </a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </jsp:body>
</tags:pageTemplate>