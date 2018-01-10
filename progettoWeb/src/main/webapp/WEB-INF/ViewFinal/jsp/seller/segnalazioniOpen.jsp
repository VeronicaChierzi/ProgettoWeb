<%-- Lista delle segnalazioni aperte sporte verso il proprio negozio --%>
<%-- Permette di scegliere una segnalazione per visualizzarla nella pagina segnalazione.jsp --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazioniOpen%>"/>
<% Segnalazione[] segnalazioni = (Segnalazione[]) Model.Request.getAttribute(request, Model.Request.segnalazioniOpenSeller); %>
