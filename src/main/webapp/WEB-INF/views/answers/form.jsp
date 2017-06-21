<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="'criterion.add.title'">
    <jsp:body>
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-body">
                <s:url value="/answer" var="formActionUrl"/>

                <form class="form-horizontal" method="POST" action="${formActionUrl}" acceptCharset="UTF-8">

                    <div class="form-group">
                        ${model.title}
                    </div>

                    <div class="form-group">
                        ${checklist.title}
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="requirements.label"/>:</label>
                        <div class="col-sm-7">
                            <select id="requirementSelect">
                                <c:forEach items="${checklist.requirements}" var="item">
                                    <option value="item.id">item.description</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label"><s:message code="criterions.label"/>:</label>
                        <div class="col-sm-7">
                            <select id="criterionSelect">
                            </select>
                        </div>
                        <div class="col-sm-3"></div>
                    </div>



                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button id="btnSubmit" type="submit" class="btn btn-primary pull-right">
                                <s:message code="general.save"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </jsp:body>

    <jsp:attribute name="extraScripts">
    <script type="text/javascript">
        $(document).ready(function() {

            var currentSelect = $("#requirementSelect");
            var selectToBeLoad = $("#criterionSelect");
            loadSelectBySelect("requirements/"+ currentSelect.value +"/criterions",currentSelect, selectToBeLoad);


            // Include CSRF token as header in JQuery AJAX requests
            // See http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
/*            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });*/

        });
    </script>
    </jsp:attribute>
</tags:pageTemplate>