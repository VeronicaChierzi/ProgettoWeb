<%-- Lista delle segnalazioni sporte da tutti gli utenti --%>
<%-- Permette di scegliere una segnalazione che verrà poi gestita nella pagina segnalazione.jsp --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.adminGetSegnalazioni%>"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Segnalazione[] segnalazioni = (Segnalazione[]) Model.Request.getAttribute(request, Model.Request.segnalazioniAdmin);%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tutte le segnalazioni</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

            <h1 class="paddingTop">Tutte le segnalazioni</h1>

            <% if (segnalazioni != null) {%>
            <% for (Segnalazione s : segnalazioni) {%>

            <li>
                <%=(s.isOpen() ? "APERTA" : "CHIUSA")%>
            </li>
            <li>
                <h5><<%=s.getTitle()%></h5>
            </li>
            <li>
                <h5><%=s.getDescription()%></h5>
            </li>

            <% if (s.getSegnalazioneRisposta() != null) {%>

            <button class="btn btn-primary" onclick="window.location.href = '<%=MyPaths.Jsp.adminSegnalazione + "?id=" + s.getId()%>'">Gestisci questa segnalazione</button>
            <% } else {%>
            <button class="btn btn-primary" onclick="window.location.href = '<%=MyPaths.Jsp.adminSegnalazione + "?id=" + s.getId()%>'">Vedi dettaglio</button>
            <% } %>

            <% }%>
            <% }%>


        </ul>
    </body>
</html>

