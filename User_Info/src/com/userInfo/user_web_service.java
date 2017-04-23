/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soap;

import com.mysql.MySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ISL
 */
@WebService(serviceName = "user_web_service")
public class user_web_service {
    private Connection con;
    private Statement st;
    private String username;

    /* Add new user */
    @WebMethod
    public String add_User(@WebParam(name="username") String username,
                        @WebParam(name="password") String password) {
        try {
            MySQL sql = new MySQL();
            this.con = sql.createConnection();
            this.st = con.createStatement();
            
            // check username exists or not
            String query_check_username = "select password from login where username='" + username + "'";
            ResultSet rs = st.executeQuery(query_check_username);
            if(rs.next()) {
                sql.closeDB();
                return "User name exists";
            }
            else {
                // add new use
                String query = "insert into user_information.login (username, password)  values ('" + username + "', '" + password + "');";
                st.executeUpdate(query);
                sql.closeDB();
                return "Successful";
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_web_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
    
    /* Validate password */
    @WebMethod
    public boolean valid_User(@WebParam(name="username") String username,
                             @WebParam(name="password") String password) {
        this.username = username;
        String password_correct = "";
        try {
            MySQL sql = new MySQL();
            this.con = sql.createConnection();
            this.st = con.createStatement();
            String query = "select password from login where username='" + username + "'";
            ResultSet rs = st.executeQuery(query);

            rs.next();
            password_correct = rs.getString(1);
            
            sql.closeDB();
            
        } catch (SQLException ex) {
            Logger.getLogger(user_web_service.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(password.equals(password_correct))  
            return true;
        else 
            return false;
    }
    
    /* Add new favorite singer name*/
    @WebMethod
    public String add_Singername(@WebParam(name="username") String username,
                                       @WebParam(name="singername") String singername) {
         try {
            this.username = username;
            MySQL sql = new MySQL();
            this.con = sql.createConnection();
            this.st = con.createStatement();
            
            // check singer name exists or not
            String query_check_singername = "select singername from favorite_singer where username='" + username + "'";
            ResultSet rs = st.executeQuery(query_check_singername);
            if(rs.next()) {
                sql.closeDB();
                return "Singer name is already added";
            } else {
                // add new singer name
                String query = "insert into user_information.favorite_singer (username, singername)  values ('" + username + "', '" + singername + "');";
                st.executeUpdate(query);
                sql.closeDB();
                return "Successful";
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_web_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Error";
    }
    
    /* Get favorite singer names */
    @WebMethod
    public String favorite_Singername(@WebParam(name="username") String username) {
        String singernames = "";
        try {
            this.username = username;
            MySQL sql = new MySQL();
            this.con = sql.createConnection();
            this.st = con.createStatement();
            String query = "select singername from favorite_singer where UserName='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()) {
                singernames += rs.getString(1) + "\n";
            }
            sql.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(user_web_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return singernames;
    }
}

