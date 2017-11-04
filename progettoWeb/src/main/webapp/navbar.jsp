<%@page session="true" %>
<%@page import="it.unitn.disi.entities.User" %>
<% User user = (User) session.getAttribute("user"); %>

<div>

    <nav class="navbar navbar-transparent navbar-static-top navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/progettoWeb/index.jsp"><img src="/progettoWeb/img/logo/logoPrivacySenzaBordo.png" alt="Brand KSMR" class="img-responsive2"></a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <% if (user == null) {%>
                    <% if (request.getRequestURI().equalsIgnoreCase("/progettoweb/login.jsp")) {%>
                    <li><a href="/progettoWeb/register.jsp"><span class="glyphicon glyphicon-user"></span> Registrati </a></li>
                        <% } else if (request.getRequestURI().equalsIgnoreCase("/progettoweb/register.jsp") || request.getRequestURI().equalsIgnoreCase("/progettoweb/registerseller.jsp")) {%>
                    <li><a href="/progettoWeb/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Accedi </a></li>
                        <% } else {%>
                    <li><a href="/progettoWeb/register.jsp"><span class="glyphicon glyphicon-user"></span> Registrati </a></li>
                    <li><a href="/progettoWeb/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Accedi </a></li>
                        <%}%>
                        <% } else {%>
                    <li><a href="/progettoWeb/user/ordini.jsp">ordini</a></li>
                    <li><a href="/progettoWeb/user/profilo.jsp"><span class="glyphicon glyphicon-user"></span> <%=user.getFirstName()%> </a></li>
                        <% if (user.isSeller()) {%>
                    <li><a href="/progettoWeb/negozio.jsp"><span class="glyphicon glyphicon-lock"></span> <%=user.getUserSeller().getName()%> </a></li>
                        <%}%>
                        <%if (user.isAdmin()) {%>
                    <li><a href="/progettoWeb/user/notificheAdmin.jsp"><span class="glyphicon glyphicon-comment"></span></a></li>
                            <%}%>
                    <li><a href="/progettoWeb/LogoutServlet"><span class="glyphicon glyphicon-log-out"></span> Esci </a></li>
                        <%}%>
                </ul>
            </div>
        </div>

    </nav>
</div>