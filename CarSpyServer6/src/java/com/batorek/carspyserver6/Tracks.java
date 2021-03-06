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
public class Tracks extends HttpServlet {

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
            out.println("<title>Servlet Tracks</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Tracks at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            
            String[] uids, dates, times;

            Connection dbConn;
            dbConn = DriverManager.getConnection("jdbc:postgresql://" + DB_URL + "/" + DB_NAME, DB_USER, DB_PASS);

            Statement stat = dbConn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT COUNT(DISTINCT uid) FROM locations"); //ilość tras na podstawie unikalnych uid

            rs.next();

            uids = new String[rs.getInt(1)];
            dates = new String[rs.getInt(1)];
            times = new String[rs.getInt(1)];

            rs = stat.executeQuery("SELECT DISTINCT uid FROM locations");

            while (rs.next()) {
                uids[rs.getRow()-1] = rs.getString("uid");
            }

            for (int i=0; i <uids.length; i++) {
                rs = stat.executeQuery("SELECT date, \"time\" FROM locations WHERE uid='" + uids[i] + "' ORDER BY id");

                rs.next();
                dates[i] = rs.getString("date");
                times[i] = rs.getString("time");
                //pobieramy pierwszy rekord, czyli pierwsza date i czas danej trasy
                dates[i] = dates[i].substring(0, 2) + "-" + dates[i].substring(2, 4) + "-" + dates[i].substring(4, 6);
                times[i] = times[i].substring(0, 2) + ":" + times[i].substring(2, 4) + ":" + times[i].substring(4, 6);
                //formatujemy na DD-MM-YY i HH:MM:SS
            }

            rs.close();
            stat.close();
            dbConn.close();

            //generujemy HTML

            out.println("<html>");
            out.println("<head>");
            out.println("<title>CarSpy: wybór trasy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Wybierz trasę:</h1><br>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>Identyfikator</th>");
            out.println("<th>Data</th>");
            out.println("<th>Godzina</th>");
            out.println("<th>Usuń</th>");
            out.println("</tr>");

            for (int i=0; i < uids.length; i++) {
                out.println("<tr>");
                out.print("<td>"); out.print("<a href=\"map.jsp?uid="); out.print(uids[i]); out.print("\">"); out.print(uids[i]); out.print("</a>"); out.print("</td>");
                out.print("<td>"); out.print(dates[i]); out.println("</td>");
                out.print("<td>"); out.print(times[i]); out.println("</td>");
                out.print("<td>"); out.print("<a href=\"Delete.do?uid="); out.print(uids[i]); out.print("\">"); out.print("Usuń"); out.print("</a>"); out.print("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br>");
            out.println("<a href=\"index.jsp\">Wróć</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            Logger.getLogger(Tracks.class.getName()).log(Level.SEVERE, null, ex);
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
