<%-- 
    Document   : other
    Created on : Feb 9, 2017, 2:40:16 PM
    Author     : Dev
--%>
<%@page import="model.teams.DbMods"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@page language="java" import="View.TeamView" %>
<%@page language="java" import="dbUtils.DbConn" %>

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <title>List of All Teams</title>
        <style>
            body {
                font-family: verdana, sans-serif;
                background-color: #A6DBFF;
            }
            .resultSet {
                background-color: #888888; /* dark grey shows thru between th and td */
                margin: auto; /* center table within its container */
            }
            .resultSet th {
                background-color: #608890;  /* dark green/blue */
                color: white;
                font-weight: bold;
                padding: 4px 6px;  /* top/bottom then left/right */
            }
            .resultSet td {
                background-color: #F0F8FF; /* light green/blue */
                padding: 2px 6px;  /* top/bottom then left/right */
            }
            h1, h2 {
                margin-top:78px;
                text-align:center;
            }
            #titleNav {
                left:0px;
                top:0px;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <jsp:include page="headToContent.jsp"/>
            <%  
                
                
                String deleteMsg = "";
                String team_id = request.getParameter("team_id");
                
                
                DbConn dbc = new DbConn();
                String msg = dbc.getErr(); // returns "" if connection is good, else error msg.
                if (msg.length() == 0) { // got open connection
                     if (team_id != null && team_id.length() != 0 && msg.length() == 0) {
                        deleteMsg = DbMods.Delete(team_id, dbc);
                    }
                    // returns a string that contains a HTML table with the db data in it
                    // pass in the name of the CSS style that you want applied to the HTML 
                    // table ("dependency injection") and pass in an open DbConn object.
                    msg = TeamView.listWithUpdateAndDelete("resultSet", dbc);
                } else{
                    msg = "Error in connecting to database";
                }
                
                // PREVENT DB CONNECTION LEAKS (every code path that opens a db connection 
                // also must close it).
                dbc.close();
            %>
            <h2 style="text-align:center; margin-top: 10%;"><a href="insertOther.jsp">Insert Team</a></h2>
            <h2>Team List</h2>
            <% out.print(msg);%>
            <br/><br/>
            <%out.print(deleteMsg);%>
            <jsp:include page="bottom.jsp"/>
        </div> <!-- container -->
    </body>
</html>