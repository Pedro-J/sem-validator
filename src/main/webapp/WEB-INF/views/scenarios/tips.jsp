<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="scenario.detail.title">
    <jsp:attribute name="extraScripts">
        <script type="text/javascript">
            $(document).ready(function() {
                $('.data-table-pagination').DataTable(getCurrentLanguage());
            } );
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <c:if test="${not empty tips}">
                <c:url value="/resources/images" var="imagesPath" />
                <div class="panel panel-primary" style="margin: 0 auto;">
                    <div class="panel-heading text-center">
                        <h5><s:message code="tips.label"/></h5>
                    </div>
                    <div class="panel-body ">
                        <div class="table-responsive">
                            <table class="table table-hover data-table-pagination">
                                <thead>
                                <tr>
                                    <th><s:message code="description.label" /> </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tip" items="${tips}" >
                                    <tr>
                                        <td class="long-text-column width800">${tip.description}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="panel-footer text-center" >
                <c:url value="/" var="app_context" />
                <a href="${app_context}scenarios/list" class="btn btn-default"><s:message code="general.back" /> </a>
            </div>
        </div>
    </jsp:body>
</tags:pageTemplate>
