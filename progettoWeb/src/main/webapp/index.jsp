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

    <body class="sfondoHome"> <!--da sistemare con cicli per cambio immagini-->
        <ul id="paginazione">
            <li>
                <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <div class="centerImg resizeImgHome">
                    <img class="centerImg resizeImgHome" src="/progettoWeb/img/logo/logoFinale.png">
                </div>
                <br/>
            </li>
            <li>
                <form action="/progettoWeb/ProductListServlet" method="get">
                    <div class="input-group resizeSearch">
                        <input id="testoRicerca" name="textSearch" type="text" class="form-control" placeholder="Cerca..." />                                
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </button>
                        </span>
                    </div>

                </form>
            </li>
        </ul>


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

    <!--<script src="http://code.jquery.com/jquery-2.2.3.js" integrity="sha256-laXWtGydpwqJ8JA+X9x2miwmaiKhn8tVmOVEigRNtP4=" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js" integrity="sha256-DI6NdAhhFRnO2k51mumYeDShet3I8AKCQf/tf7ARNhI=" crossorigin="anonymous"></script>
    <script src="js/autocompletamento.js"></script>-->
    <script src="/progettoWeb/js/autocompletamento.js"></script>
</body>
</html>
