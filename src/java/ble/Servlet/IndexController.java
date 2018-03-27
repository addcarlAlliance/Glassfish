package ble.Servlet;

import ble.BleDriver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Max
 */
public class IndexController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        
        InputStream is;
        BufferedReader bf;
        ServletContext context;
        List<String> lines;
        String path;
        String currline;
        String allLines;
        Sessions sess = new Sessions();
        
        context = request.getServletContext();
        path = request.getRequestURI().substring(request.getContextPath().length());
        
        System.out.println(path);
       
        if(path.equals("/") || path.equals("/Login")) {
            path = "/index.ble";
        }
        
        is = context.getResourceAsStream(path);
        
        if(is != null) {
            
            bf = new BufferedReader(new InputStreamReader(is));
          
            try (PrintWriter out = response.getWriter()) {
                lines = new ArrayList<>();
                while ((currline = bf.readLine()) != null) {
                    lines.add(currline);

                }
             
                String id = sess.getSession(request, response, "id");
                allLines = String.join("\n", lines);
                allLines = BleDriver.drive(allLines, path,id);
                out.println(allLines);
            }
            
        } else {
            PrintWriter out = response.getWriter();
            out.println("404 Page not found");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
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
