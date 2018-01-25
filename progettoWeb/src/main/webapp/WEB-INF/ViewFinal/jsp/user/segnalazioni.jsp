<%-- Lista delle segnalazioni sporte dall'utente riguardo agli ordini effettuati --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.userGetSegnalazioni%>"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Segnalazione[] segnalazioni = (Segnalazione[]) Model.Request.getAttribute(request, Model.Request.segnalazioniUser);%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Segnalazioni</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

            <h1 class="paddingTop">Segnalazioni</h1>

            <% if (Model.Messages.consumeBoolean(request, "erroreAggiuntaSegnalazione")) { %>
            <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove-sign"></span> Impossibile aprire la segnalazione. Contattare l'amministratore del sito.</div>
            <% } %>
            <% if (Model.Messages.consumeBoolean(request, "correttaAggiuntaSegnalazione")) { %>
            <div class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok-sign"></span> Segnalazione aperta correttamente!</div>
            <% } %>

            <% if (segnalazioni != null) {%>
            <% for (Segnalazione s : segnalazioni) {%>
			<div class="container">
				<li>
					<%=(s.isOpen() ? ""
							+ "<div class=\"price\" style=\"color: green; margin-bottom: 4px; \">"
							+ "APERTA"
							+ "</div>" : ""
							+ "<div class=\"price\" style=\"color: red; margin-bottom: 4px;\">"
							+ "CHIUSA"
							+ "</div>")%>
				</li>
				<li>
					<div style="font-size: 20px;">
						<a href="<%=MyPaths.Jsp.userSegnalazione + "?id=" + s.getId()%>"><%=s.getTitle()%></a>
					</div>
				</li>
				<li style="border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: black; margin-bottom: 10px; font-size: 16px; ">
					<div style="margin-bottom: 5px;"><%=s.getDescription()%></div>
				</li>
			</div>

            <% }%>
            <% }%>


        </ul>
    </body>
</html>