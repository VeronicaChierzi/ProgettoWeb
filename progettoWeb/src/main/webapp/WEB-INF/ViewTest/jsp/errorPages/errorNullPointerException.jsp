<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Errore Null Pointer Exception</title>
    </head>
    <body>
        <h1>Errore Null Pointer Exception</h1>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>" />
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>