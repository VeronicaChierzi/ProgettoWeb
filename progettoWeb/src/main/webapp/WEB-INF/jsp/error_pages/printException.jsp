<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page isErrorPage="true" %>

<% if(exception==null){ %>
	Exception is null
<% } else { %>
	<b>Exception Class:</b>
	<div style="border:1px solid black;">
		<%= exception.getClass()%>
	</div>
	<br/>
	<b>Exception Message:</b>
	<div style="border:1px solid black;">
		<%= exception.getMessage()%>
	</div>
	<br/>

	<b>StackTrace:</b>
	<div style="border:1px solid black;">
		<%
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			exception.printStackTrace(printWriter);
			out.println(stringWriter);
			printWriter.close();
			stringWriter.close();
		%>
	</div>
<% } %>
