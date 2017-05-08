<%-- 
    Document   : userAssoc
    Created on : Mar 6, 2017, 5:46:48 PM
    Author     : Dev
--%>

<%@page import="dbUtils.MakeTags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@page language="java" import="dbUtils.DbConn" %>
<%@page language="java" import="model.comments.*" %>
<%@page language="java" import="View.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Comment</title>
        <link href="index.css" rel="stylesheet" type="text/css" />
        <style>
        </style>
    </head>
    <body>
        <%
            String userTagName = "role";
            String userTagFirstOption = "<option value='0'>Select User</option>";
            String userSelectTag = "";
            String userTagSelectSql = "select user_id, user_screen_name from sp17_3308_tuf37373.`user_table` order by user_email";

            String teamTagName = "team_id";
            String teamTagFirstOption = "<option value='0'>Select Team</option>";
            String teamSelectTag = "";
            String teamTagSelectSql = "select team_id, team_abbrv from sp17_3308_tuf37373.`teams_table` order by team_abbrv";

            // Declare all Strings and objects as they should be if it is first rendering
            StringData inputData = new StringData(); // all properties empty string, good for first rendering
            StringData errorMsgs = new StringData(); // all properties empty string, good for first rendering
            DbConn dbc = new DbConn();
            String msg = "";
            
            msg = dbc.getErr();

            if (msg.length() > 0) {
                msg = "Error in connecting to database";
            }

            if (request.getParameter("commentInput") != null) { // this is postback

                // package up Customer String data
                inputData.comment = request.getParameter("commentInput");
                inputData.comment_date = request.getParameter("commentDateInput");
                inputData.user_id = request.getParameter(userTagName);
                inputData.team_id = request.getParameter(teamTagName);
                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();
                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    errorMsgs = DbMods.insert(inputData, dbc); // errorMsgs will hold all validation messags
                    if (errorMsgs.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Record successfully inserted !";
                    } else {
                        if (inputData.user_id.equals("0")) {
                            errorMsgs.user_id = "Please select a user";
                        }
                        if (inputData.team_id.equals("0")) {
                            errorMsgs.team_id = "Please select a Team";
                        }
                        errorMsgs.errorMsg = "ERROR! Could not insert record. Something went wrong.";
                    }
                } // if db connection is good
                // no database connection leaks !
            } // postback
            userSelectTag = MakeTags.makeSelectTag(dbc, userTagName, userTagSelectSql, userTagFirstOption, inputData.user_id);
            teamSelectTag = MakeTags.makeSelectTag(dbc, teamTagName, teamTagSelectSql, teamTagFirstOption, inputData.team_id);

            dbc.close();

        %>
        <div id ="container">
            <jsp:include page="headToContent.jsp" />

            <div id="content">
                <h3><a href="assoc.jsp">List All</a></h3>

                <form action ="insertAssoc.jsp" method="get">
                    <table>
                        <tr>
                            <td>Select User</td>
                            <td><%=userSelectTag%></td>
                            <td class="error"><%=errorMsgs.user_id%></td>
                        </tr> 
                        <tr>
                            <td>Select Team</td>
                            <td><%=teamSelectTag%></td>
                            <td class="error"><%=errorMsgs.team_id%></td>
                        </tr>
                        <tr>
                            <td>Comment</td>
                            <td><input type="text"  name="commentInput" value="<%=inputData.comment%>"/></td>
                            <td class="error"><%=errorMsgs.comment%></td> 
                        </tr>
                        <tr>
                            <td>Comment Date</td>
                            <td><input type="text"  name="commentDateInput" value="<%=inputData.comment_date%>"/></td>
                            <td class="error"><%=errorMsgs.comment_date%></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="text-align:center;"><input type="submit" value="Submit"/></td>
                            <td class="error"></td>
                        </tr>
                    </table>
                </form>
                <br/>
                <strong><%=errorMsgs.errorMsg%></strong> 
                <strong><%=msg%></strong> 
            </div>
        </div> <!-- container -->
    </body>
</html>
