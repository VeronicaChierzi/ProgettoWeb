<%-- 
    Document   : testTrasformaIndirizzoInCoordinate
    Created on : Oct 12, 2017, 4:10:07 PM
    Author     : root
--%>

<%@page import="it.unitn.disi.utils.CoordinateUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyCrBfS6gq4DRjdnLI8fmMUQNvKEIp8hegg
        
        <br>
        <br>
        <br>
        
        <%=CoordinateUtil.addressToCoordinates("via Roma 18,Bolzano")[0]%>
        <br>
        <%=CoordinateUtil.addressToCoordinates("via Roma 18,Bolzano")[1]%>
        
        
        
    </body>
</html>
