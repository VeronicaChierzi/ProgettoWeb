<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<div>
	<div class="header-content">
		<div class="header-content-inner">
			<div class="input-group center-block border resizeRegistration">
				<form action="RegistrationSellerServlet" method="POST">
					<h1 class="text-center">Registrati come Venditore</h1>
					<br><div><% //if (session.getAttribute("username") != null) {
						//out.print((String) session.getAttribute("username"));
						//session.removeAttribute("username");
									//}%></div>
					<!--CONTROLLO MAIL E INVIO MESSAGGIO-->
					<div class="form-group">
						<label for="nomeNeg">Nome Negozio*</label>
						<input type="text" id="nomeNeg" name="name" class="form-control paddingRightInput" placeholder="Nome Negozio" required>
					</div>
					<br>
					<div class="form-group">
						<label for="partitaIVA">Partita IVA*</label>
						<input type="text" id="partitaIVA" name="partita_iva" class="form-control" placeholder="Partita Iva" required>
					</div>
					<br><br>
					<div class="text-center">

						<input type="submit" class="btn btn-primary btn-large" value="Registrati"></input>
					</div>
				</form>
				<input type="submit" class="btn btn-large btn-secondary" value="Annulla"></input> 

				<% if (Model.Messages.consumeAttribute(request, Model.Messages.registrationSellerFailed)) { %>
				Registrazione venditore non riuscita!!!<br/>
				<% }%>
			</div>
		</div>
	</div>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>