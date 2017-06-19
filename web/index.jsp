<%-- 
    Document   : index
    Created on : Jan 31, 2017, 1:21:43 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Soccer Tribune</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div  id="container">
            <jsp:include page="headToContent.jsp" />

            <div id="content">
                <p>
                    Visit Soccer tribune to get the latest updates about your team from the supporter’s 
                    point of view. Discuss things with them such as team form, transfer updates, player 
                    forms, news, fixtures etc. Share your thoughts about your team or any of the teams you follow
                    on this blog.

                </p>
                <p>
                    Also visit <a id="paraLink" href="https://www.premierleague.com/tables">premierleague.com</a> to keep up with the
                    latest news straight from the source.
                </p>
                <img src="pics/Premier_League_Logo.png" alt=""/>
            </div>
            <jsp:include page="bottom.jsp" />
        </div> <!-- container -->
    </body>
</html>


