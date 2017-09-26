<%@page session="true" %>
<%@page import="it.unitn.disi.entities.User" %>
<% User user = (User) session.getAttribute("user");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="sfondo"> <!--da sistemare con cicli per cambio immagini-->
		<jsp:include page="/navbar.jsp" />
		<h1>Profilo utente</h1>
        Se la vedi dovresti essere loggato<br/>
		<br/>

		id: <%=user.getId()%><br/>
		username: <%=user.getUsername()%><br/>
		email: <%=user.getEmail()%><br/>
		password: <%=user.getPassword()%><br/>
		first name: <%=user.getFirstName()%><br/>
		last name: <%=user.getLastName()%><br/>
		<br/>
		
		isSeller: <%=user.isSeller()%><br/>
		<% if (user.isSeller()) { %>
		id: <%=user.getUserSeller().getId()%><br/>
		name: <%=user.getUserSeller().getName()%><br/>
		partita iva: <%=user.getUserSeller().getPartitaIva()%><br/>
		<% }%>
		<br/>
		
		isAdmin: <%=user.isAdmin()%><br/>
		<% if (user.isAdmin()) { %>
		id: <%=user.getUserAdmin().getId()%><br/>
		<% }%>
		<br/>
		
    </body>
</html>
