<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<form:form method="post" modelAttribute="jira">
		<form:hidden path="id" />
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label>
			<form:input path="desc" type="text" class="form-control"
				required="required" />
			<form:errors path="desc" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="targetDate">Target Date</form:label>
			<form:input path="targetDate" type="text" class="form-control"
				required="required" />
			<form:errors path="targetDate" cssClass="text-warning" />
		</fieldset>
		
		<!--  
		<fieldset class="form-group">
			<form:label path="done">Is Done</form:label>
			<form:select path="done" name="done">
				<form:option path="done" value="true">true</form:option>
				<form:option path="done" value="false">false</form:option>
			</form:select>
			<form:errors path="done" cssClass="text-warning" />
		</fieldset>
		-->

		<button type="submit" class="btn btn-success">Add</button>
	</form:form>
</div>
<%@ include file="common/footer.jspf" %>