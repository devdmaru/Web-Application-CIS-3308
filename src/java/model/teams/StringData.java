/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.teams;

/**
 *
 * @author Dev
 */
public class StringData {
    public String team_id = "";
    public String team_abbrv = ""; 
    public String team_est = "";
    public String team_trophies = "";
    public String team_logo = "";
    public String errorMsg = "";
    
    public int getCharacterCount() {
        String s = this.team_id + this.team_abbrv + this.team_est + this.team_trophies + this.team_logo;
        return s.length();
    }

    public String toString() {
        return "User id:" + this.team_id
                + " team name:" + this.team_abbrv
                + " etablished:" + this.team_est
                + " team trophies:" + this.team_trophies
                + " team logo:" + this.team_logo;

    }
    
    
    
    
    
    
    
    
}
