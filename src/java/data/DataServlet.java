/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public class DataServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DataServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    //WHAT EVER FRAMEWORK IS BEING USED THE INITAL CALL SHOULD INITATE THE DATASOURCE
    //THIS IS HOW IT'S DONE USING A SERVLET SO JUST CHANGE IT TO WHAT WE ARE USING
    //THEN GET THE RFERENCE WHEN WE ARE DOING OUR CRUD SINCE THIS IS ALL WE WILL BE COVERING
        @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        Context ctx; 
        try {
            ctx = new InitialContext();
       
            DataSource ds = (DataSource)ctx.lookup("jdbc/WildlifeDatasource"); 
            Connection connection = ds.getConnection(); 
            ServletContext sct = config.getServletContext();
            sct.setAttribute("Connection", connection);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(DataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*SAMPLE OF SOME QUERIES
                            Connection connection = (Connection)getServletContext().getAttribute("Connection");
                    if(connection != null){
                    Statement stmt = connection.createStatement();
                    String sql = "Select * From CUSTOMERTB where CUSTOMERID =" +customerId;
                    ResultSet res = stmt.executeQuery(sql);
                    if(res.next()){
                        String insertsql = "INSERT into ORDERS(CUSTOMERID,PSIZE, TOPPING) VALUES "
                                + "("+customerId+","+size+",'"+formatToppings(topping)+"')";
                        stmt.executeUpdate(insertsql);
                    }else{
                        String insertCustomer = "INSERT INTO CUSTOMERTB (CUSTOMERID, CUSTOMERNAME) VALUES"
                                + "("+customerId+",'"+customerName+"')";
                        stmt.executeUpdate(insertCustomer);
                        String insertsql = "INSERT into ORDERS(CUSTOMERID,PSIZE, TOPPING) VALUES "
                                + "("+customerId+","+size+",'"+formatToppings(topping)+"')";
                        stmt.executeUpdate(insertsql);
                    
        */

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
            throws ServletException, IOException {
        processRequest(request, response);
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
