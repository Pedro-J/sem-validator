<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="question.list.title" >
    <div class="container">
        <c:if test="${not empty msgContent}">
            <div class="alert alert-${msgCSS} alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <strong><s:message code="${msgTitle}" /><s:message code="app.entity.question"/>&nbsp;<s:message code="${msgContent}" /></strong>
            </div>
        </c:if>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="question.list.title" /></h4>
            </div>
            <div class="panel-body">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Number</th>
                        <th>Description</th>
                        <th>Criterion</th>
                        <th>Tip</th>
                    </tr>
                    </thead>

                    <c:forEach var="question" items="${questions}">
                        <tr>
                            <td>${question.number}</td>
                            <td>${question.description}</td>
                            <td>${question.criterion.description}</td>
                            <td>${question.tip}</td>
                            <td>
                                <s:url value="/questions/${question.id}" var="detailUrl" />
                                <s:url value="/questions/${question.id}/delete" var="deleteUrl" />
                                <s:url value="/questions/${question.id}/update" var="updateUrl" />

                                <button class="btn btn-info" onclick="location.href='${detailUrl}'">Query</button>
                                <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                                <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}');">Delete</button></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
</tags:pageTemplate>