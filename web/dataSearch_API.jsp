<%-- 
    Document   : dataSearch_API
    Created on : Apr 1, 2017, 6:37:45 PM
    Author     : Dev
--%>

<%@page import="model.comments.StringDataList"%>
<%@page import="View.CommentsView"%>
<%@page import="dbUtils.FormatUtils"%>
<%@page import="model.comments.StringData"%>
<%@page import="dbUtils.DbConn"%>
<%@page language="java" import="com.google.gson.*" %>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 



<%
    DbConn dbc = new DbConn();
    String commentDate = "";
    String userId = "0";
    String teamId = "0";
    StringDataList List = new StringDataList();

    if (request.getParameter("userId") != null) {
        userId = request.getParameter("userId");
    }
    if (request.getParameter("teamId") != null) {
        teamId = request.getParameter("teamId");
    }
    if (request.getParameter("date") != null) {
        commentDate = request.getParameter("date");
    }
    List.dbError = dbc.getErr();
    if (List.dbError.length() == 0) {

        List = new StringDataList(userId, teamId, commentDate, dbc);
        
    }

    dbc.close();
    Gson gson = new Gson();

    out.print(gson.toJson(List).trim());


%>
