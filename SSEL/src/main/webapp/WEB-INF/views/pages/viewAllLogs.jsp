<%@page import="org.springframework.ui.Model"
	import="com.softserve.entity.Log" import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="row">
	<div class="col-lg-12">
		<h1 align="center">This will add some logs!</h1>
		<%
			List<Log> logList = (List<Log>) session.getAttribute("logs");
		%>
		<br />
		<%=logList.size()%><br />
		<table border="2px">
			<thead>
				<tr>
					<th>Date</th>
					<th>Level</th>
					<th>Logger</th>
					<th>Massage</th>
					<th>Exception</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 0;
				%>
				<c:forEach var="i" begin="1" end="<%=logList.size()%>">
					<tr>
						<td><%=logList.get(i).getEventDate()%></td>
						<td><%=logList.get(i).getLevel()%></td>
						<td><%=logList.get(i).getLogger()%></td>
						<td><%=logList.get(i).getMassage()%></td>
						<td><%=logList.get(i).getException()%></td>
					</tr>
					<%
						i++;
					%>
				</c:forEach>

			</tbody>
		</table>
		<br>
	</div>
</div>
