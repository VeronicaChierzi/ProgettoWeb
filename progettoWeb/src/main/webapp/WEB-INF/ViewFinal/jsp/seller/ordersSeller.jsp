<%-- Lista degli ordini ricevuti dal venditore (tutti i negozi) --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetOrdersSeller%>"/>
<% Order[] orders = (Order[]) Model.Request.getAttribute(request, Model.Request.ordersSeller); %>
