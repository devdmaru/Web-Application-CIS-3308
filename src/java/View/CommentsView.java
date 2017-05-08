/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import dbUtils.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.comments.StringData;

/**
 *
 * @author Dev
 */
public class CommentsView {

    public static String listAllComments(String cssTableClass, DbConn dbc) {

        // String type could have been used, but StringBuilder is more efficient 
        // in this case where we are just appending
        StringBuilder sb = new StringBuilder("");

        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            //sb.append("ready to create the statement & execute query " + "<br/>");
            String sql = "SELECT comment_id, user_screen_name, comments, comment_date, "
                    + "team_abbrv FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Comment ID</th>");
            sb.append("<th style='text-align:center'>User</th>");
            sb.append("<th style='text-align:center'>Comments</th>");
            sb.append("<th style='text-align:center'>comment Date</th>");
            sb.append("<th style='text-align:center'>Team</th></tr>");

            while (results.next()) {
                sb.append("<tr>");
                sb.append(FormatUtils.formatIntegerTd(results.getObject("comment_id")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("comments")));
                sb.append(FormatUtils.formatDateTd(results.getObject("comment_date")));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "Exception thrown in ViewUserSql.listAllComments(): " + e.getMessage()
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
            String sql = "SELECT comment_id, user_screen_name, comments, comment_date, "
                    + "team_abbrv FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>Comment ID</th>");
            sb.append("<th style='text-align:center'>User</th>");
            sb.append("<th style='text-align:center'>Comments</th>");
            sb.append("<th style='text-align:center'>Comment Date</th>");
            sb.append("<th style='text-align:center'>Team</th></tr>");

            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateAssoc.jsp?comment_id=");
                int id = (int) results.getObject("comment_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("comments")));
                sb.append(FormatUtils.formatDateTd(results.getObject("comment_date")));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "Exception thrown in ViewUserSql.listAllComments(): " + e.getMessage()
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
            String sql = "SELECT comment_id, user_screen_name, comments, comment_date, "
                    + "team_abbrv FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id` order by comment_id";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>Comment ID</th>");
            sb.append("<th style='text-align:center'>User</th>");
            sb.append("<th style='text-align:center'>Comments</th>");
            sb.append("<th style='text-align:center'>Comment Date</th>");
            sb.append("<th style='text-align:center'>Team</th>");
            sb.append(("<th style='text-alignment:center'>Delete</th></tr>"));

            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateAssoc.jsp?comment_id=");
                int id = (int) results.getObject("comment_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("comments")));
                sb.append(FormatUtils.formatDateTd(results.getObject("comment_date")));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append("<td style='text-align:center'><a href = 'assoc.jsp?comment_id=");
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
            return "Exception thrown in ViewUserSql.listAllComments(): " + e.getMessage()
                    + "<br/> partial output: <br/>" + sb.toString();
        }
    }

    public static String search(DbConn dbc, String userStringId, String otherStringId, String date, String cssTableClass) {
        StringBuilder sb = new StringBuilder("");
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet results = null;
        try {

            sql = "SELECT comment_id, user_screen_name, comments, comment_date, "
                    + "team_abbrv,user_email FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id`";

            //Filters as per request from user
            if (Integer.parseInt(userStringId) != 0) {
                sql += "AND sp17_3308_tuf37373.`user_table`.`user_id` = ? ";
            }
            if (Integer.parseInt(otherStringId) != 0) {
                sql += "AND sp17_3308_tuf37373.`teams_table`.`team_id` = ? ";
            }
            if (date.length() != 0) {
                sql += "AND sp17_3308_tuf37373.`comments_table`.`comment_date` = ? ";
            }
            sql += "\nORDER BY comment_id";

            stmt = dbc.getConn().prepareStatement(sql);
            int count = 1;
            if (Integer.parseInt(userStringId) != 0) {
                stmt.setInt(count, Integer.parseInt(userStringId));
                count++;
            }
            if (Integer.parseInt(otherStringId) != 0) {
                stmt.setInt(count, Integer.parseInt(otherStringId));
                count++;
            }
            if (date.length() != 0) {
                stmt.setDate(count, Date.valueOf(date));
            }

            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Comment ID</th>");
            sb.append("<th style='text-align:center'>User</th>");
            sb.append("<th style='text-align:center'>Comments</th>");
            sb.append("<th style='text-align:center'>Comment Date</th>");
            sb.append("<th style='text-align:center'>Team</th></tr>");

            while (results.next()) {
                sb.append("<tr>");
                sb.append(FormatUtils.formatIntegerTd(results.getObject("comment_id")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("comments")));
                sb.append(FormatUtils.formatDateTd(results.getObject("comment_date")));
                sb.append(FormatUtils.formatStringTd(results.getObject("team_abbrv")));
                sb.append("</tr>\n");
            }
            sb.append("</table>");
            results.close();
            stmt.close();
            System.out.println("This is a test Debug From UserView.java page");
            return sb.toString();
        } catch (Exception e) {
            return "There was an error in getting your request.";
        }
    }

    public static StringData searchComment(DbConn dbc, StringData inputData) {
        StringBuilder sb = new StringBuilder("");
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet results = null;
        System.out.println("IN SEARCH COMMENTS");
        try {
            sql = "SELECT comment_id, comments, comment_date, "
                    + "user_id, team_id FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id`";
            System.out.println("COMMENT ID : " + inputData.comment_id);
            if (Integer.parseInt(inputData.comment_id) != 0) {
                sql += "AND sp17_3308_tuf37373.`comments_table`.`comment_id`=? ";
            }

            stmt = dbc.getConn().prepareStatement(sql);
            int count = 1;
            if (Integer.parseInt(inputData.comment_id) != 0) {
                stmt.setInt(count, Integer.parseInt(inputData.comment_id));
                count++;
            }
            results = stmt.executeQuery();
            if (results.next()) {
                //inputData.comment_id = FormatUtils.formatInteger(results.getObject("comment_id"));
                inputData.comment = FormatUtils.formatString(results.getObject("comments"));
                inputData.comment_date = FormatUtils.formatDate(results.getObject("comment_date"));
                inputData.team_id = FormatUtils.formatInteger(results.getObject("team_id"));
                inputData.user_id = FormatUtils.formatInteger(results.getObject("user_id"));
                inputData.errorMsg = "";
            }

            return inputData;
        } catch (Exception e) {
            inputData.errorMsg = "ERROR in getting Comment Data. Check the Sql Statement";
            return inputData;

        }

    }

   
       

}
