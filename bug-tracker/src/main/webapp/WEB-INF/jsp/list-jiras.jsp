<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Your Jiras are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${jiras}" var="jira">
					<tr>
						<td>${jira.desc}</td>
						<td><fmt:formatDate value="${jira.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${jira.done}</td>
						<td><a type="button" class="btn btn-success"
							href="./update-jira?id=${jira.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="./delete-jira?id=${jira.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="./add-jira">Add a Jira</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>