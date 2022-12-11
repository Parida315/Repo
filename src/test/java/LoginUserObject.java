public class LoginUserObject {

    private String userName, passWord, userType,email;

    public LoginUserObject() {
    }

    public LoginUserObject(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public LoginUserObject(String userName, String passWord, String userType, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.userType = userType;
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
