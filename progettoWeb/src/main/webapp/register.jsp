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
		
        <h1>Register</h1>
		<div>
			<form method="post" action="RegistrationServlet">
				<!-- <input type="hidden" name="op" value="register" /> -->
				<table>
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" value="" required /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="email" name="email" value="" required /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="" required /></td>
					</tr>
					<tr>
						<td>First Name:</td>
						<td><input type="text" name="first_name" value="" required /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type="text" name="last_name" value="" required /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Register" /></td>
					</tr>
				</table>
			</form>
			<%
				if(session.getAttribute("registrazioneFallita") != null){
					session.removeAttribute("registrazioneFallita");
					out.print("Registrazione non riuscita<br>");
				}
			%>
		</div>

    </body>
</html>
