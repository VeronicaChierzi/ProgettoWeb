<%@page session="true" %>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User" %>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page import="it.unitn.disi.utils.Model"%>
<% User user = (User) Model.Session.getAttribute(request, Model.Session.user);%>

<div>
    <nav class="navbar navbar-transparent navbar-static-top navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="<%=MyPaths.Jsp.allHome%>"><img src="/progettoWeb/img/logo/logoPrivacySenzaBordo.png" alt="Brand KSMR" class="img-responsive2"></a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <% if (user == null) {%>
						<li><a href="<%=MyPaths.Jsp.allCart%>">Carrello</a></li>
						<% if (request.getRequestURI().equalsIgnoreCase(MyPaths.Jsp.anonymousLogin)) {%>
							<li><a href="<%=MyPaths.Jsp.anonymousRegistration%>"><span class="glyphicon glyphicon-user"></span> Registrati </a></li>
						<% } else if (request.getRequestURI().equalsIgnoreCase(MyPaths.Jsp.anonymousRegistration) || request.getRequestURI().equalsIgnoreCase(MyPaths.Jsp.anonymousRegistrationSeller)) {%>
							<li><a href="<%=MyPaths.Jsp.anonymousLogin%>"><span class="glyphicon glyphicon-log-in"></span> Accedi </a></li>
						<% } else {%>
							<li><a href="<%=MyPaths.Jsp.anonymousRegistration%>"><span class="glyphicon glyphicon-user"></span> Registrati </a></li>
							<li><a href="<%=MyPaths.Jsp.anonymousLogin%>"><span class="glyphicon glyphicon-log-in"></span> Accedi </a></li>
						<% } %>
                    <% } else { %>
						<li><a href="<%=MyPaths.Jsp.allCart%>">Carrello</a></li>
						<li><a href="<%=MyPaths.Jsp.userOrders%>">Ordini</a></li>
						<li><a href="<%=MyPaths.Jsp.userProfile%>"><span class="glyphicon glyphicon-user"></span> <%=user.getFirstName()%> </a></li>
						<% if (user.isSeller()) { %>
							<li><a href="<%=MyPaths.Jsp.sellerMySeller%>"><span class="glyphicon glyphicon-lock"></span> <%=user.getUserSeller().getName()%> </a></li>
						<% } %>
						<%if (user.isAdmin()) { %>
							<li><a href="/progettoWeb/user/notificheAdmin.jsp"><span class="glyphicon glyphicon-comment"></span></a></li>
						<% } %>
						<li><a href="<%=MyPaths.Servlet.Pubbliche.logout%>"><span class="glyphicon glyphicon-log-out"></span> Esci </a></li>
                    <%}%>
                </ul>
            </div>
        </div>
    </nav>
</div>
