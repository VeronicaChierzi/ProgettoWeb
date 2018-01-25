<%-- Lista delle segnalazioni sporte verso il proprio negozio --%>
<%-- Permette di scegliere una segnalazione per visualizzarla nella pagina segnalazione.jsp --%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Servlet.Privatee.sellerGetSegnalazioni%>"/>
<% Segnalazione[] segnalazioni = (Segnalazione[]) Model.Request.getAttribute(request, Model.Request.segnalazioniSeller);%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Segnalazioni ricevute</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
			<div class="container">
				<h1 class="paddingTop">Segnalazioni ricevute</h1>
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
						<h5><a href="<%=MyPaths.Jsp.sellerSegnalazione + "?id=" + s.getId()%>"><%=s.getTitle()%></a></h5>
					</li>
					<li>
						<h5><%=s.getDescription()%></h5>
					</li>
</div>
					<% }%>
					<% }%>
				</div>
			</div>
        </ul>
    </body>
</html>