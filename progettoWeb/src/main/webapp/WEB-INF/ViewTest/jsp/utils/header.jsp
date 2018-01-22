<%@page import="it.unitn.disi.utils.MyPaths"%>
<div style="border:1px solid black;">
	Questo è l'header. include logo, navbar, barra di ricerca, ecc...<br/>
	<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
</div>