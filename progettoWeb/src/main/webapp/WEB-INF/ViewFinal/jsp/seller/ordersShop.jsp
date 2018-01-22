<%-- Lista degli ordini ricevuti dal venditore in un negozio --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetOrdersShop%>"/>
<% Order[] orders = (Order[]) Model.Request.getAttribute(request, Model.Request.ordersShop); %>
