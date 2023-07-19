import java.sql.*;
import java.util.ArrayList;

public class UserQuery {
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_2_ica?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASS = "chen0402";
    private String username = null;
    private String password = null;

    public UserQuery(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    private boolean isUserExist(){
        boolean isExisted = false;
        Connection connection = null;
        Statement statement = null;
        ArrayList<String> usernames = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                usernames.add(rs.getString("username"));
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (usernames.indexOf(this.username) != -1){
            isExisted = true;
        }
        else {
            isExisted = false;
        }
        return isExisted;
    }
    private boolean isPasswordCorrect(){
        boolean isCorrect = false;
        Connection connection = null;
        Statement statement = null;
        String password = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM account WHERE username='" +  this.username   + "'";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                password = rs.getString("password");
            }
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        isCorrect = (password.equals(this.password));

        return isCorrect;
    }
    private void writeNewUser(){
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
            String sql = "insert into account value(\"" + this.username + "\", \"" + this.password + "\")";
            statement.executeUpdate(sql);
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String register(){
        String message = null;
        boolean isUserExist = isUserExist();
        if (isUserExist){
            message = "Register Failed: Existed user name";
        }
        else {
            writeNewUser();
            message = "Register success: " + this.username;
        }
        return message;
    }
    public int login(){
        int message = 0;
        boolean isUserExist = isUserExist();
        if (isUserExist){
            boolean isPasswordCorrect = isPasswordCorrect();
            if (isPasswordCorrect){
                message = 1; // Login success
            }
            else {
                message = -2; // Login failed: Password incorrect
            }
        }
        else {
            message = -1; // Login failed: User not found
        }
        return message;
    }
}
