<%-- Pagina che visualizzera il messagio di avvenuta conferma della registrazione --%>
<%-- Il link nella email manderà alla servlet, che confermerà l'utente e che rimanderà a questa pagina --%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Anonymous.confirmUser%>"/>