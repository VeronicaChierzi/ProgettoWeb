<%-- Scheda dettagliata di una segnalazione --%>
<%-- Permette al venditore di visionare una segnalazione ricevuta --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneSeller); %>
