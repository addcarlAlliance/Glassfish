/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ble.Servlet.Sessions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author shellygobui
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Sessions sess = new Sessions();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            
        }finally{
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");
            Sessions sess = new Sessions();
            Statement stmt;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            String sql;
            ResultSet rs = null;
            
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/ble", "root", "");
                stmt = conn.createStatement();
                //    sql = "SELECT * FROM users WHERE idNumber = '" + uname + "' AND password IS "
                rs = stmt.executeQuery("SELECT * FROM users WHERE idNumber = '" + uname + "' AND password = '" + pass + "'");
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(rs != null && rs.next()) {
                //if((uname.equalsIgnoreCase("stud") && pass.equalsIgnoreCase("stud")) || (uname.equalsIgnoreCase("teacher") && pass.equalsIgnoreCase("teacher"))){
                HttpSession session =request.getSession(); //this is setsession
                rs.first();
                session.setAttribute("user", rs.getString("userType")); //this is setsession

                /*something wrong with calling a function:
                /sess.setSession(request, response, "user", uname);*/
                RequestDispatcher rd = request.getRequestDispatcher("Authentication");
                rd.forward(request, response);
            }else{
                RequestDispatcher rd =request.getRequestDispatcher("index.ble");
                rd.include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
