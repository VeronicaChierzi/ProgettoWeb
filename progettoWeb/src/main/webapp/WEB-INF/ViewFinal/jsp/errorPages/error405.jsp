<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 405</title>
    </head>
    <body>
        <h1>Error 405: Method not allowed</h1>
		Il metodo GET potrebbe non essere accettato da questa pagina<br/>
		<br/>
		<jsp:include page="<%=MyPaths.Jsp._errorPagesPrintException%>"/>
    </body>
</html>
