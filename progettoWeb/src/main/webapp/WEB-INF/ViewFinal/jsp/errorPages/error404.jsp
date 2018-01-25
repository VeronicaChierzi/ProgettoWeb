<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
		 <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>404 Not Found</title>
    </head>
     <body class="sfondo">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
			
			<li class="container">
				<h1>Ops.. Qui non c'&egrave; niente!</h1>
			</li>
		</ul>
			
		<%--CANCELLARE QUESTA PARTE--%>
        <h1>404 Not Found</h1>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>"/>
    </body>
</html>