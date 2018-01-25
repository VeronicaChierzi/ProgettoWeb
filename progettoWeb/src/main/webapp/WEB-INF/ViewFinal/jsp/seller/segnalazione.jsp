<%-- Scheda dettagliata di una segnalazione --%>
<%@page import="java.text.SimpleDateFormat"%>
<%-- Permette al venditore di visionare una segnalazione ricevuta --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneSeller);%>

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
						<h5  class="price" style="font-size: 16px;"><%=s.getTitle()%> - Rif. ordine: <a href="<%=MyPaths.Jsp.sellerOrder + "?id=" + s.getIdOrder()%>"><%=s.getIdOrder()%></a></h5>
					</li>
					<li>
						<h5><%=s.getDescription()%></h5>
					</li>
					<li>
						<h5><%=new SimpleDateFormat("dd/MM/yyyy").format(s.getDatetime())%></h5>
					</li>

					<% if (s.getSegnalazioneRisposta() != null) {%>
					
					<li style="border-top-color: black; border-top-style: solid; border-top-width: 1px;  margin-bottom: 5px;">
						<h3>Risposta</h3>                
					</li>
					<li>
						<div style="font-style: oblique">
							<%= (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("a") ? "APPROVATA" : (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("n")) ? "NON PROCEDO" : "RIGETTATA")%><br>
						</div>
						<div>
							<%= s.getSegnalazioneRisposta().getMessage()%><br>
						</div>
						<%= (s.getSegnalazioneRisposta().isRestituireSoldi() ? "Rimborso avviato<br>" : "")%> 
						<%= (s.getSegnalazioneRisposta().isValutazioneNegativaVenditore() ? "Ti è stato dato un feedback negativo." : "")%> 

					</li>

					<% } else { %>
					<li style="border-top-color: black; border-top-style: solid; border-top-width: 1px;">
						<h5 style="margin-top: 20px;">L'amministratore non ha ancora risposto alla segnalazione.</h5>
					</li>
					<% }%>
					<% }%>
				</div>
			</div>
        </ul>
    </body>
</html>