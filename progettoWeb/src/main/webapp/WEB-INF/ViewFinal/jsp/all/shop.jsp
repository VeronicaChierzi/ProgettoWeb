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
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>I miei punti vendita</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <div class="container">	
                    <h1 class="paddingTop"><%= s.getUserSeller().getName()%></h1>
                    <h5>di <%= u.getLastName()%> <%= u.getFirstName()%></h5>
                    <span class="meta"><%= s.getAddress()%>, <%= s.getComune().getName()%></span>
                    <br><br>

                    <iframe
                        width="600"
                        height="450"
                        frameborder="0" style="border:0"
                        src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBfUs-GBHcPOMgY39DYXfuqWo0b3vNTNzo
                        &q=<%= s.getAddress()%>, <%= s.getComune().getName()%>" allowfullscreen>
                    </iframe>



                </div>
            </li>
        </ul>

    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>