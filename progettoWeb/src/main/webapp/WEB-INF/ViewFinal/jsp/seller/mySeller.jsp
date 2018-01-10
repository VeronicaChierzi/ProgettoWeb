<%-- Scheda del proprio account da venditore --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetMySeller%>"/>
<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.mySeller); %>
