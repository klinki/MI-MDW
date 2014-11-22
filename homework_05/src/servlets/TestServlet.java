/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import cz.fit.cvut.mdw.IBankAccountService;
import cz.fit.cvut.mdw.IBankAccountTransfer;
import cz.fit.cvut.mdw.clients.BankAccountServiceClient;
import cz.fit.cvut.mdw.clients.BankAccountTransferClient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author david
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

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
            
            IBankAccountTransfer transfer = new BankAccountTransferClient();
            IBankAccountService service = new BankAccountServiceClient();
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");

            String invalid = "0000000";
            String first = "0000001";
            String second = "0000002";
            
            out.println("<p>Initial state: </p>");
            out.println("<p>Account 1: number -  <strong>0000001</strong>, balance: <strong>0</strong></p>");
            out.println("<p>Account 2: number -  <strong>0000002</strong>, balance: <strong>5000</strong></p>");
            
            out.println("<p>-----------------------------------------</p>");
                        
            out.println("<p>Validating existing account number 0000001: <strong>" + service.accountExists(first) + "</strong></p>");
            out.println("<p>Validating existing account number 0000002: <strong>" + service.accountExists(second) + "</strong></p>");
            out.println("<p>Validating not-existing account number 0000000: <strong>" + service.accountExists(invalid) + "</strong></p>");
            
            out.println("<p>Checking balance of acc. 0000001, balance = 100? : <strong>" + service.validateBalance(first, 100) + "</strong></p>");
            out.println("<p>Checking balance of acc. 0000001, balance = 0? : <strong>" + service.validateBalance(first, 0) + "</strong></p>");
            out.println("<p>Checking balance of acc. 0000002, balance = 5000? : <strong>" + service.validateBalance(second, 5000) + "</strong></p>");
            
            out.println("<p>Transfer 100 from invalid acc. 0000000 to 0000001: <strong>" + transfer.transfer(invalid, first, 100) + "</strong></p>");
            out.println("<p>Transfer 100 from  0000001 to 0000002: <strong>" +  transfer.transfer(first, second, 100) + "</strong></p>");
            out.println("<p>Transfer 500 from  0000002 to 0000001: <strong>" +  transfer.transfer(second, first, 500) + "</strong></p>");
            out.println("<p>Checking balance of acc. 0000001, balance = 500? : <strong>" + service.validateBalance(first, 500) + "</strong></p>");
            out.println("<p>Checking balance of acc. 0000002, balance = 4500? : <strong>" + service.validateBalance(second, 4500) + "</strong></p>");           
            out.println("<p>Checking balance of acc. 0000002, balance = 5000? : <strong>" + service.validateBalance(second, 5000) + "</strong></p>");           

            out.println("<p>New balance of acc. 0000001: <strong>" + service.getBalance(first) + "</strong></p>");           
            out.println("<p>New balance of acc. 0000002: <strong>" + service.getBalance(second) + "</strong></p>");           

            out.println("</body>");
            out.println("</html>");
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
