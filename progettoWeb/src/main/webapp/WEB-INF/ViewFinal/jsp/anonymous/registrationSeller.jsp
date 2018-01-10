<%-- Pagina di registrazione venditore --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrazione Venditore</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
            <li>
                <div>
                    <img class="centerImg resizeImg showLogo" src="progettoWeb/img/logo/logoFinale.png" height="60" width="60">
                    <div class="header-content">
                        <div class="header-content-inner">
                            <div class="input-group center-block border resizeRegistration">
								<form action="<%=MyPaths.Servlet.Pubbliche.registrationSeller%>" method="POST">
                                    <h1 class="text-center">Registrati come Venditore</h1>
                                    <!--CONTROLLO MAIL E INVIO MESSAGGIO-->

									<div class="form-group">
										<label for="emailRegistration">Email*</label>
										<input type="email" id="emailRegistration" name="email" class="form-control paddingRightInput" placeholder="Email" required
											   value="<%=Model.Messages.consumeString(request, "email")%>">
									</div>
									<div class="form-group">
										<label for="usernameRegistration">Username*</label>
										<input type="text" id="usernameRegistration" name="username" class="form-control paddingRightInput" placeholder="Username" required
											   value="<%=Model.Messages.consumeString(request, "username")%>">
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
											   value="<%=Model.Messages.consumeString(request, "first_name")%>">
									</div>
									<div class="form-group">
										<label for="cognomeRegistration">Cognome*</label>
										<input type="text" id="cognomeRegistration" name="last_name" class="form-control paddingRightInput" placeholder="Cognome" required
											   value="<%=Model.Messages.consumeString(request, "last_name")%>">
									</div>

									<div class="form-group">
										<label for="nomeNeg">Nome Negozio*</label>
										<input type="text" id="nomeNeg" name="nome_neg" class="form-control paddingRightInput" placeholder="Nome Negozio" required
											   value="<%=Model.Messages.consumeString(request, "nome_neg")%>">
									</div>
									<div class="form-group">
										<label for="partitaIVA">Partita IVA*</label>
										<input type="text" id="partitaIVA" name="partita_iva" class="form-control paddingRightInput" placeholder="Partita Iva" required
											   value="<%=Model.Messages.consumeString(request, "partita_iva")%>">
									</div>
                                    <br>
                                    <br>
                                    <div class="text-center checkbox">
                                        <label>
                                            <input type="checkbox" name="privacy" id="privacy" value="" required>Accetto l'informaiva sulla
                                            <a href="/progettoWeb/documenti/InformativaPrivacy.pdf" target="_blank"> privacy</a>
                                        </label>
                                    </div>
                                    <br>
                                    <div class="text-center">
		                                <button class="btn btn-default btn-secondary" href="<%=MyPaths.Jsp.allHome%>">Annulla</button>
                                        <input type="submit" class="btn btn-primary btn-large" value="Registrati"></input>
                                    </div>
                                    <br>
                                </form>
								<% if (Model.Messages.consumeBoolean(request, Model.Messages.registrationSellerFailedBoolean)) {%>
									Registrazione venditore non riuscita!!!<br/>
									<%--<%=Model.Messages.consumeString(request, Model.Messages.registrationSellerFailedString)%><br/>--%>
								<% } %>

                                <!--MESSAGGIO PER ACCETTARE LA PRIVACY
                                <div class="alert alert-danger text-center" role="alert">
                                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                    <span class="sr-only"></span>Accettare l'informativa per la privacy.
                                </div>-->
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>