<%-- 
    Document   : deny
    Created on : Feb 19, 2017, 8:06:16 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="index.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div  id="container">
        <jsp:include page="headToContent.jsp" />
        <h1 style="padding-top: 100px;">You are not logged in hence you cannot view the page.</h1>
        <jsp:include page="bottom.jsp" />
        </div>
    </body>
</html>
