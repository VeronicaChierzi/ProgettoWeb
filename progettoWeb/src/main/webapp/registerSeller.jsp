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
		
        <h1>Register Seller</h1>
		questa pagina dovrebbe essere visualizzata solo se l'utente è loggato.<br/>
		da sistemare!!!!!!!!!!!!!!!!!!!!!!!!!!!<br/>
		<div>
			<form method="post" action="RegistrationSellerServlet">
				<!-- <input type="hidden" name="op" value="register" /> -->
				<table>
					<tr>
						<td>name:</td>
						<td><input type="text" name="name" value="" required /></td>
					</tr>
					<tr>
						<td>partita iva:</td>
						<td><input type="text" name="partita_iva" value="" required /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Register" /></td>
					</tr>
				</table>
			</form>
			<%
				if(session.getAttribute("registrazioneVenditoreFallita") != null){
					session.removeAttribute("registrazioneVenditoreFallita");
					out.print("Registrazione venditore non riuscita<br>");
				}
			%>
		</div>

    </body>
</html>
