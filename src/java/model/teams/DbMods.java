/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.teams;

import dbUtils.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Dev
 */
public class DbMods {

    private static StringData validate(StringData inputData) {

        StringData errorMsgs = new StringData();

        // Validation
        errorMsgs.errorMsg = ValidationUtils.stringValidationMsg(inputData.team_id, 45, false);
        errorMsgs.team_abbrv = ValidationUtils.stringValidationMsg(inputData.team_abbrv, 45, true);
        errorMsgs.team_logo = ValidationUtils.stringValidationMsg(inputData.team_logo, 200, false);
        errorMsgs.team_est = ValidationUtils.dateValidationMsg(inputData.team_est, false);
        errorMsgs.team_trophies = ValidationUtils.integerValidationMsg(inputData.team_trophies, false);

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
            String sql = "INSERT INTO teams_table (team_abbrv, team_est, team_trophies, team_logo) "
                    + "values (?,?,?,?)";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.team_abbrv);
            pStatement.setDate(2, ValidationUtils.dateConversion(inputData.team_est));
            pStatement.setInt(3, ValidationUtils.integerConversion(inputData.team_trophies));
            pStatement.setString(4, inputData.team_logo);

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
            String sql = "UPDATE teams_table SET team_abbrv=?, team_trophies=?, team_est=?, team_logo=? "
                    + " WHERE team_id=? ";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);
            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.team_abbrv);
            pStatement.setString(2, inputData.team_trophies);
            pStatement.setDate(3, ValidationUtils.dateConversion(inputData.team_est));
            pStatement.setString(4, inputData.team_logo);
            System.out.println("TEAM ID: " + inputData.team_id);
            pStatement.setInt(5, ValidationUtils.integerConversion(inputData.team_id));
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
        } // customerId is not null and not empty string.
        return errorMsgs;
    } // insert

    public static String Delete(String team_id, DbConn dbc) {

        String sql = "";
        PreparedStatement stmt = null;
        int result = 0;
        String msg = "";

        try {
            sql += "DELETE FROM  teams_table WHERE team_id = ? ";
            stmt = dbc.getConn().prepareStatement(sql);
            stmt.setInt(1, ValidationUtils.integerConversion(team_id));
            result = stmt.executeUpdate();
            if (result == 1) {
                msg = "Delete Successful. Team Deleted.";
            } else {
                msg = "Error Could not delete. Expected 1 row to be deleted, but " + result
                        + " rows were deleted.";
            }
            return msg;

        } catch (Exception e) {
            msg = "Error: Could not delete Team with id: " + team_id + ". There may be records associated"
                    + " with this team.";
            return msg;
        }

    }

}
