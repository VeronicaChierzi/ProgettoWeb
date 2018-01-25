<%-- Scheda dettagliata di una segnalazione sporta dall'utente riguardo ad un ordine effettuato --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneUser);%>

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

            <h1 class="paddingTop">Dettaglio segnalazione</h1>

            <% if (s != null) {%>

            <li>
                <%=(s.isOpen() ? "APERTA" : "CHIUSA")%>
            </li>
            <li>
                <h5><%=s.getTitle()%> - Rif. ordine: <a href="<%=MyPaths.Jsp.userOrder + "?id=" + s.getIdOrder()%>"><%=s.getIdOrder()%></a></h5>
            </li>
            <li>
                <h5><%=s.getDescription()%></h5>
            </li>
            <li>
                <h5><%=s.getDatetime()%></h5>
            </li>

            <% if (s.getSegnalazioneRisposta() != null) {%>
            <li>
                L'amministratore ha risposto alla segnalazione.                
            </li>
            <li>
                Decisione: <%= (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("a") ?  "APPROVATA" : (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("n")) ? "NON PROCEDO" : "RIGETTATA" ) %><br>
                Messaggio: <%= s.getSegnalazioneRisposta().getMessage()%><br>
                <%= (s.getSegnalazioneRisposta().isRestituireSoldi() ?  "Rimborso avviato<br>" : "") %> 
                <%= (s.getSegnalazioneRisposta().isValutazioneNegativaVenditore() ?  "Al venditore è stato dato un feedback negativo." : "") %> 
                
            </li>

            <% } else { %>
            <li>
                <h5>L'amministratore non ha ancora risposto alla segnalazione.</h5>
            </li>
            <% }%>
            <% }%>

        </ul>
    </body>
</html>