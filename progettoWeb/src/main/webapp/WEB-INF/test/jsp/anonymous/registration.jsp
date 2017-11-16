<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<div>
	<form action="<%=MyPaths.Public.Servlet.Anonymous.register%>" method="POST">
		<h1 class="text-center">Registrati</h1>
		<!--CONTROLLO MAIL E INVIO MESSAGGIO-->

		<div class="form-group">
			<label for="emailRegistration">Email*</label>
			<input type="email" id="emailRegistration" name="email" class="form-control paddingRightInput" placeholder="Email" required
				   value="<% if (session.getAttribute("email") != null) {
						   out.print((String) session.getAttribute("email"));
						   session.removeAttribute("email");
					   }%>">
		</div>
		<div class="form-group">
			<label for="usernameRegistration">Username*</label>
			<input type="text" id="usernameRegistration" name="username" class="form-control paddingRightInput" placeholder="Username" required
				   value="<% if (session.getAttribute("username") != null) {
						   out.print((String) session.getAttribute("username"));
						   session.removeAttribute("username");
					   }%>">
		</div>
		<!--VEDERE COME METTERE TIC VISUALIZZATO QUANDO PASSWORD GIUSTA ENTRAMBI-->
		<!--METTERE AVVISO PER LUNGHEZZA 8 CARATTERI ALMENO 1 NUMERO-->
		<div class="form-group">
			<label for="passwordRegistration">Password* </label>
			<input type="password" id="passwordRegistration" name="password" class="form-control paddingRightInput" placeholder="Password" required>
		</div>
		<div class="form-group">
			<label for="passwordRegistrationRip">Ripeti Password* </label>
			<input type="password" id="passwordRegistrationRip" name="password2" class="form-control paddingRightInput" placeholder="Password" required>
		</div>
		<div class="form-group">
			<label for="nameRegistration">Nome*</label>
			<input type="text" id="nameRegistration" name="first_name" class="form-control paddingRightInput" placeholder="Nome" required
				   value="<% if (session.getAttribute("first_name") != null) {
						   out.print((String) session.getAttribute("first_name"));
						   session.removeAttribute("first_name");
					   }%>">
			<div class="form-group">
				<label for="cognomeRegistration">Cognome*</label>
				<input type="text" id="cognomeRegistration" name="last_name" class="form-control paddingRightInput" placeholder="Cognome" required
					   value="<% if (session.getAttribute("last_name") != null) {
							   out.print((String) session.getAttribute("last_name"));
							   session.removeAttribute("last_name");
						   }%>">
			</div>
		</div>
		<br>
		<br>
		<div class="text-center">
			<a href="/progettoWeb/registerSellerNoLog.jsp">Vai alla registrazione come VENDITORE</a>
		</div>
		<div class="text-center checkbox">
			<label>
				<input type="checkbox" name="privacy" id="privacy" value="" required>Accetto l'informaiva sulla
				<a href="documenti/InformativaPrivacy.pdf" target="_blank"> privacy</a>
			</label>
		</div>
		<br>
		<div class="text-center">
			<input type="submit" class="btn btn-large btn-secondary" value="Annulla"></input> 

			<input type="submit" class="btn btn-primary btn-large" value="Registrati"></input>
		</div>
		<br>

	</form>
	<% if (Model.Messages.consumeAttribute(request, Model.Messages.registrationFailed)) { %>
	Registrazione non riuscita!!!<br/>
	<% }%>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>