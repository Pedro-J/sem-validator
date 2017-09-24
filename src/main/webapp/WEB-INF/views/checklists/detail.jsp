<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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

                <c:if test="${not empty questions}">
                    <c:url value="/resources/images" var="imagesPath" />
                    <div class="row">
                        <div class="panel panel-primary" style="width: 90%;margin: 0 auto;">
                            <div class="panel-heading text-center">
                                <h5><s:message code="questions.label"/></h5>
                            </div>
                            <div class="panel-body ">
                                <div id="questionsContainer" class="table-responsive">
                                    <table class="table table-hover data-table-pagination">
                                        <thead>
                                        <tr>
                                            <th><s:message code="numeration.label" /> </th>
                                            <th><s:message code="app.entity.question" /> </th>
                                            <th><s:message code="tip.label" /> </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="question" items="${questions}" >
                                            <tr>
                                                <td>${question.numeration}</td>
                                                <td class="long-text-column width800">${question.description}</td>
                                                <td> <img src="${imagesPath}/tip_info.png" title="${question.tip}" class="tip-info-img" /></td>
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
    </jsp:body>
</tags:pageTemplate>
