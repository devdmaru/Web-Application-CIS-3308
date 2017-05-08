<%-- 
    Document   : members
    Created on : Feb 19, 2017, 8:06:59 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <title>Members</title>
    </head>
    <body>
        <div  id="container">
        <jsp:include page="headToContent.jsp" />
        <%
            String msg = "";
            if (session.getAttribute("User_Email") != null) {               
                out.print("<div style='padding-top: 100px;'>This is the Members area</div>");
                out.print("User: "+ session.getAttribute("Name")+ "<br/>");
                out.print("User Id: " + session.getAttribute("User_Id"));

            } else {
                 response.sendRedirect("deny.jsp");
            }

        %>
        <jsp:include page="bottom.jsp" />
        </div> <!-- container -->
    </body>
</html>
