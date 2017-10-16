<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="checklist.detail.title">
    <jsp:attribute name="extraScripts">
        <script type="text/javascript">
            $(document).ready(function() {
                $('.data-table-pagination').DataTable(getCurrentLanguage());
            } );
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">

            <c:if test="${not empty msgContent}">
                <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <strong><s:message code="${msgTitle}"/>
                        <s:message code="app.entity.evaluation"/>&nbsp;<s:message code="${msgContent}" />
                    </strong>
                </div>
            </c:if>
            <br/>
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h4><s:message code="evaluation.detail.title"/></h4>
                </div>
                <div class="panel-body">

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="title.label"/>:</label>
                        <div class="col-sm-9">${evaluation.title}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="app.entity.evaluation"/>:</label>
                        <div class="col-sm-9">${evaluation.checklist.title}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="checklistType.label"/>:</label>
                        <div class="col-sm-9">
                            <c:if test="${not empty evaluation.checklist.checklistType}">
                                <s:message code="${evaluation.checklist.checklistType.messageCode}"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="row"><br/></div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="app.entity.model.detail"/>:</label>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="title.label"/>:</label>
                        <div class="col-sm-9">${evaluation.model.title}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="description.label"/>:</label>
                        <div class="col-sm-9">${evaluation.model.description}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="objectives.label"/>:</label>
                        <div class="col-sm-9">${evaluation.model.objectivesDesc}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="applicabilities.label"/>:</label>
                        <div class="col-sm-9">${evaluation.model.applicabilitiesDesc}</div>
                    </div>

                    <div class="row">
                        <div class="col-sm-1"></div>
                        <label class="col-sm-2"><s:message code="general.satisfaction.label"/>:</label>
                        <div class="col-sm-9">
                            <fmt:formatNumber value="${generalSatisfaction}" type="percent" maxFractionDigits="2"/>
                        </div>
                    </div>

                    <div class="row"><br/></div>

                    <div class="row">
                        <c:if test="${not empty requirements}">
                        <div class="panel panel-primary" style="width: 43%;float:left;margin-left: 58px;">
                            <div class="panel-heading text-center">
                                <h5><s:message code="requirements.satisfaction.label"/></h5>
                            </div>
                            <div class="panel-body ">
                                <div id="requirementStatistics" class="table-responsive">
                                    <table class="table table-hover data-table-pagination">
                                        <thead>
                                        <tr>
                                            <th><s:message code="app.entity.requirement" /> </th>
                                            <th><s:message code="app.entity.satisfaction" /> </th>
                                        </tr >
                                        </thead>
                                        <tbody>
                                        <c:forEach var="requirement" items="${requirements}" >
                                            <tr>
                                                <td class="long-text-column width300">${requirement.description}</td>
                                                <td class="width40">
                                                    <fmt:formatNumber value="${requirement.satisfactionValue}" type="percent" maxFractionDigits="2"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        </c:if>

                        <c:if test="${not empty criterions}">
                            <div class="panel panel-primary" style="width: 43%;float:right;margin-right:58px;">
                                <div class="panel-heading text-center">
                                    <h5><s:message code="criterions.satisfaction.label"/></h5>
                                </div>
                                <div class="panel-body ">
                                    <div id="criterionStatistics" class="table-responsive">
                                        <table class="table table-hover data-table-pagination">
                                            <thead>
                                            <tr>
                                                <th><s:message code="app.entity.criterion" /> </th>
                                                <th><s:message code="app.entity.satisfaction" /> </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="criterion" items="${criterions}" >
                                                <tr>
                                                    <td class="long-text-column width300">${criterion.description}</td>
                                                    <td class="width40">
                                                        <fmt:formatNumber value="${criterion.satisfactionValue}" type="percent" maxFractionDigits="2"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </c:if>
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
                                        <table class="table table-hover data-table-pagination">
                                            <thead>
                                            <tr>
                                                <th><s:message code="app.entity.question" /> </th>
                                                <th><s:message code="app.entity.requirement" /> </th>
                                                <th><s:message code="app.entity.criterion" /> </th>
                                                <th><s:message code="app.entity.answer" /> </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="answer" items="${answers}" >
                                                <tr>
                                                    <td ><div class="long-text-column">${answer.question.description}</div></td>
                                                    <td class="width200"><div class="long-text-column">${answer.question.requirement.description}</div></td>
                                                    <td class="width100"><div class="long-text-column">${answer.question.criterion.description}</div></td>
                                                    <td class="width100"><div class="long-text-column"><s:message code="${answer.value.messageCode}" /></div></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>


                <div class="panel-footer text-center" >
                    <c:url value="/" var="app_context" />
                    <a href="${app_context}evaluations/list" class="btn btn-default"><s:message code="general.back" /> </a>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
