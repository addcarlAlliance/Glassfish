/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ble.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *x
 * @author addcarl
 */
public class Servlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("SERVER RUNNING!!");
    }
}
