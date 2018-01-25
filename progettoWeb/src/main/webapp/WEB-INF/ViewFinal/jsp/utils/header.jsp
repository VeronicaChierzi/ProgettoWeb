<%@page import="it.unitn.disi.utils.MyPaths"%>
<li>
	<div style="position: initial">
		<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
	</div>
</li>
<%--<% if (!request.getRequestURI().equalsIgnoreCase(MyPaths.Jsp.allHome)) {%>--%>

<li id="barraheader">
	<div style="position: sticky; width: 100%;">
		<jsp:include page = "<%=MyPaths.Jsp._utilsSearchBar%>"/>
	</div>
</li>

<%--<% } %>--%>