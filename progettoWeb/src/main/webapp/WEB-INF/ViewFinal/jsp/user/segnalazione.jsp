<%-- Scheda dettagliata di una segnalazione sporta dall'utente riguardo ad un ordine effettuato --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneUser); %>
