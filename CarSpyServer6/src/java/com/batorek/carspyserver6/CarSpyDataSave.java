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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
//import javax.sql.*;

/**
 *
 * @author Bartek
 */
public class CarSpyDataSave extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CarSpyDataSave</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarSpyDataSave at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */

            String uid = request.getParameter("uid");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String lat = request.getParameter("lat");
            String lat_d = request.getParameter("lat_d");
            String lon = request.getParameter("lon");
            String lon_d = request.getParameter("lon_d");

            uid = checkLength(uid, 20);
            date = checkLength(date, 6);
            time = checkLength(time, 6);
            lat = checkLength(lat, 9);
            lat_d = checkLength(lat_d, 1);
            lon = checkLength(lon, 10);
            lon_d = checkLength(lon_d, 1);
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarSpyDataSave.class.getName()).log(Level.SEVERE, null, ex);
                out.println("nie ma jdbc");
            }

            boolean result = false;
            try {
                result = saveData(uid, date, time, lat, lat_d, lon, lon_d);
            } catch (SQLException ex) {
                Logger.getLogger(CarSpyDataSave.class.getName()).log(Level.SEVERE, null, ex);
            }

            


            if (result == true) {
                out.println("OK");
            }else {
                out.println("FAIL");
            }            

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
        return "CarSpyServer JavaEE 6";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Method: saveData(uuid, date, time, lat, lat_d, lon, lon_d)">
    public boolean saveData(String uid, String date, String time, String lat, String lat_d, String lon, String lon_d) throws SQLException {
        boolean result = false;
        Connection dbConn;

        dbConn = DriverManager.getConnection("jdbc:postgresql://" + DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);
        Statement stat = dbConn.createStatement();

        stat.executeUpdate("INSERT INTO " + DB_TABLE_NAME + " VALUES(default, '" + uid + "', '" + date + "', '" + time + "', '" + lat + "', '" + lat_d + "', '" + lon + "', '" + lon_d + "')");

        dbConn.close();

        result = true;
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Method: checkLength()">
    public String checkLength(String str, int len) {

        if (str.length() > len) {
            str = str.substring(0, len);
        }

        return str;
    }
    // </editor-fold>



}
