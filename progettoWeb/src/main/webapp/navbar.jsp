<%@page session="true" %>
<%@page import="it.unitn.disi.entities.User" %>
<% User user = (User) session.getAttribute("user"); %>

<ul>
	<li><a href="/progettoWeb/index.jsp">Home</a></li>
	<li><a href="/progettoWeb/carrello.jsp">Carrello</a></li>
		<% if (user == null) { %>
	<li><a href="/progettoWeb/login.jsp">Login</a></li>
	<li><a href="/progettoWeb/register.jsp">Register</a></li>
		<% } else {%>
	<li><a href="/progettoWeb/user/profilo.jsp"><%= user.getUsername()%></a></li>
	<li><a href="/progettoWeb/user/ordini.jsp">ordini</a></li>
	<li><a href="/progettoWeb/LogoutServlet">Logout</a></li>
		<% }%>
	<li><a href="/progettoWeb/location.jsp">location(test)</a></li>
	<li><a href="/progettoWeb/ProductServlet?id=1">product1(test)</a></li>
	<li><a href="/progettoWeb/product.jsp?id=2">product2(test)</a></li>
</ul>
