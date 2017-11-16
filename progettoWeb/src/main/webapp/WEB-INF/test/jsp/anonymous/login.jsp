<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<div>
	<h1>Login</h1>
	<form action="<%=MyPaths.Public.Servlet.Anonymous.login%>" method="post">
		<div>
			<label>Username (email)</label>
			<input type="text" name="username" placeholder="Email" value="" required/>
		</div>
		<div>
			<label>Password</label>
			<input type="password" name="password" placeholder="Password" value="" required/>
		</div>
		<br>
		<div>
			<a href="passwordDimenticata.jsp">Password dimenticata?</a>
		</div>
		<br>
		<div>
			<input type="submit" value="Accedi"></input>
		</div>
		<br/>
	</form>
	<% if (Model.Messages.consumeAttribute(request, Model.Messages.loginFailed)) { %>
	Login non riuscito!!!<br/>
	<% }%>
	<div>
		Dati account<br/>
		username/email: "verdi" oppure "verdi@gmail.com"<br/>
		password: "Verdi1."<br/>
	</div>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>