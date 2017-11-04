<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>500</title>
    </head>
    <body>
        <h1>500 Internal Server Error</h1>
		<jsp:include page="/WEB-INF/jsp/error_pages/printException.jsp" />
    </body>
</html>
