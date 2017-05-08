package model.users;

import dbUtils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbMods {

    /*
     Returns a "StringData" object that is full of field level validation
     error messages (or it is full of all empty strings if inputData
     totally passed validation.  
     */
    private static StringData validate(StringData inputData) {

        StringData errorMsgs = new StringData();

        // Validation
        errorMsgs.userEmail = ValidationUtils.stringValidationMsg(inputData.userEmail, 45, true);
        errorMsgs.userPasswd = ValidationUtils.stringValidationMsg(inputData.userPasswd, 45, true);

        if (inputData.userPasswd.compareTo(inputData.userPasswd2) != 0) { // case sensative comparison
            errorMsgs.userPasswd2 = "Both passwords must match";
        }

        errorMsgs.userName = ValidationUtils.stringValidationMsg(inputData.userName, 15, true);
        errorMsgs.birthDate = ValidationUtils.dateValidationMsg(inputData.birthDate, false);
        errorMsgs.roleId = ValidationUtils.stringValidationMsg(inputData.roleId, 10, true);

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
            String sql = "INSERT INTO user_table (user_email, user_passwd, user_screen_name, user_birthdate, user_role_table_user_role_id) "
                    + "values (?,?,?,?,?)";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);

            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.userEmail);
            pStatement.setString(2, inputData.userPasswd);
            pStatement.setString(3, inputData.userName);
            pStatement.setDate(4, ValidationUtils.dateConversion(inputData.birthDate));
            pStatement.setString(5, inputData.roleId);
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
        } // customerId is not null and not empty string.
        return errorMsgs;
    } // insert

    public static StringData update(StringData inputData, DbConn dbc) {

        StringData errorMsgs = new StringData();
        errorMsgs = validate(inputData);
        if (errorMsgs.getCharacCount() > 0) {  // at least one field has an error, don't go any further.
            errorMsgs.errorMsg = "Please try again";
            return errorMsgs;

        } else { // all fields passed validation

            // Start preparing SQL statement
            String sql = "UPDATE user_table SET user_email=?, user_passwd=?, user_screen_name=?, user_birthdate=?, user_role_table_user_role_id=? "
                    + "WHERE user_id=? ";

            // PrepStatement is Sally's wrapper class for java.sql.PreparedStatement
            // Only difference is that Sally's class takes care of encoding null 
            // when necessary. And it also System.out.prints exception error messages.
            PrepStatement pStatement = new PrepStatement(dbc, sql);
            // Encode string values into the prepared statement (wrapper class).
            pStatement.setString(1, inputData.userEmail);
            pStatement.setString(2, inputData.userPasswd);
            pStatement.setString(3, inputData.userName);
            pStatement.setDate(4, ValidationUtils.dateConversion(inputData.birthDate));
            pStatement.setString(5, inputData.roleId);
            pStatement.setInt(6, ValidationUtils.integerConversion(inputData.userId));
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
    } // update   

    public static String Delete(String user_id, DbConn dbc) {

        String sql = "";
        PreparedStatement stmt = null;
        int result = 0;
        String msg = "";

        try {
            sql += "DELETE FROM user_table WHERE user_id = ? ";
            stmt = dbc.getConn().prepareStatement(sql);
            stmt.setInt(1, ValidationUtils.integerConversion(user_id));
            result = stmt.executeUpdate();
            if (result == 1) {
                msg = "Delete Successful. User Deleted.";
            } else {
                msg = "Error Could not delete. Expected 1 row to be deleted, but " + result
                        + " rows were deleted.";
            }
            return msg;

        } catch (Exception e) {
            msg = "Error: Could not delete User with id: " + user_id + ". There may be records associated with this User.";
            return msg;
        }

    }

}
