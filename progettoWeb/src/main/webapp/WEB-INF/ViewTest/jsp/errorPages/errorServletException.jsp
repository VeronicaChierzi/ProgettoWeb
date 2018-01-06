<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error ServletException</title>
    </head>
    <body>
        <h1>Error ServletException</h1>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>" />
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>