
<%@ page import="com.ashburndev.Temperature" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'temperature.label', default: 'Temperature')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-temperature" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-temperature" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="value" title="${message(code: 'temperature.value.label', default: 'Value')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'temperature.location.label', default: 'Location')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'temperature.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'temperature.lastUpdated.label', default: 'Last Updated')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${temperatureInstanceList}" status="i" var="temperatureInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${temperatureInstance.id}">${fieldValue(bean: temperatureInstance, field: "value")}</g:link></td>
					
						<td>${fieldValue(bean: temperatureInstance, field: "location")}</td>
					
						<td><g:formatDate date="${temperatureInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${temperatureInstance.lastUpdated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${temperatureInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
