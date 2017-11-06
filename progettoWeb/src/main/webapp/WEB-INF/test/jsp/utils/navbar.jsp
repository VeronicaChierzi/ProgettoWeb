<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.User" %>
<% User user = Model.Session.getUser(request);%>

<div>
	<ul>
		<li><a href="<%=MyPaths.Public.Jsp.All.home%>">Home</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.User.cart%>">Carrello</a></li>
			<% if (user == null) { %>
		<li><a href="<%=MyPaths.Public.Jsp.Anonymous.login%>">Login</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.Anonymous.register %>">Register</a></li>
			<% } else {%>
		<li><a href="<%=MyPaths.Public.Jsp.User.profile%>"><%//= user.getUsername()%></a></li>
		<li><a href="<%=MyPaths.Public.Jsp.User.orderList%>">ordini</a></li>
		<li><a href="<%=MyPaths.Public.Servlet.User.logout%>">Logout</a></li>
			<% } %>
		<li><a href="<%=MyPaths.Public.Jsp.Debug.navbarComplete%>">navbarComplete(test)</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.Debug.locationContainer%>">location(test)</a></li>
		<li><a href="<%=MyPaths.Public.Jsp.Debug.categoryContainer%>">categoryContainer(test)</a></li>
		<li><a href="/progettoWeb/ProductServlet?id=1">product1(test)</a></li>
		<li><a href="/progettoWeb/product.jsp?id=2">product2(test)</a></li>
	</ul>
</div>

<div>
	<ul>
		<li><a href="/progettoWeb/index.jsp">Home</a></li>
		<li><a href="/progettoWeb/carrello.jsp">Carrello</a></li>
			<% if (user == null) { %>
		<li><a href="/progettoWeb/login.jsp">Login</a></li>
		<li><a href="/progettoWeb/register.jsp">Register</a></li>
			<% } else {%>
		<li><a href="/progettoWeb/user/profilo.jsp"><%//= user.getUsername()%></a></li>
		<li><a href="/progettoWeb/user/ordini.jsp">ordini</a></li>
		<li><a href="/progettoWeb/LogoutServlet">Logout</a></li>
			<% } %>
		<li><a href="/progettoWeb/location.jsp">location(test)</a></li>
		<li><a href="/progettoWeb/ProductServlet?id=1">product1(test)</a></li>
		<li><a href="/progettoWeb/product.jsp?id=2">product2(test)</a></li>
	</ul>
</div>