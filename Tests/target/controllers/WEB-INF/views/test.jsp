<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="include/doctype.jsp" %>
<script type="text/javascript" src= '<c:url value = "/resources/scripts/script.buttonsCountdownAjax.js"/>'/></script>
<%@include file="include/header.jsp"%>

	<div class="timerCss">
		<c:if test="${ timer != null }">
			<ul>
			<c:forEach var="res" begin="0" end="${ timer }">
				<c:choose>
					<c:when test="${(timer - res) >= '10'}">
						<li>${timer - res}</li>
					</c:when>
					<c:otherwise>
						<li>0${timer - res}</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</ul>
		</c:if>
	</div>

	<f:form method="POST" commandName="testForm" class="test_form">

		<h2>${ questions.quest }</h2>
		<div id="radio">
		<c:forEach items="${ questions.answers }" var="res">

			<f:radiobutton path="answerForm" id="answerForm_${ res.id }" value="${ res.id }" class="check" />
			<f:label path="answerForm" for="answerForm_${ res.id }" >${ res.answer }</f:label>

		</c:forEach>
			<f:radiobutton path="answerForm" value="NaN" checked="checked" hidden="hidden" />
		</div>
		<f:button type="submit" id="submit">Ответить</f:button>

	</f:form>

<%@include file="include/footer.jsp" %>