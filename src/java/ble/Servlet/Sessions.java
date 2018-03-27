/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shellygobui
 */
public class Sessions {

    public String getSession(HttpServletRequest request, HttpServletResponse response, String var)
            throws ServletException, IOException {
      //  response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if(session!=null){
        return session.getAttribute(var).toString();
        } else {
            return null;
        }
    }
    public void removeSession(HttpServletRequest request, HttpServletResponse response, String var)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();  
        session.invalidate();  
    }
}
