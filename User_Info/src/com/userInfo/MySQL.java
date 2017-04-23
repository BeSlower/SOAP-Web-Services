package com.userInfo;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Zhan Shi
 */
public class MySQL {
    private Connection con;
       
    public Connection createConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/user_information","zhans","123");
        //here sonoo is database name, root is username and password
        } catch(Exception e) { System.out.println(e);}
        this.con = con;
        return con;
    }
    
    public void closeDB(){
        try {
            this.con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}