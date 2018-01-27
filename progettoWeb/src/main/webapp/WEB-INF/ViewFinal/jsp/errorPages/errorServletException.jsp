<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>

<!DOCTYPE html>
<html>
    <head>
		 <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error ServletException</title>
    </head>
    <body class="sfondo">
        <ul id="paginazione">
            <li>
				<jsp:include page="<%=MyPaths.Jsp._utilsNavbar%>"/>
            </li>
			
			<li class="container">
				<h1>Error ServletException</h1>
				<p>
					Si è verificato un errore.
				</p>
				<p>
					Torna alla <a href="<%=MyPaths.Jsp.allHome%>">home</a>
				</p>
			</li>
		</ul>
			
		<%--
		<!--CANCELLARE QUESTA PARTE-->
        <h1>Error ServletException</h1>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>"/>
		--%>
    </body>
</html>
