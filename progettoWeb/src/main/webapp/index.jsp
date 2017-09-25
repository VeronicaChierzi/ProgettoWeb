<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
		<jsp:include page="/navbar.jsp" />
        <h1>Hello World!</h1>
        <%
            /*Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/prova","postgres", "marco");
            PreparedStatement p = connection.prepareStatement("SELECT * FROM test");
            ResultSet rs = p.executeQuery();
            
            connection.close();*/
        
        %>
        
        <h5></h5>
    </body>
</html>
