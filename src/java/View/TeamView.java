/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.teams.StringData;

/**
 *
 * @author Dev
 */
public class TeamView {

    public static String listAllTeams(String cssTableClass, DbConn dbc) {

        // String type could have been used, but StringBuilder is more efficient 
        // in this case where we are just appending
        StringBuilder sb = new StringBuilder("");

        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            //sb.append("ready to create the statement & execute query " + "<br/>");
            String sql = "SELECT team_id, team_abbrv, team_est, team_trophies, team_logo "
                    + "FROM sp17_3308_tuf37373.`teams_table`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Team ID</th>");
            sb.append("<th style='text-align:center'>Team Name</th>");
            sb.append("<th style='text-align:center'>Established</th>");
            sb.append("<th style='text-align:center'>Trophies</th>");
            sb.append("<th style='text-align:center'>Team Logo</th></tr>");

            while (results.next()) {
                sb.append("<tr>");
                sb.append(FormatUtils.formatIntegerTd(results.getObject("team_id")));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append(FormatUtils.formatDateTd(results.getObject("team_est")));
                sb.append(FormatUtils.formatIntegerTd(results.getObject("team_trophies")));
                sb.append(FormatUtils.formatStringTd("<img width= '220px' height = '150px' src= '"
                        + results.getObject("team_logo") + "'"));

                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "Exception thrown in ViewUserSql.listAllUsers(): " + e.getMessage()
                    + "<br/> partial output: <br/>" + sb.toString();
        }
    }

    public static String listWithUpdate(String cssTableClass, DbConn dbc) {

        // String type could have been used, but StringBuilder is more efficient 
        // in this case where we are just appending
        StringBuilder sb = new StringBuilder("");

        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            //sb.append("ready to create the statement & execute query " + "<br/>");
            String sql = "SELECT team_id, team_abbrv, team_est, team_trophies, team_logo "
                    + "FROM sp17_3308_tuf37373.`teams_table`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>Team ID</th>");
            sb.append("<th style='text-align:center'>Team Name</th>");
            sb.append("<th style='text-align:center'>Established</th>");
            sb.append("<th style='text-align:center'>Trophies</th>");
            sb.append("<th style='text-align:center'>Team Logo</th></tr>");

            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateOther.jsp?team_id=");
                int id = (int) results.getObject("team_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append(FormatUtils.formatDateTd(results.getObject("team_est")));
                sb.append(FormatUtils.formatIntegerTd(results.getObject("team_trophies")));
                sb.append(FormatUtils.formatStringTd("<img width= '220px' height = '150px' src= '"
                        + results.getObject("team_logo") + "'"));

                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "Exception thrown in ViewUserSql.listAllUsers(): " + e.getMessage()
                    + "<br/> partial output: <br/>" + sb.toString();
        }
    }
    
    public static String listWithUpdateAndDelete(String cssTableClass, DbConn dbc) {

        // String type could have been used, but StringBuilder is more efficient 
        // in this case where we are just appending
        StringBuilder sb = new StringBuilder("");

        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            //sb.append("ready to create the statement & execute query " + "<br/>");
            String sql = "SELECT team_id, team_abbrv, team_est, team_trophies, team_logo "
                    + "FROM sp17_3308_tuf37373.`teams_table`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>Team ID</th>");
            sb.append("<th style='text-align:center'>Team Name</th>");
            sb.append("<th style='text-align:center'>Established</th>");
            sb.append("<th style='text-align:center'>Trophies</th>");
            sb.append("<th style='text-align:center'>Team Logo</th>");
            sb.append(("<th style='text-alignment:center'>Delete</th></tr>"));

            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateOther.jsp?team_id=");
                int id = (int) results.getObject("team_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append(FormatUtils.formatDateTd(results.getObject("team_est")));
                sb.append(FormatUtils.formatIntegerTd(results.getObject("team_trophies")));
                sb.append(FormatUtils.formatStringTd("<img width= '220px' height = '150px' src= '"
                        + results.getObject("team_logo") + "'"));
                sb.append("<td style='text-align:center'><a href = 'other.jsp?team_id=");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/delete.png'></a></td>");

                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "Exception thrown in ViewUserSql.listAllUsers(): " + e.getMessage()
                    + "<br/> partial output: <br/>" + sb.toString();
        }
    }
    
    
    public static StringData search(DbConn dbc, StringData inputData) {
        StringBuilder sb = new StringBuilder("");
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet results = null;

        try {
            sql = "SELECT team_id, team_abbrv, team_est, team_trophies, team_logo "
                    + "FROM sp17_3308_tuf37373.`teams_table`\n";
            
            if (Integer.parseInt(inputData.team_id) != 0) {
                sql += "WHERE team_id = ?";
            }
            
            stmt = dbc.getConn().prepareStatement(sql);
            int count = 1;
            if (Integer.parseInt(inputData.team_id) != 0) {
                stmt.setInt(count, Integer.parseInt(inputData.team_id));
                count++;
            }
            results = stmt.executeQuery();
            if (results.next()) {
                //inputData.team_id = FormatUtils.formatInteger(results.getObject("team_id"));
                inputData.team_abbrv = FormatUtils.formatString(results.getObject("team_abbrv"));
                inputData.team_trophies = FormatUtils.formatInteger(results.getObject("team_trophies"));
                inputData.team_est = FormatUtils.formatDate(results.getObject("team_est"));
                inputData.team_logo = FormatUtils.formatString(results.getObject("team_logo"));
                inputData.errorMsg = "";
            }        
            
            return inputData;
        } catch (Exception e) {
            inputData.errorMsg = "ERROR in getting Team Data. Check the Sql Statement";
            return inputData;
                    
        }

    }

}
