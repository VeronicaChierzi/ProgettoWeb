<%-- Scheda dettagliata di una segnalazione --%>
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
                <h3>Risposta</h3>                
                Decisione: <%= (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("a") ? "APPROVATA" : (s.getSegnalazioneRisposta().getDecisione().equalsIgnoreCase("n")) ? "NON PROCEDO" : "RIGETTATA")%><br>
                Messaggio: <%= s.getSegnalazioneRisposta().getMessage()%><br>
                <%= (s.getSegnalazioneRisposta().isRestituireSoldi() ? "Rimborso avviato<br>" : "")%> 
                <%= (s.getSegnalazioneRisposta().isValutazioneNegativaVenditore() ? "Al venditore è stato dato un feedback negativo." : "")%> 

            </li>

            <% } else {%>
            <li>
                <h3>Rispondi alla segnalazione</h3>
                <form action="<%=MyPaths.Servlet.Pubbliche.rispondiSegnalazione%>" method="post">
                    <input type="hidden" name="idSegnalazione" value="<%=s.getId()%>"/>
                    Decisione<br>
                    <fieldset id="decis">
                        <input type="radio" name="decisione" value="a" checked>Approvo   
                        <input type="radio" name="decisione" value="r"> Rigetto   
                        <input type="radio" name="decisione" value="n"> Non procedo   
                    </fieldset>
                    Messaggio<br>
                    <input style="width: 100%; border-style: hidden; margin-bottom: 10px;" type="text" name="message" value=""/>
                    <br>
                    <input type="checkbox" name="rimborso" value="true"> Disponi rimborso   
                    <input type="checkbox" name="negVal" value="true"> Valutazione negativa venditore<br>
                    <button class="btn btn-primary" type="submit"><span class="glyphicon glyphicon-plus-sign"></span>  Apri segnalazione</button>
                </form>
            </li>
            <% }%>
            <% }%>

        </ul>
    </body>
</html>