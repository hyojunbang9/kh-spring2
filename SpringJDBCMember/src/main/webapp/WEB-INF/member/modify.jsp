<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./header.jsp"%>

<main>
	<h2>MODIFY</h2>
	<form:form modelAttribute="member" action="modify">
		<form:hidden path="memberNo" />
		<table>
			<tr>
				<td>MEMBER_NAME</td>
				<td><form:input path="name" /></td>
				<td><font color="red"><form:errors path="name" /></font></td>
			</tr>
			<tr>
				<td>MEMBER_ID</td>
				<td><form:input path="id" /></td>
				<td><font color="red"><form:errors path="id" /></font></td>
			</tr>
			<tr>
				<td>MEMBER_PW</td>
				<td><form:textarea path="pwd" /></td>
				<td><font color="red"><form:errors path="pwd" /></font></td>
			</tr>
		</table>
	</form:form>

	<div>
		<button type="submit" id="btnModify">Modify</button>
		<button type="submit" id="btnList">List</button>
	</div>

</main>

<%@ include file="./footer.jsp"%>
<script>
	$(document).ready(function() {
		var formObj = $("#member");
		$("#btnModify").on("click", function() {
			formObj.attr("action", "/member/modify");
			formObj.attr("method", "post");
			formObj.submit();
		});
		$("#btnList").on("click", function() {
			self.location = "/member/list";
		});
	});
</script>


