<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="checklist.list.title" >
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
                <strong><s:message code="${msgTitle}" /><s:message code="app.entity.checklist"/>&nbsp;<s:message code="${msgContent}" /></strong>
            </div>
        </c:if>
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h4><s:message code="checklist.list.title" /></h4>
            </div>
            <div class="panel-body">

                <table class="table table-bordered data-table-pagination">
                    <thead>
                    <tr>
                        <th><s:message code="title.label" /></th>
                        <th><s:message code="checklistType.label" /></th>
                        <th><s:message code="actions.label" /></th>
                    </tr>
                    </thead>

                    <c:forEach var="checklist" items="${checklists}">
                        <tr>
                            <td class="width600"><div class="long-text-column">${checklist.title}</div></td>
                            <td class="width200"><div class="long-text-column"><s:message code="${checklist.checklistType.messageCode}" /></div></td>
                            <td class="actions-column">
                                <s:message code="general.delete" var="delete"/>
                                <s:message code="general.edit" var="edit"/>
                                <s:message code="general.seeDetail" var="detail"/>


                                <s:url value="/checklists/${checklist.id}" var="detailUrl" />
                                <s:url value="/checklists/${checklist.id}/delete" var="deleteUrl" />
                                <s:url value="/checklists/${checklist.id}/update" var="updateUrl" />


                                <button class="btn btn-info glyphicon glyphicon-zoom-in"
                                        onclick="location.href='${detailUrl}'" title="${detail}"></button>
                                <button class="btn btn-primary glyphicon glyphicon-edit"
                                        onclick="location.href='${updateUrl}'" title="${edit}"></button>
                                <button class="btn btn-danger glyphicon glyphicon-remove"
                                        onclick="this.disabled=true;post('${deleteUrl}');" title="${delete}"></button>


                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
    </jsp:body>
</tags:pageTemplate>