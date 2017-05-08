/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.comments;

import dbUtils.DbConn;
import dbUtils.FormatUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dev
 */
public class StringDataList {

    public String dbError = "";
    ArrayList<StringData> commentList = new ArrayList();

    public StringDataList() {

    }

    public StringDataList(String userId, String teamId, String commentDate, DbConn dbc) {
        PreparedStatement stmt = null;
        ResultSet results = null;
        String sql = "";
        StringData inputData = new StringData();
        System.out.println("user ID: " + userId);
        System.out.println("team ID: " + teamId);

        try {
            sql = "SELECT comment_id, user_screen_name, comments, comment_date, "
                    + "team_abbrv,user_email FROM sp17_3308_tuf37373.`comments_table`, sp17_3308_tuf37373.`teams_table`, "
                    + "sp17_3308_tuf37373.`user_table` "
                    + "WHERE sp17_3308_tuf37373.`comments_table`.`teams_table_team_id` = sp17_3308_tuf37373.`teams_table`.`team_id` "
                    + "AND sp17_3308_tuf37373.`comments_table`.`user_table_user_id` = sp17_3308_tuf37373.`user_table`.`user_id`";
            System.out.println("BEFORE SQL");
            //Filters as per request from user
            if (Integer.parseInt(userId) != 0) {
                sql += "AND sp17_3308_tuf37373.`user_table`.`user_id` = ? ";
            }
            if (Integer.parseInt(teamId) != 0) {
                sql += "AND sp17_3308_tuf37373.`teams_table`.`team_id` = ? ";
            }
            if (commentDate.length() != 0) {
                sql += "AND sp17_3308_tuf37373.`comments_table`.`comment_date` = ? ";
            }
            sql += "\nORDER BY comment_id";

            stmt = dbc.getConn().prepareStatement(sql);
            System.out.println("AFTER SQL");
            int count = 1;
            if (Integer.parseInt(userId) != 0) {
                stmt.setInt(count, Integer.parseInt(userId));
                count++;
            }
            if (Integer.parseInt(teamId) != 0) {
                stmt.setInt(count, Integer.parseInt(teamId));
                count++;
            }
            if (commentDate.length() != 0) {
                stmt.setDate(count, Date.valueOf(commentDate));
            }

            results = stmt.executeQuery();
            while (results.next()) {
                System.out.println("ITS IN THE RESULTS");
                inputData = new StringData();
                inputData.comment_id = FormatUtils.formatInteger(results.getObject("comment_id"));
                System.out.println("Comment ID: " + inputData.comment_id);
                inputData.userName = FormatUtils.formatString(results.getObject("user_screen_name"));
                inputData.comment = FormatUtils.formatString(results.getObject("comments"));
                inputData.comment_date = FormatUtils.formatDate(results.getObject("comment_date"));
                inputData.teamAbbrv = FormatUtils.formatString(results.getObject("team_abbrv"));
                inputData.userEmail = FormatUtils.formatString(results.getObject("user_email"));
                this.commentList.add(inputData);
            }

        } catch (Exception e) {
            this.dbError = "ERROR IN SQL STATEMENT";
            
        }

    }

}
