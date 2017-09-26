<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
		<jsp:include page="/navbar.jsp" />
        <h1>Hello World!</h1>

		<h1>Login</h1>
		<div>
			<form method="post" action="LoginServlet">
				<input type="hidden" name="op" value="login" />
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="email" name="email" value="" /></td>
					</tr>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" value="" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Log In" /></td>
					</tr>
				</table>
			</form>
			<%
				if(session.getAttribute("loginFallito") != null){
					out.print(session.getAttribute("loginFallito"));
					session.removeAttribute("loginFallito");
				}
			%>
		</div>

    </body>
</html>
