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


			<div class="container">
				<h1 class="paddingTop">Tutte le segnalazioni</h1>

				<div id="puzzette" style="margin-left: auto; margin-right: auto; display: table;">

					<% if (segnalazioni != null) {%>
					<% for (Segnalazione s : segnalazioni) {%>

					<div style="border-bottom-color: black; border-bottom-style: solid; border-bottom-width: 1px;">
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
						<h5><<%=s.getTitle()%></h5>
					</li>
					<li>
						<h5><%=s.getDescription()%></h5>
					</li>
					<li style="margin-left: auto; margin-right: auto; display: table;" >
					<% if (s.getSegnalazioneRisposta() != null) {%>
					
					<button style="margin-bottom: 20px;"class="btn btn-primary" onclick="window.location.href = '<%=MyPaths.Jsp.adminSegnalazione + "?id=" + s.getId()%>'">Vedi dettaglio</button>
					<% } else {%>
					<button style="margin-bottom: 20px;" class="btn btn-primary" onclick="window.location.href = '<%=MyPaths.Jsp.adminSegnalazione + "?id=" + s.getId()%>'">Gestisci questa segnalazione</button>
					<% } %>
					</li>
					</div>
					<% }%>
					
					<% }%>

				</div>
			</div>
        </ul>
    </body>
</html>

