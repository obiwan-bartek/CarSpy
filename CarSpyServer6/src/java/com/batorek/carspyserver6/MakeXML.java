/*
 *
 * Copyright (c) 2014 Bartosz Bątorek
 * Distributed under the MIT License
 * See LICENSE.txt for further information.
 *
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
public class MakeXML extends HttpServlet {

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
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MakeXML</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MakeXML at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */

            String uid = request.getParameter("uid");

            String[] lats, lons;


            Connection dbConn;
            dbConn = DriverManager.getConnection("jdbc:postgresql://" + DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);
      
            Statement stat = dbConn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT COUNT(*) FROM locations WHERE uid='" + uid + "'");

            rs.next();
            lats = new String[rs.getInt(1)];
            lons = new String[rs.getInt(1)];

            rs = stat.executeQuery("SELECT lat, lat_d, lon, lon_d FROM locations WHERE uid='" + uid + "'");

            while (rs.next()) {

                if (rs.getString("lat_d").equals("S")) {
                    lats[rs.getRow()-1] = "-" + rs.getString("lat");
                }else {
                    lats[rs.getRow()-1] = rs.getString("lat");
                }

                if (rs.getString("lon_d").equals("W")) {
                    lons[rs.getRow()-1] = "-" + rs.getString("lon");
                }else {
                    lons[rs.getRow()-1] = rs.getString("lon");
                }
            }

            rs.close();
            stat.close();
            dbConn.close();

            out.println("<dane>");
            out.println("<trasa>");

            for (int i = 0; i < lats.length; i++) {
                out.print("<punkt lat=\"");
                out.print(lats[i]);
                out.print("\" lon=\"");
                out.print(lons[i]);
                out.println("\"/>");
            }

            out.println("</trasa>");
            out.println("</dane>");



        } catch (SQLException ex) {
            Logger.getLogger(MakeXML.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.flush();
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
