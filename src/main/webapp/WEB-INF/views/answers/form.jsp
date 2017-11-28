<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="answer.evaluation">

    <jsp:attribute name="extraScripts">
        <c:url value="/resources/js" var="jsPath" />
        <script type="text/javascript" src="${jsPath}/components/answerTable.js"></script>
    </jsp:attribute>

    <jsp:body>
        <c:url value="/" var="urlContext" scope="request"></c:url>
        <div class="container">
            <div class="alert alert-success alert-dismissible" role="alert" style="display: none;">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="general.msg.title.info" />&nbsp;<s:message code="general.msg.positive.operation"/></strong>
            </div>

            <div class="alert alert-danger alert-dismissible" role="alert" style="display: none;">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="general.msg.title.error" />&nbsp;<s:message code="general.msg.negative.operation"/></strong>
            </div>

            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h4><s:message code="answer.evaluation"/></h4>
                </div>
                <div class="panel-body">
                    <s:url value="/answer" var="formActionUrl"/>
                    <s:message code="select.label" var="select" />
                    <form class="form-horizontal" method="POST" action="${formActionUrl}" acceptCharset="UTF-8">
                        <input id="init_value" type="hidden" value="${select}"/>
                        <input id="id_evaluation" type="hidden" value="${evaluation.id}"/>

                        <div class="row">
                            <div class="col-sm-1"></div>
                            <label class="col-sm-2"><s:message code="title.label" />:</label>
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

                        <div class="row">
                            <div class="col-sm-1"></div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#model-details"><s:message code="app.entity.model.detail"/></button>
                            </div>
                            <div class="col-sm-8">

                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group collapse col-sm-12" id="model-details">
                                <div class="panel" style="width: 100%;margin: 10px auto;">

                                    <div class="panel-body ">
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
                                            <label class="col-sm-2"><s:message code="modelFile.label"/>:</label>
                                            <div class="col-sm-9"></div>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-10">
                                                <c:if test="${not empty evaluation.model.modelFileUrl}" >
                                                    <c:url value="/" var="contextPath" />
                                                    <img src="${contextPath}${evaluation.model.modelFileUrl}" class="img-responsive" />
                                                </c:if>
                                            </div>
                                            <div class="col-sm-1"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="panel panel-primary" style="width: 90%;margin: 10px auto;">
                                <div class="panel-heading text-center">
                                    <h5>${evaluation.checklist.title}</h5>
                                </div>
                                <div class="panel-body ">
                                    <div id="questionsContainer" class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th class="text-center width400"><s:message code="app.entity.question" /> </th>
                                                <th class="text-center width200"><s:message code="app.entity.criterion" /> </th>
                                                <th class="text-center width100"><s:message code="tip.label" /> </th>
                                                <th class="text-center width200"><s:message code="app.entity.answer" /> </th>
                                            </tr>
                                            </thead>
                                            <tbody id="answers_questions">
                                            </tbody>
                                        </table>
                                    </div>
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
                        </div>


                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <c:url value="/" var="app_context" />
                                <button type="button" class="btn btn-primary pull-right btn-save">
                                    <s:message code="general.save"/>
                                </button>
                                <a href="${app_context}evaluations/list" style="margin-right: 10px;" class="btn btn-default pull-right">
                                    <s:message code="general.back" />
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    <%--    <script type="text/javascript">
            $(document).ready(function() {


                var currentSelectId = "#requirementSelect";
                var responseContainer = $("#divCriterion");
                var urlLoadCriterions = "/requirements/{id}/criterions";
                loadSelectBySelect(urlLoadCriterions, currentSelectId, responseContainer);

                $("#btnSubmit").click(function(event) {

                    // Prevent the form from submitting via the browser.
                    event.preventDefault();
                    saveAnswers();

                });

                // Include CSRF token as header in JQuery AJAX requests
                // See http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
                /*            var token = $("meta[name='_csrf']").attr("content");
                 var header = $("meta[name='_csrf_header']").attr("content");
                 $(document).ajaxSend(function(e, xhr, options) {
                 xhr.setRequestHeader(header, token);
                 });*/

            });
        </script>--%>

    </jsp:body>

</tags:pageTemplate>