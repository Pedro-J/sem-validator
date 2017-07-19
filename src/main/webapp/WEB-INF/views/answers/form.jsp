<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="answer.add.title">
    <jsp:attribute name="extraScripts">
        <script type="text/javascript" src="/resources/js/answerTable.js"></script>
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
                    <h4><s:message code="answer.add.title"/></h4>
                </div>
                <div class="panel-body">
                    <s:url value="/answer" var="formActionUrl"/>
                    <s:message code="select.label" var="select" />
                    <form class="form-horizontal" method="POST" action="${formActionUrl}" acceptCharset="UTF-8">
                        <input id="init_value" type="hidden" value="${select}"/>
                        <input id="id_checklist" type="hidden" value="${checklist.id}"/>

                        <div class="form-group">
                            <label class="col-sm-2"><s:message code="app.entity.checklist" />:</label>
                            <div class="col-sm-8">${checklist.title}</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2"><s:message code="app.entity.model" />:</label>
                            <div class="col-sm-8">${checklist.model.title}</div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2"><s:message code="app.entity.model" />:</label>
                            <div class="col-sm-8">mais dados do modelo</div>
                        </div>

                        <div class="form-group">
                            <div class="panel panel-primary" style="width: 80%;margin: 10px auto;">
                                <div class="panel-heading text-center">
                                    <h5><s:message code="answers.label"/></h5>
                                </div>
                                <div class="panel-body ">
                                    <div id="questionsContainer" class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                            <tr>
                                                <th colspan="3" class="text-center"><s:message code="app.entity.question" /> </th>
                                                <th colspan="1" class="text-center"><s:message code="app.entity.answer" /> </th>
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
                                            <li class="page-item page-prev disabled">
                                                <span class="page-link">Previous</span>
                                            </li>
                                            <li class="page-item page-left active">
                                                <span class="page-link" >1</span>
                                            </li>
                                            <li class="page-item page-middle">
                                                <span class="page-link">2</span>
                                            </li>
                                            <li class="page-item page-right">
                                                <span class="page-link" >3</span>
                                            </li>
                                            <li class="page-item page-next">
                                                <span class="page-link" >Next</span>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" class="btn btn-primary pull-right btn-save">
                                    <s:message code="general.save"/>
                                </button>
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