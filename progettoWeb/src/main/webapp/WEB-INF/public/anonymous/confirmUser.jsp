<%-- Pagina che visualizzera il messagio di avvenuta conferma della registrazione --%>
<%-- Il link nella email mander� alla servlet, che confermer� l'utente e che rimander� a questa pagina --%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Private.Jsp.Anonymous.confirmUser%>"/>