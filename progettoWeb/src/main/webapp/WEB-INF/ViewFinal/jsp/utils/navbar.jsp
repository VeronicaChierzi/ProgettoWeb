<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User" %>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>

<% User user = (User) Model.Session.getAttribute(request, Model.Session.user);%>
<div>
	<ul>
		<li><a href="<%=MyPaths.Jsp.allHome%>">Home</a></li>
		<li><a href="<%=MyPaths.Jsp.allCart%>">Carrello</a></li>
			<% if (user == null) {%>
		<li><a href="<%=MyPaths.Jsp.anonymousLogin%>">Login</a></li>
		<li><a href="<%=MyPaths.Jsp.anonymousRegistration%>">Register</a></li>
			<% } else {%>
		<li><a href="<%=MyPaths.Jsp.userProfile%>"><%=user.getUsername()%> (Profilo utente)</a></li>
		<li><a href="<%=MyPaths.Jsp.userOrders%>">ordini</a></li>
		<li><a href="<%=MyPaths.Servlet.Pubbliche.logout%>">Logout</a></li>
			<% if (user.isSeller()) {%>
			<% UserSeller seller = user.getUserSeller();%>
		<li><a href="<%=MyPaths.Jsp.sellerMySeller%>"><%=seller.getName()%></a></li>		
			<% } %>
			<% if (user.isAdmin()) {%>
			<% } %>
			<% }%>
	</ul>
</div>
