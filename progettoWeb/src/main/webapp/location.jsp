<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Location</h1>
		<jsp:include page="/LocationServlet" flush="true" />

		ricomincia jsp<br/>
		
		<%--
		<%@page session="true" %>
		<%@page import="it.unitn.disi.entities.locations.Location" %>
		<% Location location = (Location) application.getAttribute("location"); %>
		<%=location.getRegione(3).getName() %>
		--%>
		
		asddsfsadfsd
		
    </body>
</html>
