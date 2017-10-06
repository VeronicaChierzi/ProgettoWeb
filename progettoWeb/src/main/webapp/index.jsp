<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body > <!--da sistemare con cicli per cambio immagini-->
        <div class="sfondoHome">
		<jsp:include page="/navbar.jsp" />
                <br><br><br>
        <div>
            <div class="centerImg resizeImgHome">
                <img class="centerImg resizeImgHome" src="/progettoWeb/img/logo/logoFinale.png">
            </div>
            <br>
           
            <div class="header-content">
                <div class="header-content-inner">
                    <form action="/progettoWeb/ProductListServlet" method="post">
                    <div class="input-group resizeSearch">
                        <input id="testoRicerca" type="text" class="form-control" placeholder="Cerca..." />
                        <span class="input-group-btn">
                      		<button class="btn btn-default" type="button">
                      			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        </button>
                        </span>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        
        
        
        <%
            /*Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/prova","postgres", "marco");
            PreparedStatement p = connection.prepareStatement("SELECT * FROM test");
            ResultSet rs = p.executeQuery();
            
            connection.close();*/
        
        %>
        
        <h5></h5>
        </div>
    </body>
</html>
