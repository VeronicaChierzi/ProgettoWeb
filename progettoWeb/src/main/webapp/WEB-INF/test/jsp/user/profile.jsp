<%@page import="it.unitn.disi.entities.UserAdmin"%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<% User user = Model.Session.getUserLogged(request); %>
<div>
	<h1>Profilo utente</h1>

	id: <%=user.getId()%><br/>
	username: <%=user.getUsername()%><br/>
	email: <%=user.getEmail()%><br/>
	password: <%=user.getPassword()%><br/>
	first name: <%=user.getFirstName()%><br/>
	last name: <%=user.getLastName()%><br/>
	hash: <%=user.getHash()%><br/>
	verificato: <%=user.isVerificato()%><br/>
	<br/>

	isSeller: <%=user.isSeller()%><br/>
	<% if (user.isSeller()) {%>
		<% UserSeller seller = user.getUserSeller(); %>
		id: <%=seller.getId()%><br/>
		name: <%=seller.getName()%><br/>
		partita iva: <%=seller.getPartitaIva()%><br/>
	<% } else { %>
		<a href="/progettoWeb/registerSeller.jsp">Registrati come venditore</a><br/>
	<% } %>
	<br/>

	isAdmin: <%=user.isAdmin()%><br/>
	<% if (user.isAdmin()) {%>
		<% UserAdmin admin = user.getUserAdmin(); %>
		id: <%=admin.getId()%><br/>
	<% } %>
	<br/>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>