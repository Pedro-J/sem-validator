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
        <h3>Criação de Modelos de Experiência do Usuário</h3>
        <p>
          <span class="sprite" id="sprite-drm"></span>
          Modelos de Experiência do Usuário podem auxiliar na diminuição de falhas relacionadas a Experiência do Usuário na fase inicial de um projeto.
        </p>
      </li>
      <li class="col-right">
        <h3>Criação de Checklists de Validação e Verificação</h3>
        <p>
          <span class="sprite" id="sprite-renome"></span>
          Checklists podem ser criadas utilizando questões já presentes no programa ou novas questões adicionadas pelo autor do modelo para avaliar os requisitos de Modelos de Experiência do Usuário.
        </p>
      </li>
      <li class="col-left">
        <h3>Avaliação de Modelos de Experiência do Usuário a Partir de Checklists</h3>
        <p>
          <span class="sprite" id="sprite-atualizacoes"></span>
          Novos modelos ou modelos pré-existentes podem ser avaliados pelo criador do modelo e especialistas da área a partir de checklists de validação e verificação.
        </p>
      </li>
    </ul>
  </section>

</tags:pageTemplate>