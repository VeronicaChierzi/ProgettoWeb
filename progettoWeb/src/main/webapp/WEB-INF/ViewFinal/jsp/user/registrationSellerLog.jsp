<%-- Pagina di registrazione come venditore (l'utente deve essere già loggato e non deve essere già venditore --%>
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
                <img class="centerImg resizeImg showLogo" src="/progettoWeb/img/logo/logoFinale.png" height="50" width="50">
                <div class="header-content">
                    <div class="header-content-inner">
                        <div class="input-group center-block border resizeRegistration">
							<form action="<%=MyPaths.Servlet.Pubbliche.registrationSellerLog%>" method="POST">
                                <h1 class="text-center">Registrati come Venditore</h1>
                                <br>
								<!--CONTROLLO MAIL E INVIO MESSAGGIO-->
								<div class="form-group">
									<label for="nomeNeg">Nome Negozio*</label>
									<input type="text" id="nomeNeg" name="nome_neg" class="form-control paddingRightInput" placeholder="Nome Negozio" required
									   value="<%=Model.Messages.consumeString(request, "nome_neg")%>">
								</div>
								<br>
								<div class="form-group">
									<label for="partitaIVA">Partita IVA*</label>
									<input type="text" id="partitaIVA" name="partita_iva" class="form-control" placeholder="Partita Iva" required
									   value="<%=Model.Messages.consumeString(request, "partita_iva")%>">
								</div>
                                <br>
								<br>
                                <div class="text-center">
	                                <button class="btn btn-default btn-secondary" href="<%=MyPaths.Jsp.allHome%>">Annulla</button>
                                    <input type="submit" class="btn btn-primary btn-large" value="Registrati"></input>
                                </div>
                            </form>

							<% if (Model.Messages.consumeBoolean(request, Model.Messages.registrationSellerLogFailedBoolean)) {%>
								Registrazione venditore non riuscita!!!<br/>
								<%--<%=Model.Messages.consumeString(request, Model.Messages.registrationSellerLogFailedBoolean)%><br/>--%>
							<% } %>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </body>
</html>