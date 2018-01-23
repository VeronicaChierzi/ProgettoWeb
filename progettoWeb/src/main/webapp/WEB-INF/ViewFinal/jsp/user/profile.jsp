<%-- Scheda del profilo dell'utente con informazioni personali --%>
<%@page session="true" %>
<%@page import="it.unitn.disi.entities.UserAdmin"%>
<%@page import="it.unitn.disi.entities.UserSeller"%>
<%@page import="it.unitn.disi.entities.User"%>
<%@page import="it.unitn.disi.utils.Model"%>
<%@page import="it.unitn.disi.utils.MyPaths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User user = Model.Session.getUserLogged(request); %>

<html>
    <head>
        <jsp:include page="/navbarHead.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profilo Utente</title>
    </head>
    <body class="sfondo"> <!--da sistemare con cicli per cambio immagini-->
        <ul id="paginazione">
			<jsp:include page="<%=MyPaths.Jsp._utilsHeader%>"/>
            <li>
                <ul id="profilo">
                    <li style="margin-top: 15px;">
                        <% if (user.isAdmin()) {%>
							<img src="/progettoWeb/img/user/userAdmin.png" width="100px" height="100px" />
                        <%}else{%>
							<img src="/progettoWeb/img/user/user.png" width="100px" height="100px"> 
                        <%}%>
                    </li>
                </ul>
            </li>
            <li>
				<h1>Profilo utente</h1>

				id: <%=user.getId()%><br/>
				username: <%=user.getUsername()%><br/>
				email: <%=user.getEmail()%><br/>
				password: <%=user.getPassword()%><br/>
				first name: <%=user.getFirstName()%><br/>
				last name: <%=user.getLastName()%><br/>
				hash: <%=user.getHash()%><br/>
				verificato: <%=user.isVerificato()%><br/>
				<br/>

				<% if (user.isSeller()) {%>
					<% UserSeller seller = user.getUserSeller(); %>
					Sei il proprietario di <%=seller.getName()%><br/>
					<a href="<%=MyPaths.Jsp.sellerMySeller%>">Gestisci negozi (<%=seller.getName()%>)</a><br/>
					<br/>
				<% } else { %>
					<a href="<%=MyPaths.Jsp.userRegistrationSellerLog%>">Registrati come venditore</a><br/>
					<br/>
				<% } %>

				<% if (user.isAdmin()) {%>
					Sei un amministratore<br/>
					<%--
					<% UserAdmin admin = user.getUserAdmin(); %>
					<a href="<%=MyPaths.Jsp.myAdmin%>">Vai alla pagina riservata agli amministratori</a><br/>
					--%>
					<br/>
				<% } %>
				
				<a href="<%=MyPaths.Jsp.userSegnalazioni%>">Lista delle segnalazioni effettuate</a><br/>
				<a href="<%=MyPaths.Jsp.userSegnalazioniOpen%>">Lista delle segnalazioni aperte effettuate</a><br/>
				<br/>
			</li>
		</ul>
	</body>
</html>

<jsp:include page="<%=MyPaths.Jsp._utilsFooter%>"/>