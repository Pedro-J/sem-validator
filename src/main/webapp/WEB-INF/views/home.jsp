<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="general.page.home.title" >
  <section id="index-section" class="container middle">

    <h2 class="cdc-call"><s:message code="app.title" /> </h2>
   
    <ul id="cdc-diferenciais" class="clearfix">
      <li class="col-left">
        <h3><s:message code="app.feature.1.title" /> </h3>
        <p>
          <span class="sprite" id="sprite-drm"></span>
          <s:message code="app.feature.1.description" />
        </p>
      </li>
      <li class="col-right">
        <h3><s:message code="app.feature.2.title" /></h3>
        <p>
          <span class="sprite" id="sprite-renome"></span>
          <s:message code="app.feature.2.description" />
        </p>
      </li>
      <li class="col-left">
        <h3><s:message code="app.feature.3.title" /></h3>
        <p>
          <span class="sprite" id="sprite-atualizacoes"></span>
          <s:message code="app.feature.3.description" />
        </p>
      </li>
    </ul>
  </section>

</tags:pageTemplate>