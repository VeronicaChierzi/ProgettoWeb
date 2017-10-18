<%-- 
    Document   : ValutazioneTmp
    Created on : 26-set-2017, 13.15.35
    Author     : Marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ValutazioneTemporanea</title>
        <link rel="stylesheet" href="../css/style.css" />
        
    </head>
    <body>
        <h2>Valutazione Prodotto</h2>
        <form action="/ValutazioneTmp.jsp" method="post">
            Titolo<br>
            <input type="text" name="titolo"><br>
            Recensione<br>
            <textarea name="recensione" rows="13" cols="70"></textarea><br>
            
            <div class="stars">
                <input type="radio" name="star" class="star-1" id="star-1" value="1" />
		<label class="star-1" for="star-1">1</label>
		<input type="radio" name="star" class="star-2" id="star-2" value="2"/>
		<label class="star-2" for="star-2">2</label>
		<input type="radio" name="star" class="star-3" id="star-3" value="3"/>
		<label class="star-3" for="star-3">3</label>
		<input type="radio" name="star" class="star-4" id="star-4" value="4"/>
		<label class="star-4" for="star-4">4</label>
		<input type="radio" name="star" class="star-5" id="star-5" value="5"/>
		<label class="star-5" for="star-5">5</label>
		<span></span>
            </div>            
            
            <input type="submit" value="Submit">           
        </form>
        
    </body>
</html>
