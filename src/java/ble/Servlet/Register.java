/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Max
 */
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            String uname = request.getParameter("id");
            String name = request.getParameter("name");
            String type = request.getParameter("designation");
            String course = request.getParameter("course");
            String pass = request.getParameter("password");
            Statement stmt;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://localhost/ble", "root", "");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO users VALUES(null, '"+uname+"', '"+pass+"', '"+name+"', '"+course+"', '"+type+"')");
            
            RequestDispatcher rd =request.getRequestDispatcher("index.ble");
            rd.include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
