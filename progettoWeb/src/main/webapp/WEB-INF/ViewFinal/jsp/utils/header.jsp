<%@page import="it.unitn.disi.utils.MyPaths"%>
<div>
	<li>
		<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
	</li>
	<%--<% if (!request.getRequestURI().equalsIgnoreCase(MyPaths.Jsp.allHome)) {%>--%>
	<li>
		<jsp:include page = "<%=MyPaths.Jsp._utilsSearchBar%>"/>
	</li>
	<%--<% } %>--%>
</div>