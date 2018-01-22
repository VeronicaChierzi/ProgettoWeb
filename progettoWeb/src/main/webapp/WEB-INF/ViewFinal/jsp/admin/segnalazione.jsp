<%-- Scheda dettagliata di una segnalazione --%>
<%-- Permette all'admin di gestire/rispondere alla segnalazione --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.adminGetSegnalazione%>"/>
<% Segnalazione s = (Segnalazione) Model.Request.getAttribute(request, Model.Request.segnalazioneAdmin); %>
