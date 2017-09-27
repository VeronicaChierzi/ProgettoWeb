<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/navbar.jsp" />

        <div>
            <img class="centerImg" src="images/logoFinale.png" height="100" width="100">
            <div class="header-content">
                <div class="header-content-inner">
                    <div class="input-group center-block border">
                        <h1 class="text-center">Registrati</h1>
                        <!--CONTROLLO MAIL E INVIO MESSAGGIO-->
                        <div class="form-group">
                            <label for="emailRegistration">Username (email)*</label>
                            <input type="email" id="emailRegistration" name="email" class="form-control paddingRightInput" placeholder="Email" required>
                        </div>
                        <!--VEDERE COME METTERE TIC VISUALIZZATO QUANDO PASSWORD GIUSTA ENTRAMBI-->
                        <!--METTERE AVVISO PER LUNGHEZZA 8 CARATTERI ALMENO 1 NUMERO-->
                        <div class="form-group">
                            <label for="passwordRegistration">Password* </label>
                            <input type="password" id="passwordRegistration" name="password" class="form-control paddingRightInput" placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <label for="passwordRegistrationRip">Ripeti Password* </label>
                            <input type="password" id="passwordRegistrationRip" name="password2" class="form-control paddingRightInput" placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <label for="nameRegistration">Nome*</label>
                            <input type="email" id="nameRegistration" name="first_name" class="form-control paddingRightInput" placeholder="Nome" required>
                            <div class="form-group">
                                <label for="cognomeRegistration">Cognome*</label>
                                <input type="email" id="cognomeRegistration" name="last_name" class="form-control paddingRightInput" placeholder="Cognome" required>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="text-center checkbox">
                            <label>
                                <input type="checkbox" value="">Accetto l'informaiva sulla
                                <a href="InformativaPrivacy.pdf" target="_blank"> privacy</a>
                            </label>
                        </div>
                        <br>
                        <!--MESSAGGIO PER ACCETTARE LA PRIVACY-->
                        <div class="alert alert-danger text-center" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <span class="sr-only"></span>Accettare l'informativa per la privacy.
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>







    <h1>Hello World!</h1>

    <h1>Register</h1>
    <div>
        <form method="post" action="RegistrationServlet">
            <!-- <input type="hidden" name="op" value="register" /> -->
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value="" required /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" value="" required /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value="" required /></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="first_name" value="" required /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="last_name" value="" required /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form>
        <%
            if (session.getAttribute("registrazioneFallita") != null) {
                session.removeAttribute("registrazioneFallita");
                out.print("Registrazione non riuscita<br>");
            }
        %>
    </div>

</body>
</html>
