/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.batorek.carspyserver6;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bartek
 */
public class Delete extends HttpServlet {

    final static String DB_URL = "127.0.0.1:5432";
    final static String DB_NAME = "carspy_db";
    final static String DB_USER = "postgres";
    final static String DB_PASS = "postgres";
    final static String DB_TABLE_NAME = "locations";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarSpyDataSave.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Delete</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Delete at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */

            String uid = request.getParameter("uid");

            Connection dbConn;
            dbConn = DriverManager.getConnection("jdbc:postgresql://" + DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);

            Statement stat = dbConn.createStatement();

            stat.executeUpdate("DELETE FROM locations WHERE uid='"+ uid + "'");

            out.println("<html>");
            out.println("<body>");
            out.println("<h2>Usunięto</h2>");
            out.println("<br>");
            out.println("<a href=\"Tracks.do\">Wróć</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
