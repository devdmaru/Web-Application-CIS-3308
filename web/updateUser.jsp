<%-- 
    Document   : updateUser
    Created on : Mar 7, 2017, 1:24:21 PM
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
        <title>Update User</title>
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

            if (msg.length() > 0) {
                msg = "Error in connecting to database";
            }

            if (request.getParameter("user_id") != null) { // this is postback

                // package up User String data
                //inputData.roleId = request.getParameter(roleTagName);
                inputData.userId = request.getParameter("user_id");
                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();
                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    inputData = UserView.search(dbc, inputData); // errorMsgs will hold all validation messags
                    if (inputData.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Ready for Update.";
                    }
                } // if db connection is good

                // no database connection leaks !
            } // postback

            if (request.getParameter("emailAddressInput") != null) { // this is postback

                // package up User String data
                inputData.roleId = request.getParameter(roleTagName);
                inputData.userEmail = request.getParameter("emailAddressInput");
                inputData.userPasswd = request.getParameter("pwdInput");
                inputData.userName = request.getParameter("userNameInput");
                inputData.birthDate = request.getParameter("birthDateInput");

                // Get db connection and make sure it worked.
                errorMsgs.errorMsg = dbc.getErr();
                if (errorMsgs.errorMsg.length() == 0) { // DB connection is good
                    errorMsgs = DbMods.update(inputData, dbc); // errorMsgs will hold all validation messags
                    if (errorMsgs.errorMsg.length() == 0) { // this is the form level error message
                        // replace empty string with successful message
                        errorMsgs.errorMsg = "Update Successful.";
                    } else {
                        if (inputData.roleId.equals("0")) {
                            errorMsgs.roleId = "Please Select a role";
                        }
                        errorMsgs.errorMsg = "ERROR! Could not update record.";
                    }
                } // if db connection is good

            } // postback 
            roleSelectTag = MakeTags.makeStringSelectTag(dbc, roleTagName, roleTagSelectSql, roleTagFirstOption, inputData.roleId);

            dbc.close();

        %>
        <div id ="container">
            <jsp:include page="headToContent.jsp" />

            <div id="content">
                <form action ="updateUser.jsp" method="get">
                    <table>
                        <tr>
                            <td>Select Role</td>
                            <td><%=roleSelectTag%></td>
                            <td class="error"><%=errorMsgs.roleId%></td> 
                        </tr>
                        <tr>
                            <td>User Id</td>
                            <td><input type="text"  name="user_id" value="<%=inputData.userId%>"readonly/></td>
                            <td class="error"></td> 
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
