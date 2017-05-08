<%-- 
    Document   : updateOther
    Created on : Mar 18, 2017, 2:12:20 PM
    Author     : Dev
--%>

<%@page import="dbUtils.MakeTags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@page language="java" import="dbUtils.DbConn" %>
<%@page language="java" import="model.teams.*" %>
<%@page language="java" import="View.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Team</title>
        <link href="index.css" rel="stylesheet" type="text/css" />
        <style>
        </style>
    </head>
    <body>
        <%
            String roleTagName = "role";
            String roleTagFirstOption = "<option value='0'>Select Role</option>";
            String roleSelectTag = "";
            String roleTagSelectSql = "select team_id, team_abbrv from teams_table order by team_id";
            String msg = "";
            // Declare all Strings and objects as they should be if it is first rendering
            StringData inputData = new StringData(); // all properties empty string, good for first rendering
            StringData errorMsgs = new StringData(); // all properties empty string, good for first rendering
            DbConn dbc = new DbConn();
            msg = dbc.getErr();
            
            if(msg.length() > 0){
                msg = "Error in connecting to database";
            }

            if (request.getParameter("team_id") != null) { // this is postback

                // package up User String data
                //inputData.roleId = request.getParameter(roleTagName);
                inputData.team_id = request.getParameter("team_id");
                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();
                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    inputData = TeamView.search(dbc, inputData); // errorMsgs will hold all validation messags
                    if (inputData.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Ready for Update.";
                    }
                } // if db connection is good
                //roleSelectTag = MakeTags.makeStringSelectTag(dbc, roleTagName, roleTagSelectSql, roleTagFirstOption, inputData.roleId);
                // no database connection leaks !
            } // postback

            //roleSelectTag = MakeTags.makeStringSelectTag(dbc, roleTagName, roleTagSelectSql, roleTagFirstOption, inputData.roleId);
            if (request.getParameter("teamAbbrvInput") != null) { // this is postback

                // package up Customer String data
                inputData.team_id = request.getParameter("team_id");
                inputData.team_abbrv = request.getParameter("teamAbbrvInput");
                inputData.team_est = request.getParameter("estInput");
                inputData.team_trophies = request.getParameter("trophiesInput");
                inputData.team_logo = request.getParameter("logoInput");
                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();

                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    errorMsgs = DbMods.update(inputData, dbc); // errorMsgs will hold all validation messags
                    if (errorMsgs.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Record successfully Updated !";
                    }
                    else{
                        errorMsgs.errorMsg = "ERROR! Could not update record.";
                    }
                } // if db connection is good
                 // no database connection leaks !
            } // postback
            dbc.close();
        %>  
        <div id ="container">
            <jsp:include page="headToContent.jsp" />

            <div id="content">
                <form action ="updateOther.jsp" method="get">
                    <table>
                        <tr>
                            <td>Team Id</td>
                            <td><input type="text"  name="team_id" value="<%=inputData.team_id%> "readonly/></td>
                            <td class="error"></td> 
                        </tr>
                        <tr>
                            <td>Team Abbreviation</td>
                            <td><input type="text"  name="teamAbbrvInput" value="<%=inputData.team_abbrv%>"/></td>
                            <td class="error"><%=errorMsgs.team_abbrv%></td> 
                        </tr>
                        <tr>
                            <td>Established</td>
                            <td><input type="text"  name="estInput" value="<%=inputData.team_est%>"/></td>
                            <td class="error"><%=errorMsgs.team_est%></td>
                        </tr>
                        <tr>
                            <td>Number of Trophies</td>
                            <td><input type="text" name="trophiesInput" value="<%=inputData.team_trophies%>"/></td>
                            <td class="error"><%=errorMsgs.team_trophies%></td>
                        </tr>
                        <tr>
                            <td>Team Logo</td>
                            <td><input type="text" name="logoInput" value="<%=inputData.team_logo%>"/></td>
                            <td class="error"><%=errorMsgs.team_logo%></td> 
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
