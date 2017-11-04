<%@page import="it.unitn.disi.MyPaths"%>
<%@page import="it.unitn.disi.entities.Segnalazione"%>

asd
<br/>


<% Segnalazione[] segnalazioni = (Segnalazione[]) request.getAttribute("segnalazioni");%>
<%--<%=segnalazioni%>--%>
<br/>

<% for (Segnalazione s : segnalazioni) {%>
	<%=s%>
<% }%>

<br/>
qwe
<br/>

<jsp:include page="<%=MyPaths.Utils.navbar %>" />
<%--<jsp:include page="<%=MyPaths.All.home %>" />--%>

