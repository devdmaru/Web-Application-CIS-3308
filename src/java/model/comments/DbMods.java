/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.comments;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dev
 */
public class DbMods {

    private static StringData validate(StringData inputData) {

        StringData errorMsgs = new StringData();

        // Validation
        errorMsgs.errorMsg = ValidationUtils.integerValidationMsg(inputData.comment_id, false);
        errorMsgs.comment = ValidationUtils.stringValidationMsg(inputData.comment, 200, false);
        errorMsgs.comment_date = ValidationUtils.dateValidationMsg(inputData.comment_date, false);
        errorMsgs.team_id = ValidationUtils.integerValidationMsg(inputData.team_id, true);
        errorMsgs.user_id = ValidationUtils.integerValidationMsg(inputData.user_id, true);

        return errorMsgs;
    } // validate 

    public static StringData insert(StringData inputData, DbConn dbc) {
        StringData errorMsgs = new StringData();
        errorMsgs = validate(inputData);
        if (errorMsgs.getCharacterCount() > 0) {  // at least one field has an error, don't go any further.
            errorMsgs.errorMsg = "Please try again";
            return errorMsgs;

        } else { // all fields passed validation

            // Start preparing SQL statement
            String sql = "INSERT INTO comments_table (comments, comment_date, user_table_user_id, teams_table_team_id) "
                    + "values (?,?,?,?)";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.comment);
            pStatement.setDate(2, ValidationUtils.dateConversion(inputData.comment_date));
            pStatement.setString(3, inputData.user_id);
            pStatement.setString(4, inputData.team_id);

            // here the SQL statement is actually executed
            int numRows = pStatement.executeUpdate();

            // This will return empty string if all went well, else all error messages.
            errorMsgs.errorMsg = pStatement.getErrorMsg();
            if (errorMsgs.errorMsg.length() == 0) {
                if (numRows == 1) {
                    errorMsgs.errorMsg = ""; // This means SUCCESS. Let the JSP page decide how to tell this to the user.
                } else {
                    // probably never get here unless you forgot your WHERE clause and did a bulk sql update.
                    errorMsgs.errorMsg = numRows + " records were inserted when exactly 1 was expected.";
                }
            }
        } // teamId is not null and not empty string.
        return errorMsgs;
    } // insert

    public static StringData update(StringData inputData, DbConn dbc) {
        StringData errorMsgs = new StringData();
        errorMsgs = validate(inputData);
        if (errorMsgs.getCharacterCount() > 0) {  // at least one field has an error, don't go any further.
            errorMsgs.errorMsg = "Please try again";
            return errorMsgs;

        } else { // all fields passed validation

            // Start preparing SQL statement
            String sql = "UPDATE comments_table SET comments=?, comment_date=?, teams_table_team_id=?, user_table_user_id=? "
                    + "WHERE comment_id=? ";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);
            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.comment);
            pStatement.setDate(2, ValidationUtils.dateConversion(inputData.comment_date));
            pStatement.setInt(3, ValidationUtils.integerConversion(inputData.team_id));
            pStatement.setInt(4, ValidationUtils.integerConversion(inputData.user_id));
            pStatement.setInt(5, ValidationUtils.integerConversion(inputData.comment_id));
            // here the SQL statement is actually executed
            int numRows = pStatement.executeUpdate();

            // This will return empty string if all went well, else all error messages.
            errorMsgs.errorMsg = pStatement.getErrorMsg();
            if (errorMsgs.errorMsg.length() == 0) {
                if (numRows == 1) {
                    errorMsgs.errorMsg = ""; // This means SUCCESS. Let the JSP page decide how to tell this to the user.
                    //System.out.println("UPDATED SUCCESSFULLY");
                } else {
                    // probably never get here unless you forgot your WHERE clause and did a bulk sql update.
                    errorMsgs.errorMsg = numRows + " records were updated when exactly 1 was expected.";
                }
            }
        }
        return errorMsgs;

    }
    
    public static String Delete(String comment_id, DbConn dbc){
        
        String sql  = "";
        PreparedStatement stmt = null;
        int result = 0;
        String msg ="";

        try{
            sql += "DELETE FROM  comments_table WHERE comment_id = ? ";
            stmt = dbc.getConn().prepareStatement(sql);
            stmt.setInt(1, ValidationUtils.integerConversion(comment_id));
            result = stmt.executeUpdate();
            if(result == 1){
                msg = "Delete Successful. Comment Deleted.";
            } 
            else{
                msg = "Error Could not delete. Expected 1 row to be deleted, but " + result +
                         " rows were deleted.";
            }
            return msg;
            
        } catch (Exception e){
            msg = "Error in Sql Statemet. Could not delete Comment with id: " + comment_id + ".";
            return msg;
        }
        
    }

}
