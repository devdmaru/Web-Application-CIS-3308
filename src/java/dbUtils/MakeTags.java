package dbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MakeTags {

    /* 
     Example of a select tag that you would want to create: 
    
     <select name='dept'>
     <option value='0'>Select Department</option>
     <option value='3'>Biology</option> 
     <option value='4' selected='selected' >Chemistry</option>
     <option value='2'>Math</option>
     <option value='1'>Physics</option>
     </select>

     */
    public static String makeSelectTag(DbConn dbc, String tagName, String sql, String firstOption, String selected) {

        // String type could have been used, but StringBuilder is more efficient 
        // when just appending to a string as we are doing here. 
        StringBuilder sb = new StringBuilder("<select name='" + tagName + "'>\n");

        // \n just makes it easier to read the "View Source"
        sb.append(firstOption + "\n");
        try {
            PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                sb.append("<option value='");
                sb.append(FormatUtils.formatInteger(results.getObject(1)));
                if (FormatUtils.formatInteger(results.getObject(1)).equals(String.valueOf(selected))) {
                    sb.append("'");
                    sb.append("selected = '");
                    sb.append(selected);
                }
                sb.append("'>");
                sb.append(FormatUtils.formatString(results.getObject(2)));

                sb.append("</option>\n");
            }
        } catch (Exception e) {
            // Put error message right in the select tag (easy for JSP programmer to see error).
            sb.append("<option>Exception thrown in WebUserSql.listAllUsers(): " + e.getMessage()
                    + "</option>");
        }

        sb.append("</select>");
        return sb.toString();
    }
    public static String makeStringSelectTag(DbConn dbc, String tagName, String sql, String firstOption, String selected) {

        // String type could have been used, but StringBuilder is more efficient 
        // when just appending to a string as we are doing here. 
        StringBuilder sb = new StringBuilder("<select name='" + tagName + "'>\n");

        // \n just makes it easier to read the "View Source"
        sb.append(firstOption + "\n");
        try {
            PreparedStatement stmt = dbc.getConn().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while (results.next()) {
                sb.append("<option value='");
                sb.append(FormatUtils.formatString(results.getObject(1)));
                if (FormatUtils.formatString(results.getObject(1)).equals(String.valueOf(selected))) {
                    sb.append("'");
                    sb.append("selected = '");
                    sb.append(selected);
                }
                sb.append("'>");
                sb.append(FormatUtils.formatString(results.getObject(2)));

                sb.append("</option>\n");
            }
        } catch (Exception e) {
            // Put error message right in the select tag (easy for JSP programmer to see error).
            sb.append("<option>Exception thrown in WebUserSql.listAllUsers(): " + e.getMessage()
                    + "</option>");
        }

        sb.append("</select>");
        return sb.toString();
    }
}
