<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error DAOException</title>
    </head>
    <body>
        <h1>Error DAOException</h1>
		<p>C'è stato un errore nell'eseguire una query</p>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>" />
    </body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>