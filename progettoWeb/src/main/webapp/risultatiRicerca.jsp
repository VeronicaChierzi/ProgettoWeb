<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> <!--CHE FARE CON STA PAGINA? CANCELLIAMO!-->
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Risultati Ricerco</title>
    </head>
    <body>
         <ul id="paginazione">
            <li>
        <jsp:include page="/navbar.jsp" />
            </li>
            <li>
                <jsp:include page = "/barraRicerca.jsp"/>
            </li>
            <li>
                <ul id="risRicerca">
                    <li>
                        <jsp:include page="categoryContainer.jsp"/>
                    </li>
                    <li>
                        <jsp:include page="/ProductListServlet"/>
                    </li>
                    
                </ul>
                
                
            </li>
         </ul>
    </body>
</html>
