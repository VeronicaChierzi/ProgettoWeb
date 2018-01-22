<%-- Scheda dettagliata di un proprio negozio(punto vendita) --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetMyShop%>"/>
<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.myShop); %>
