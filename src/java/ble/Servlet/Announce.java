/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Max
 */
public class Announce  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        
        try {
            Sessions sess = new Sessions();
            
            response.setContentType("text/html;charset=UTF-8");
            String ann = request.getParameter("ann");
            String type = request.getParameter("stat");
            String id = sess.getSession(request, response, "id");
            Statement stmt;
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://localhost/ble", "root", "");
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO announcements VALUES(null, '"+id+"', '"+ann+"', 'open', '"+type+"')");
            
            conn.close();
            RequestDispatcher rd = request.getRequestDispatcher("/yourannouncement.ble");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
