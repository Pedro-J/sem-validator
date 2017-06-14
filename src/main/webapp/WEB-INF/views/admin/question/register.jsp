<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="<s:message code='login.message.newAccount' />">
    <div class="container">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <s:message code="login.painel.title"/>
            </div>
            <div class="panel-body">

                <form:form action="${s:mvcUrl('PC#gravar').build() }" method="POST"
                           commandName="produto" enctype="multipart/form-data">
                    <div class="form-group">
                        <label>Titulo</label>
                        <form:input path="titulo" cssClass="form-control" />
                        <form:errors path="titulo" />
                    </div>
                    <div class="form-group">
                        <label>Descição</label>
                        <form:textarea path="descricao" cssClass="form-control" />
                        <form:errors path="descricao" />
                    </div>
                    <div class="form-group">
                        <label>Páginas</label>
                        <form:input path="paginas" cssClass="form-control"/>
                        <form:errors path="paginas" />
                    </div>
                    <div class="form-group">
                        <label>Data de Lançamento</label>
                        <form:input path="dataLancamento" cssClass="form-control"/>
                        <form:errors path="dataLancamento"/>
                    </div>
                    <c:forEach items="${tipos }" var="tipoPreco" varStatus="status">
                        <div class="form-group">
                            <label>${tipoPreco }</label>
                            <form:input path="precos[${status.index }].valor" cssClass="form-control" />
                            <form:hidden path="precos[${status.index }].tipo" value="${tipoPreco }" />
                        </div>
                    </c:forEach>
                    <div class="form-group">
                        <label>Sumário</label>
                        <input name="sumario" type="file" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </form:form>
            </div>
        </div>
    </div>
</tags:pageTemplate>

