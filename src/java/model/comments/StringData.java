/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.comments;


/**
 *
 * @author Dev
 */
public class StringData {
    public String comment_id = "";
    public String comment = ""; 
    public String comment_date = "";
    public String user_id = "";
    public String team_id = "";
    public String errorMsg = "";
    public String userName = "";
    public String teamAbbrv = "";
    public String userEmail = "";
    
    public int getCharacterCount() {
        String s = this.comment_id + this.comment + this.comment_date + this.user_id + this.team_id + this.userName 
                + this.teamAbbrv + this.userEmail;
        return s.length();
    }

    public String toString() {
        return "Comment id:" + this.comment_id
                + " comment:" + this.comment
                + " comment date:" + this.comment_date
                + " user id:" + this.user_id
                + " team id:" + this.team_id
                + " user name:" + this.userName
                + " user email:" + this.userEmail
                + " team abbreviation:" + this.teamAbbrv;

    }
    
    
    
    
    
    
    
    
}
