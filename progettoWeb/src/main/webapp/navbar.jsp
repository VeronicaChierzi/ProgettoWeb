<%@page session="true" %>
<%@page import="it.unitn.disi.entities.User" %>
<% User user = (User) session.getAttribute("user"); %>



<header class="masthead">
    <nav class="navbar navbar-transparent navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="landingPage.html"><img src="img/logo/logoPrivacySenzaBordo.png" alt="Brand KSMR" class="img-responsive2"></a>
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <% if (user == null) {%>
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Registrati </a></li>
                    <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span> Accedi </a></li>
                        <% } else {%>
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> <%=user.getFirstName()%> </a></li>
                        <%}%>
                </ul>
            </div>
        </div>

    </nav>
</header>
                <br><br><br><br><br><br><br>


<!--<ul>
    <li><a href="/progettoWeb/index.jsp">Home</a></li>
    <li><a href="/progettoWeb/carrello.jsp">Carrello</a></li>
        <% //if (user == null) { %>
    <li><a href="/progettoWeb/login.jsp">Login</a></li>
    <li><a href="/progettoWeb/register.jsp">Register</a></li>
        <% //} else {%>
    <li><a href="/progettoWeb/user/profilo.jsp"><%//= user.getUsername()%></a></li>
    <li><a href="/progettoWeb/user/ordini.jsp">ordini</a></li>
    <li><a href="/progettoWeb/LogoutServlet">Logout</a></li>
        <%// }%>
    <li><a href="/progettoWeb/location.jsp">location(test)</a></li>
    <li><a href="/progettoWeb/ProductServlet?id=1">product1(test)</a></li>
    <li><a href="/progettoWeb/product.jsp?id=2">product2(test)</a></li>
</ul>-->