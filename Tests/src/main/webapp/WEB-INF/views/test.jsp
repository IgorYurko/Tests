<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/doctype.jsp" %>
<script type="text/javascript" src= '<c:url value = "/resources/scripts/script.buttonsCountdownAjax.js"/>'/></script>
<%@include file="include/header.jsp"%>

	<div id="timer"></div>
	<f:form method="POST" commandName="testForm" class="test_form">
	
		<h2>${ questions.quest }</h2>
		<div id="radio">
		<c:forEach items="${ questions.answers }" var="res">
			
			<f:label path="answerForm" for="answerForm_${ res.id }" >${ res.answer }</f:label>
			<f:radiobutton path="answerForm" id="answerForm_${ res.id }" value="${ res.id }" class="check" />
	
		</c:forEach>
		</div>
		<f:button type="submit" id="submit">Ответить</f:button>

	</f:form>

<%@include file="include/footer.jsp" %>