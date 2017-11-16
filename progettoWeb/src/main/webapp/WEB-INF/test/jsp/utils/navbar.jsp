<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User" %>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>

<% User user = (User) Model.Session.getAttribute(request, Model.Session.user);%>
<div>
	<ul>
		<li><a href="<%=MyPaths.Public.Jsp.All.home%>">Home</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.All.cart%>">Carrello</a></li>
			<% if (user == null) {%>
		<li><a href="<%=MyPaths.Public.Jsp.Anonymous.login%>">Login</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.Anonymous.registration%>">Register</a></li>
			<% } else {%>
		<li><a href="<%=MyPaths.Public.Jsp.User.profile%>"><%=user.getUsername()%></a></li>
		<li><a href="<%=MyPaths.Public.Jsp.User.orders%>">ordini</a></li>
		<li><a href="<%=MyPaths.Public.Servlet.User.logout%>">Logout</a></li>
			<% if (user.isSeller()) {%>
			<% UserSeller seller = user.getUserSeller();%>
		<li><a href="<%=MyPaths.Public.Jsp.Seller.mySeller%>"><%=seller.getName()%></a></li>		
			<% } %>
			<% if (user.isAdmin()) {%>
			<% } %>
			<% }%>
	</ul>
</div>
