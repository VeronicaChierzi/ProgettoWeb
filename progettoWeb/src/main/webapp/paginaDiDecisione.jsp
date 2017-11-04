<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina decisione</title>
    </head>
   <body class="sfondo">
        <ul id="paginazione">
            <li>
                <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
        
        
        <h1>Hello World!</h1>
		<a href='/progettoWeb/index.jsp'>continua lo shopping</a><br/>
		<a href='/progettoWeb/cart.jsp'>carrello</a>
            </li>
        </ul>
    </body>
</html>
