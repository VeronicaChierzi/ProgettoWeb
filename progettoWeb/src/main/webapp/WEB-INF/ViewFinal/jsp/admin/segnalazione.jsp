<%-- Scheda dettagliata di una segnalazione --%>
<%@page import="java.text.SimpleDateFormat"%>
<%-- Permette all'admin di gestire/rispondere alla segnalazione --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.adminGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneAdmin);%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dettaglio segnalazione</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
			<div class="container">
				<h1 class="paddingTop">Dettaglio segnalazione</h1>

				<% if (Model.Messages.consumeBoolean(request, "rispostaErrore")) { %>
				<div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove-sign"></span> Impossibile inserire la risposta.</div>
				<% } %>
				<% if (Model.Messages.consumeBoolean(request, "rispostaCorretta")) { %>
				<div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Risposta inserita correttamente! La segnalazione &egrave; stata chiusa.</div>
				<% } %>
				<% if (Model.Messages.consumeBoolean(request, "segnalazioneNonEsiste")) { %>
				<div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-info-sign"></span> Risposta non inserita perch&egrave; la segnalazione non esiste!</div>
				<% } %>
				<div id="puzzette" style="margin-left: auto; margin-right: auto; display: table;">
					<% if (s != null) {%>

					<li>
						<%=(s.isOpen() ? ""
								+ "<div class=\"price\" style=\"color: green; margin-bottom: 4px; \">"
								+ "APERTA"
								+ "</div>" : ""
								+ "<div class=\"price\" style=\"color: red; margin-bottom: 4px;\">"
								+ "CHIUSA"
						+ "</div>")%>
					</li>
					<li>
						<h5 class="price" style="font-size: 16px;"><%=s.getTitle()%> - Rif. ordine: <a href="<%=MyPaths.Jsp.userOrder + "?id=" + s.getIdOrder()%>"><%=s.getIdOrder()%></a></h5>
					</li>
					<li>
						<h5><%=s.getDescription()%></h5>
					</li>
					<li style="border-bottom-color: black; border-bottom-style: solid; border-bottom-width: 1px;">
						<h5 style="margin-bottom: 20px;"><%=new SimpleDateFormat("dd/MM/yyyy").format(s.getDatetime())%></h5>
					</li>

					<% if (s.getSegnalazioneRisposta() != null) {%>                
					<li>
						<h3>Risposta</h3>                
						<div style="font-style: oblique">
							<%= (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("a") ? "APPROVATA" : (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("n")) ? "NON PROCEDO" : "RIGETTATA")%><br>
						</div>
						<div><%= s.getSegnalazioneRisposta().getMessage()%><br>
						</div>
						<%= (s.getSegnalazioneRisposta().isRestituireSoldi() ? "Rimborso avviato<br>" : "")%> 
						<%= (s.getSegnalazioneRisposta().isValutazioneNegativaVenditore() ? "Al venditore è stato dato un feedback negativo." : "")%> 

					</li>

					<% } else {%>
					<li>
						<h3>Rispondi alla segnalazione</h3>
						<form action="<%=MyPaths.Servlet.Pubbliche.rispondiSegnalazione%>" method="post">
							<input type="hidden" name="idSegnalazione" value="<%=s.getId()%>"/>
							<div style="font-size: 20px;">Decisione</div>
							<fieldset id="decis" style="display: flex; margin-bottom: 20px; ">
								<div>                        
									<input type="radio" name="decisione" value="a" checked>Approvo   
								</div>
								<div>                        
									<input type="radio" name="decisione" value="r"> Rigetto   
								</div>
								<div>                        
									<input type="radio" name="decisione" value="n"> Non procedo   
								</div>
							</fieldset>
							Messaggio<br>
							<textarea style="width: 100%; height: 100px; border-style: hidden; margin-bottom: 10px;" type="text" name="message" value=""></textarea>
							<br>
							<input type="checkbox" name="rimborso" value="true"> Disponi rimborso <br>  
							<input type="checkbox" name="negVal" value="true"> Valutazione negativa venditore<br>
							<button style="margin-top: 20px; margin-left: auto; margin-right: auto; display: table;" class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-plus-sign"></span>   Chiudi segnalazione</button>
						</form>
					</li>
					<% }%>
					<% }%>
				</div>
			</div>
        </ul>
    </body>
</html>