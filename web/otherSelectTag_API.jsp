<%-- 
    Document   : otherSelectTag_API
    Created on : Mar 30, 2017, 6:23:31 PM
    Author     : Dev
--%>

<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page language="java" import="com.google.gson.*" %>
<%@page language="java" import="dbUtils.*" %>
<%@page language="java" import="java.sql.*" %>



<%

    String msg = "";
    DbConn dbc = new DbConn();
    PreparedStatement stmt = null;
    ResultSet result = null;
    SelectOptionList teamList = new SelectOptionList();
    String sql = "SELECT team_id, team_abbrv FROM sp17_3308_tuf37373.`teams_table` ORDER BY team_id";

    try {
        stmt = dbc.getConn().prepareStatement(sql);
        result = stmt.executeQuery();
        teamList.addOption(new SelectOption("0", "Select Team"));
        while (result.next()) {
            teamList.addOption(new SelectOption(FormatUtils.formatInteger(result.getObject("team_id")),
                    FormatUtils.formatString(result.getObject("team_abbrv"))));
        }
        stmt.close();
        result.close();
        dbc.close();

        Gson gson = new Gson();
        out.print(gson.toJson(teamList).trim());
    } catch (Exception e) {
        msg = "Error in Sql Statement";
        out.print(msg);
    }


%>