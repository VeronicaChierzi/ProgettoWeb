<%-- Scheda del negozio (pubblica, visibile da tutti) --%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.entities.Shop"%>
<%@page import="it.unitn.disi.entities.orders.Order"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.allGetShop%>"/>
<% Shop s = (Shop) Model.Request.getAttribute(request, "shop");%>
<% User u = (User) Model.Request.getAttribute(request, "user");%>
<% int neg = (int) Model.Request.getAttribute(request, "negative");%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Il negozio</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                    <h1 class="paddingTop"><%= s.getUserSeller().getName()%></h1>
                    <h5>di <%= u.getLastName()%> <%= u.getFirstName()%></h5>
                    <span class="meta"><%= s.getAddress()%>, <%= s.getComune().getName()%></span><br>
                    <span class="meta">Questo negozio ha avuto <%=neg%> valutazioni negative.</span>
                    
            </li>
            <li>
                <iframe
                        width="600"
                        height="450"
                        frameborder="0" style="border:0"
                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBfUs-GBHcPOMgY39DYXfuqWo0b3vNTNzo
                        &q=<%= s.getAddress()%>, <%= s.getComune().getName()%>" allowfullscreen>
                    </iframe>
            </li>
            <li>
                <img src="https://maps.googleapis.com/maps/api/streetview?size=600x300&location=<%=s.getLatitude() %>,<%=s.getLongitude()%>&heading=150&pitch=-0.76&key=AIzaSyAdFPcsuR2JaXI_HSDZ0NBJeiA2EuH13tQ" alt="foto del negozio"/>
                    
            </li>
        </ul>

    </body>
</html>