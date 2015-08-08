<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/doctype.jsp" %>
<script type="text/javascript" src= '<c:url value = "/resources/scripts/script.progressbarTooltip.js"/>'/></script>
<%@ include file="include/header.jsp" %>

	<h1 id="result">
	    Ваш результат: <span>${result}</span>% правильных ответов!!!
	</h1>

	<div id="progressbar"></div>

	<a href="<%= request.getContextPath() %>">
		<img src='<c:url value ="/resources/images/smile.png"/>' width="200" title="На главную"/>
	</a>
	
<%@ include file="include/footer.jsp" %>