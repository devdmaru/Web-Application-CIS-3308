/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.users.*;

/**
 *
 * @author Dev
 */
public class UserView {

    /* This method returns a HTML table displaying all the records of the web_user table. 
     * cssClassForResultSetTable: the name of a CSS style that will be applied to the HTML table.
     *   (This style should be defined in the JSP page (header or style sheet referenced by the page).
     * dbc: an open database connection.
     */
    public static String listAllUsers(String cssTableClass, DbConn dbc) {

        // String type could have been used, but StringBuilder is more efficient 
        // in this case where we are just appending
        StringBuilder sb = new StringBuilder("");

        PreparedStatement stmt = null;
        ResultSet results = null;
        try {
            //sb.append("ready to create the statement & execute query " + "<br/>");
            String sql = "SELECT user_id, user_screen_name, user_email, user_passwd, user_birthdate, "
                    + "user_role_name FROM sp17_3308_tuf37373.`user_role_table`, sp17_3308_tuf37373.`user_table`\n"
                    + "WHERE `user_role_table`.`user_role_id` =  `user_table`.`user_role_table_user_role_id`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>User ID</th>");
            sb.append("<th style='text-align:center'>User Name</th>");
            sb.append("<th style='text-align:center'>User Email</th>");
            sb.append("<th style='text-align:center'>User Password</th>");
            sb.append("<th style='text-align:center'>Birthday</th>");
            sb.append(("<th style='text-alignment:center'>User Role</th></tr>"));
            while (results.next()) {
                sb.append("<tr>");
                sb.append(FormatUtils.formatIntegerTd(results.getObject("user_id")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_email")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_passwd")));
                sb.append(FormatUtils.formatDateTd(results.getObject("user_birthdate")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_role_name")));
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
            String sql = "SELECT user_id, user_screen_name, user_email, user_passwd, user_birthdate, "
                    + "user_role_name FROM sp17_3308_tuf37373.`user_role_table`, sp17_3308_tuf37373.`user_table`\n"
                    + "WHERE `user_role_table`.`user_role_id` =  `user_table`.`user_role_table_user_role_id`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>User ID</th>");
            sb.append("<th style='text-align:center'>User Name</th>");
            sb.append("<th style='text-align:center'>User Email</th>");
            sb.append("<th style='text-align:center'>User Password</th>");
            sb.append("<th style='text-align:center'>Birthday</th>");
            sb.append(("<th style='text-alignment:center'>User Role</th></tr>"));
            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateUser.jsp?user_id=");
                int id = (int) results.getObject("user_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_email")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_passwd")));
                sb.append(FormatUtils.formatDateTd(results.getObject("user_birthdate")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_role_name")));
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
            String sql = "SELECT user_id, user_screen_name, user_email, user_passwd, user_birthdate, "
                    + "user_role_name FROM sp17_3308_tuf37373.`user_role_table`, sp17_3308_tuf37373.`user_table`\n"
                    + "WHERE `user_role_table`.`user_role_id` =  `user_table`.`user_role_table_user_role_id`";
            stmt = dbc.getConn().prepareStatement(sql);
            results = stmt.executeQuery();
            sb.append("<table class='");
            sb.append(cssTableClass);
            sb.append("'>");
            sb.append("<tr>");
            sb.append("<th style='text-align:center'>Update</th>");
            sb.append("<th style='text-align:center'>User ID</th>");
            sb.append("<th style='text-align:center'>User Name</th>");
            sb.append("<th style='text-align:center'>User Email</th>");
            sb.append("<th style='text-align:center'>User Password</th>");
            sb.append("<th style='text-align:center'>Birthday</th>");
            sb.append(("<th style='text-alignment:center'>User Role</th>"));
            sb.append(("<th style='text-alignment:center'>Delete</th></tr>"));
            while (results.next()) {
                sb.append("<tr>");
                sb.append("<td style='text-align:center'><a href = 'updateUser.jsp?user_id=");
                int id = (int) results.getObject("user_id");
                sb.append(FormatUtils.formatInteger(id));
                sb.append("'>");
                sb.append("<img src ='pics/update.png'></a></td>");
                sb.append(FormatUtils.formatIntegerTd(id));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_screen_name")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_email")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_passwd")));
                sb.append(FormatUtils.formatDateTd(results.getObject("user_birthdate")));
                sb.append(FormatUtils.formatStringTd(results.getObject("user_role_name")));
                sb.append("<td style='text-align:center'><a href = 'users.jsp?user_id=");
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
            sql = "SELECT user_id, user_screen_name, user_email, user_passwd, user_birthdate, "
                    + "user_role_name, user_role_id FROM sp17_3308_tuf37373.`user_role_table`, sp17_3308_tuf37373.`user_table`\n"
                    + "WHERE `user_role_table`.`user_role_id` =  `user_table`.`user_role_table_user_role_id`";
            
            if (Integer.parseInt(inputData.userId) != 0) {
                sql += "AND sp17_3308_tuf37373.`user_table`.`user_id` = ? ";
            }
            System.out.println(inputData.userId);
            stmt = dbc.getConn().prepareStatement(sql);
            int count = 1;
            if (Integer.parseInt(inputData.userId) != 0) {
                stmt.setInt(count, Integer.parseInt(inputData.userId));
                count++;
            }
            results = stmt.executeQuery();
            if (results.next()) {
                inputData.userEmail = FormatUtils.formatString(results.getObject("user_email"));
                inputData.userName = FormatUtils.formatString(results.getObject("user_screen_name"));
                inputData.userPasswd = FormatUtils.formatString(results.getObject("user_passwd"));
                inputData.birthDate = FormatUtils.formatDate(results.getObject("user_birthdate"));
                inputData.roleId = FormatUtils.formatString(results.getObject("user_role_id"));
                inputData.errorMsg = "";
            }        
            
            return inputData;
        } catch (Exception e) {
            inputData.errorMsg = "ERROR in getting User Data. Check the Sql Statement";
            return inputData;
                    
        }

    }

}
