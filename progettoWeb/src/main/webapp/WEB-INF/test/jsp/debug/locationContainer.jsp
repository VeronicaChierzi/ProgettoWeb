<%-- Pagina di debug (mapping da rimuovere prima della consegna) --%>
<%-- Permette di visualizzare la lista delle location(regioni, città, comuni) --%>

<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.entities.locations.Provincia"%>
<%@page import="it.unitn.disi.entities.locations.LocationContainer" %>
<%@page import="it.unitn.disi.entities.locations.Regione"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Utils.header%>"/>

<h1>LocationContainer</h1>
<div>
	<div>
		<% LocationContainer locationContainer = (LocationContainer) Model.Application.getAttribute(config, Model.Application.locationContainer); %>
		<% if (locationContainer != null) {%>
		LocationContainer caricato: <%=locationContainer%><br/>
		<% } else { %>
		LocationContainer non caricato<br/>
		<% } %>
	</div>

	<div>
		<h2>Regioni</h2>
		<table>
			<% if (locationContainer != null) {%>
			<tr>
				<th>id</th>
				<th>name</th>
			</tr>
			<% for (Regione r : locationContainer.getRegioni()) {%>
			<tr>
				<td><%=r.getId()%></td>
				<td><%=r.getName()%></td>
			</tr>
			<% } %>
			<% } %>
		</table>
	</div>

	<br/><br/><br/>

	<div>
		<h2>Province</h2>
		<table>
			<% if (locationContainer != null) {%>
			<% for (Regione r : locationContainer.getRegioni()) {%>
			<tr>
				<td><h3><%=r.getName()%></h3></td>
			</tr>
			<tr>
				<th>id</th>
				<th>name</th>
			</tr>
			<% for (Provincia p : r.getProvince()) {%>
			<tr>
				<td><%=p.getId()%></td>
				<td><%=p.getName()%></td>
			</tr>
			<% } %>
			<% } %>
			<% }%>
		</table>
	</div>
</div>

<jsp:include page="<%=MyPaths.Private.Jsp.Utils.footer%>"/>