<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>

<!DOCTYPE html>
<html>
    <head>
		 <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 405</title>
    </head>
     <body class="sfondo">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
			
			<li class="container">
				<h1>Error 405: Method not allowed</h1>
					Il metodo GET potrebbe non essere accettato da questa pagina<br/>
			</li>
		</ul>
		
		<%--
		<!--CANCELLARE QUESTA PARTE-->
        <h1>Error 405: Method not allowed</h1>
		Il metodo GET potrebbe non essere accettato da questa pagina<br/>
		<br/>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>"/>
		--%>
    </body>
</html>
