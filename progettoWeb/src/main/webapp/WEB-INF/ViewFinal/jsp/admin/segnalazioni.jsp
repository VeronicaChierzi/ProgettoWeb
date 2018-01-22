<%-- Lista delle segnalazioni sporte da tutti gli utenti --%>
<%-- Permette di scegliere una segnalazione che verrà poi gestita nella pagina segnalazione.jsp --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.adminGetSegnalazioni%>"/>
<% Segnalazione[] segnalazioni = (Segnalazione[]) Model.Request.getAttribute(request, Model.Request.segnalazioniAdmin); %>

