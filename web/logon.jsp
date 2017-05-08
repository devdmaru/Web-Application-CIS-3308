<%-- 
    Document   : logon
    Created on : Feb 18, 2017, 1:07:49 PM
    Author     : Dev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="model.users.StringData"%>
<%@page language="java" import="dbUtils.*"%>
<%@page language="java" import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Log on</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
        <style>
            .error {
                color:red;
            }
        </style>
    </head>

    <%

        StringData userData = new StringData();
        StringData userErrors = new StringData();
        String strUserEmail = "sample01@example.com";
        String strUserPwd = "123";
        String sql = "";
        PreparedStatement prepStmt = null;
        ResultSet result = null;
        DbConn dbc;

        // Still have a "form message" 
        String msg = ""; // this is an overall messsage (beyond field level validation)
        dbc = new DbConn();

        msg = dbc.getErr();
        if (msg.length() > 0) {
            msg = "Error in connecting to database";
        }
        // still need to check for postback 
        if (request.getParameter("emailInput") != null) {

            // persist data from the URL
            userData.userEmail = request.getParameter("emailInput");
            userData.userPasswd = request.getParameter("password");

            if (userData.userEmail.length() == 0) {
                userErrors.userEmail = "email is required.";
            }

            if (userData.userPasswd.length() == 0) {
                userErrors.userPasswd = "Password is required.";
            }
            strUserEmail = userData.userEmail;
            strUserPwd = userData.userPasswd;

            sql = "SELECT user_id, user_screen_name, user_email, user_passwd FROM sp17_3308_tuf37373.`user_table`"
                    + "WHERE user_email = ? AND user_passwd = ?";

            prepStmt = dbc.getConn().prepareStatement(sql);
            prepStmt.setString(1, userData.userEmail);
            prepStmt.setString(2, userData.userPasswd);

            result = prepStmt.executeQuery();

            if (result.next()) {
                msg = "Hello " + result.getString("user_screen_name") + ". Please wait redirecting you to"
                        + " the home page.";
                session.setAttribute("User_Email", result.getString("user_email"));
                session.setAttribute("Name", result.getString("user_screen_name"));
                session.setAttribute("User_Id", result.getString("user_id"));

                String redirectURL = "index.jsp";
                response.setHeader("Refresh", "3;url=index.jsp");

            } else {
                msg = "Invalid Logon";
            }
        }

    %>

    <body>
        <div  id="container">
            <jsp:include page="headToContent.jsp" />
            <div id="content">
                <h2>Login</h2>
                <form action="logon.jsp" method="post">
                    Please enter your email. 
                    <input name="emailInput" value="<%=strUserEmail%>"/> 
                    <span class="error"><%=userErrors.userEmail%></span> 
                    <br/><br/>
                    Please enter your password 
                    <input name ="password" type="password" value="<%=strUserPwd%>"/>
                    <span class="error"><%=userErrors.userPasswd%></span>
                    <br/><br/>
                    <input type="submit" value="Log in"/>
                    <br/><br/>
                    <%=msg%>
                </form>
            </div>
            <jsp:include page="bottom.jsp" />
        </div> <!-- container -->
    </body>
</html>
