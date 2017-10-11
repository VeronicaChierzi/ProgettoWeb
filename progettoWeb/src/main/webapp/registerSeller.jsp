<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/navbarHead.jsp" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrazione Venditore</title>
    </head>
    <body class="sfondo">

        <ul id="paginazione">
            <li>
                <jsp:include page="/navbar.jsp" />
            </li>

            <li>
                <img class="centerImg resizeImg showLogo" src="/progettoWeb/img/logo/logoFinale.png" height="50" width="50">
                <div class="header-content">
                    <div class="header-content-inner">
                        <div class="input-group center-block border resizeRegistration">
                            <form action="RegistrationSellerServlet" method="POST">
                                <h1 class="text-center">Registrati come Venditore</h1>
                                <br><div><% //if (session.getAttribute("username") != null) {
                                    //out.print((String) session.getAttribute("username"));
                                    //session.removeAttribute("username");
                                    //}%></div>
                                <!--CONTROLLO MAIL E INVIO MESSAGGIO-->
                                <div class="form-group">
                                    <label for="nomeNeg">Nome Negozio*</label>
                                    <input type="text" id="nomeNeg" name="name" class="form-control paddingRightInput" placeholder="Nome Negozio" required>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="partitaIVA">Partita IVA*</label>
                                    <input type="text" id="partitaIVA" name="partita_iva" class="form-control" placeholder="Partita Iva" required>
                                </div>
                                <br><br>
                                <div class="text-center">

                                    <input type="submit" class="btn btn-primary btn-large" value="Registrati"></input>
                                </div>
                            </form>
                            <input type="submit" class="btn btn-large btn-secondary" value="Annulla"></input> 

                            <%
                                if (session.getAttribute("registrazioneVenditoreFallita") != null) {
                                    session.removeAttribute("registrazioneVenditoreFallita");
                                    out.print("Registrazione venditore non riuscita<br>");
                                }
                            %>
                        </div>
                    </div>
                </div>
            </li>
        </ul>



        <!--<h1>Hello World!</h1>

        <h1>Register Seller</h1>
        questa pagina dovrebbe essere visualizzata solo se l'utente è loggato.<br/>
        da sistemare!!!!!!!!!!!!!!!!!!!!!!!!!!!<br/>
        <div>
            <form method="post" action="RegistrationSellerServlet">
        <!-- <input type="hidden" name="op" value="register" />
        <table>
            <tr>
                <td>name:</td>
                <td><input type="text" name="name" value="" required /></td>
            </tr>
            <tr>
                <td>partita iva:</td>
                <td><input type="text" name="partita_iva" value="" required /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register" /></td>
            </tr>
        </table>
    </form>-->


    </body>
</html>
