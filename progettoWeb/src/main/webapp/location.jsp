<%@page import="it.unitn.disi.entities.locations.Provincia"%>
<%@page import="it.unitn.disi.entities.locations.LocationContainer" %>
<%@page import="it.unitn.disi.entities.locations.Regione"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Localita'</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
        <h1>Location</h1>

		<br/>
		location è una variabile di scope application<br/>
		l'oggetto viene letto solo la prima volta dal database, poi resta in ram<br/>
		<br/>

		chiama servlet<br/>
		<jsp:include page="/LocationServlet" />
		ricomincia jsp<br/>
		
		<%= session.getAttribute("locationMessage") %>
		<% session.removeAttribute("locationMessage"); %>
		<br/><br/>
		
		<% LocationContainer location = (LocationContainer) application.getAttribute("location"); %>
		<%=location %><br/>
		<%=location.getRegione(1) %><br/>
		<%=location.getRegione(1).getName() %><br/>
		
		<h2>Regioni</h2>
		<table>
		<%
			for (Regione r : location.getRegioni()) {
				out.print("<tr>");
				out.print("<td>id: " + r.getId() + "</td>");
				out.print("<td>name: " + r.getName() + "</td>");
				out.print("</tr>");
			}
		%>
		</table>
		<br/><br/><br/>
		<h2>Province</h2>
		<table>
		<%
			for (Regione r : location.getRegioni()) {
				out.print("<tr>");
				out.print("<td><h4>" + r.getName() + "</h4></td>");
				out.print("</tr>");
				for (Provincia p : r.getProvince()) {
					out.print("<tr>");
					out.print("<td>id: " + p.getId() + "</td>");
					out.print("<td>name: " + p.getName() + "</td>");
					out.print("</tr>");
				}
			}
		%>
		</table>
		
		<!--
		<table>
			<c:forEach items="${applicationScope['attributeNames'].regioni}" var="r">
				<c:out value="r.name"/>
			</c:forEach>
		</table>
		-->
            </li>  
        </ul>
    </body>
</html>
