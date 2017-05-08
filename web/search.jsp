<%-- 
    Document   : search
    Created on : Feb 21, 2017, 2:16:37 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page language="java" import="dbUtils.*" %>
<%@page language="java" import="View.*" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Search</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-color: #A6DBFF;
                font-family: verdana, sans-serif;
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
                text-align:center;
                margin-top:100px;
            }
            #titleNav{
                top:0px;
                left:0px;
            }
            #container{
                margin-top: -100px;
            }

        </style>
    </head>

    <%

        // Strings to specify how to make the email select tag 
        String screenNameSelectTagName = "email";
        String teamSelectTagName = "team";

        String screenNameSelectSql = "select user_id, user_screen_name from sp17_3308_tuf37373.`user_table` order by user_email";
        String teamSelectSql = "select team_id, team_abbrv from sp17_3308_tuf37373.`teams_table` order by team_abbrv";

        String screenNameFirstOption = "<option value='0'>Select User</option>";
        String teamFirstOption = "<option value='0'>Select Team</option>";

        String Results = "";

        // This string will hold the generated email select tag
        String screenNameSelectTag = ""; // empty string unless we can get a good db connection.
        String teamSelectTag = "";

        // This string will hold the value picked by the user  
        String userSelectedValue = "";
        String commentDate = "";
        String teamSelectedValue = "";

        DbConn dbc = new DbConn();
        String msg = dbc.getErr();
        if (msg.length() == 0) {

            // Postback - persist user entered values
            if (request.getParameter("Date") != null) {
                commentDate = request.getParameter("Date");
                userSelectedValue = request.getParameter(screenNameSelectTagName);
                teamSelectedValue = request.getParameter(teamSelectTagName);

                Results = CommentsView.search(dbc, userSelectedValue, teamSelectedValue, commentDate, "resultSet");

            }

            // This will be the select tag for first rendering (nothing pre-selected) 
            screenNameSelectTag = MakeTags.makeSelectTag(dbc, screenNameSelectTagName, screenNameSelectSql,
                    screenNameFirstOption, userSelectedValue);
            teamSelectTag = MakeTags.makeSelectTag(dbc, teamSelectTagName, teamSelectSql,
                    teamFirstOption, teamSelectedValue);
        } else {
            msg = "Error in connecting to database";
        }
        dbc.close();
    %>
    <body>
        <div  id="container">
            <jsp:include page="headToContent.jsp" />
            <div id="content">
                <h2>Search</h2>
                <form action="search.jsp" method="get">
                    <h3>Show comments from dates.</h3>
                    Enter Date in YYYY-MM-DD format:
                    <input name="Date" value="<%=commentDate%>"/> 
                    <br/><br/>
                    Show users
                    <%=screenNameSelectTag%>
                    <br/><br/>
                    Show Teams
                    <%=teamSelectTag%>
                    <br/><br/>
                    <input type="submit" value="Submit"/>
                </form>
                <%=Results%>
            </div>
            <br/><br/>
            <%=msg%>
            <jsp:include page = "bottom.jsp"/>
        </div>
    </body>
</html>