<%@ page import="com.ashburndev.Temperature" %>



<div class="fieldcontain ${hasErrors(bean: temperatureInstance, field: 'value', 'error')} required">
	<label for="value">
		<g:message code="temperature.value.label" default="Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="value" value="${fieldValue(bean: temperatureInstance, field: 'value')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: temperatureInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="temperature.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${temperatureInstance?.location}"/>

</div>

