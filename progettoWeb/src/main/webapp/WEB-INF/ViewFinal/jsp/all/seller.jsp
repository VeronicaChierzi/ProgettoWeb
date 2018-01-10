<%-- Scheda pubblic di un venditore --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetSeller%>"/>
<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.seller); %>
