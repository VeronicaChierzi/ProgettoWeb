<%-- Scheda dettagliata di un ordine ricevuto --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetOrder%>"/>
<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.orderSeller); %>
