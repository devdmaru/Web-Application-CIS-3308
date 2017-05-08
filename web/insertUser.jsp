<%-- 
    Document   : insertUser
    Created on : Feb 28, 2017, 1:21:28 PM
    Author     : Dev
--%>

<%@page import="dbUtils.MakeTags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@page language="java" import="dbUtils.DbConn" %>
<%@page language="java" import="model.users.*" %>
<%@page language="java" import="View.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert User</title>
        <link href="index.css" rel="stylesheet" type="text/css" />
        <style>
        </style>
    </head>
    <body>
        <%
            String roleTagName = "role";
            String roleTagFirstOption = "<option value='0'>Select Role</option>";
            String roleSelectTag = "";
            String roleTagSelectSql = "select user_role_id, user_role_name from sp17_3308_tuf37373.`user_role_table` order by user_role_id";
            String msg = "";
            // Declare all Strings and objects as they should be if it is first rendering
            StringData inputData = new StringData(); // all properties empty string, good for first rendering
            StringData errorMsgs = new StringData(); // all properties empty string, good for first rendering
            DbConn dbc = new DbConn();
            
            msg = dbc.getErr();
            
            if(msg.length() > 0){
                msg = "Error in connecting to database";
            }

            if (request.getParameter("emailAddressInput") != null) { // this is postback

                // package up Customer String data
                inputData.userEmail = request.getParameter("emailAddressInput");
                inputData.userPasswd = request.getParameter("pwdInput");
                inputData.userPasswd2 = request.getParameter("pwd2Input");
                inputData.userName = request.getParameter("userNameInput");
                inputData.birthDate = request.getParameter("birthDateInput");
                inputData.roleId = request.getParameter(roleTagName);
                
                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();
                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    errorMsgs = DbMods.insert(inputData, dbc); // errorMsgs will hold all validation messags
                    if (errorMsgs.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Record successfully inserted !";
                    } else {
                        if (inputData.roleId.equals("0")) {
                            

                            errorMsgs.roleId = "Please Select a role";
                        }
                        errorMsgs.errorMsg = "ERROR! Could not insert user. Something went wrong.";
                    }
                } // if db connection is good

            } // postback
            roleSelectTag = MakeTags.makeStringSelectTag(dbc, roleTagName, roleTagSelectSql, roleTagFirstOption, inputData.roleId);
            dbc.close();

        %>
        <div id ="container">
            <jsp:include page="headToContent.jsp" />

            <div id="content">
                <form action ="insertUser.jsp" method="get">
                    <table>
                        <tr>
                            <td>Select Role</td>
                            <td><%=roleSelectTag%></td>
                            <td class="error"><%=errorMsgs.roleId%></td> 
                        </tr>      
                        <tr>
                            <td>Email Address</td>
                            <td><input type="text"  name="emailAddressInput" value="<%=inputData.userEmail%>"/></td>
                            <td class="error"><%=errorMsgs.userEmail%></td> 
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password"  name="pwdInput" value="<%=inputData.userPasswd%>"/></td>
                            <td class="error"><%=errorMsgs.userPasswd%></td>
                        </tr>
                        <tr>
                            <td>Retype Your Password</td>
                            <td><input type="password" name="pwd2Input" value="<%=inputData.userPasswd2%>"/></td>
                            <td class="error"><%=errorMsgs.userPasswd2%></td>
                        </tr>
                        <tr>
                            <td>User Screen Name</td>
                            <td><input type="text" name="userNameInput" value="<%=inputData.userName%>"/></td>
                            <td class="error"><%=errorMsgs.userName%></td> 
                        </tr>

                        <tr>
                            <td>Birth Date</td>
                            <td><input type="text" name="birthDateInput" value="<%=inputData.birthDate%>"/></td>
                            <td class="error"><%=errorMsgs.birthDate%></td>
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