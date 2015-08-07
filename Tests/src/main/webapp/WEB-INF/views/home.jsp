<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="include/doctype.jsp"%>
<script type="text/javascript" src= '<c:url value = "/resources/scripts/script.checkCookieTooltip.js"/>'/></script>
<%@ include file="include/header.jsp"%>

	<div class="err ui-state-error ui-corner-all">
		<p>
			<span class="ui-icon ui-icon-alert" style=""></span>
			<strong>Alert:</strong> 
			<span>${ err }</span>
		</p>
	</div>
	
	<div class="grid">
		<c:forEach items="${category}" var="res">
			<figure class="effect-layla">
				<img src='<c:url value ="/resources/images/${ res.image }"/>' alt="img04" />
				<figcaption>
					<h2>${ res.name }</h2>
					<p>${ res.description }</p>
					<a href="<%= request.getContextPath() %>?category=${ res.id }">View
						more</a>
				</figcaption>
			</figure>
		</c:forEach>
	</div>

<%@ include file="include/footer.jsp"%>