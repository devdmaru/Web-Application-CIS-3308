<%-- 
    Document   : UserSelectTag
    Created on : Mar 28, 2017, 1:12:22 PM
    Author     : Dev
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page language="java" import="com.google.gson.*" %>
<%@page language="java" import="dbUtils.*" %>



<%
    String sql = "SELECT user_id, user_screen_name FROM sp17_3308_tuf37373.`user_table` order by user_id";
    SelectOptionList userList = new SelectOptionList();
    PreparedStatement stmt = null;
    ResultSet results = null;
    DbConn dbc = new DbConn();
    String msg = dbc.getErr();
    if (msg.length() == 0) {
        try {
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            userList.addOption(new SelectOption("0", "Select User"));
            while (results.next()) {

                userList.addOption(new SelectOption(FormatUtils.formatInteger(results.getObject("user_id")),
                        FormatUtils.formatString(results.getObject("user_screen_name"))));
            }

            results.close();
            stmt.close();

        } catch (Exception e) {
            msg = "Exception thrown in SQL Statement " + e.getMessage();
            out.print(msg);
        }
    }
    dbc.close();

    Gson gson = new Gson();
    out.print(gson.toJson(userList).trim());
%>