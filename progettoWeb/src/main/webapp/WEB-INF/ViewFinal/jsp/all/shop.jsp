<%-- Scheda del negozio (pubblica, visibile da tutti) --%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetShop%>"/>
<% Order o = (Order) Model.Request.getAttribute(request, Model.Request.shop); %>
