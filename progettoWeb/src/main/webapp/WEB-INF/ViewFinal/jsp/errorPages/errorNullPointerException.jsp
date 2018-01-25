<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<!DOCTYPE html>
<html>
    <head>
		 <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Errore Null Pointer Exception</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
			
			<li class="container">
				   <h1>Errore Null Pointer Exception</h1>
			</li>
		</ul>
			
				<%--CANCELLARE QUESTA PARTE--%>
        <h1>Errore Null Pointer Exception</h1>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>"/>
    </body>
</html>
