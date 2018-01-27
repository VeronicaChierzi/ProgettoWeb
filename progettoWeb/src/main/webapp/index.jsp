<!-- Reindirizza immediatamente alla home nel caso in cui un utente scriva semplicemente "/progettoWeb/" invece di "/progettoWeb/Home" -->
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%
    String redirectURL = MyPaths.Jsp.allHome;
    response.sendRedirect(redirectURL);
%>