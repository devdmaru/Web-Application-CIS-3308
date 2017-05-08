package model.users;

/**
 *
 * @author Dev
 */
public class StringData {
    public String userId = "";
    public String userPasswd = "";
    public String userEmail = "";
    public String userName = "";
    public String userPasswd2 = "";
    public String birthDate = "";
    public String roleId = "";
    public String errorMsg = "";

    public int getCharacterCount() {
        String s = this.userId + this.userEmail + this.userPasswd + this.userPasswd2 + this.userName
                + this.birthDate + this.roleId;
        return s.length();
    }
    public int getCharacCount(){
        String s = this.userId + this.userEmail + this.userPasswd + this.userName
                + this.birthDate + this.roleId;
        return s.length();
    }

    public String toString() {
        return "User id:" + this.userId
                + " email Address: " + this.userEmail
                + " password: " + this.userPasswd
                + " password2: " + this.userPasswd2
                + " userName: " + this.userName
                + " BirthDate: " + this.birthDate
                + " Role Id: " + this.roleId;

    }
}
