/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import DBAccess.CustomerMapper;
import Exceptions.LoginSampleException;
import FunctionLayer.SVG;
import Model.Customer;
import Model.User;

import java.io.IOException;
import java.util.ArrayList;
import javax.activation.MimeType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**

 @author kasper
 */
@WebServlet( name = "FrontController", urlPatterns = { "/FrontController" } )
public class FrontController extends HttpServlet {


    /**
     Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     methods.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            Command action = Command.from( request );
            String view = action.execute( request, response );
            request.getRequestDispatcher( "/WEB-INF/" + view + ".jsp" ).forward( request, response );
        } catch ( LoginSampleException ex ) {
            request.setAttribute( "error", ex.getMessage() );
            request.getRequestDispatcher( "fejl.jsp" ).forward( request, response );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     Handles the HTTP <code>GET</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String destination = "index.jsp";

        String source = request.getParameter("source");
        HttpSession session = request.getSession();


        switch(source){

            case "profil":
                destination = "/WEB-INF/brugerside.jsp";
                break;

            case "logout":
                session.removeAttribute("login");

                destination = "/index.jsp";
                break;
            case "admin":
                int role = 0;

                ArrayList<Customer> login = (ArrayList<Customer>) session.getAttribute("login");
                role = login.get(0).getRole();

                if (login != null && role == 1) {
                    destination = "/WEB-INF/admin.jsp";
                } else {
                    destination = "index.jsp";
                }
                break;

        }

        request.getRequestDispatcher(destination).forward(request,response);
    }

    /**
     Handles the HTTP <code>POST</code> method.

     @param request servlet request
     @param response servlet response
     @throws ServletException if a servlet-specific error occurs
     @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {


        String destination = "index.jsp";
        String source = request.getParameter("source");

        HttpSession session = request.getSession();


        ArrayList<Customer> customer;
        customer = (ArrayList<Customer>) session.getAttribute("login");
        if(customer == null){
            customer = new ArrayList<>();
        }


        switch(source){

            case "login":

                ArrayList<Customer> customerList = FacadeLayer.KundeFacade.getKunderList();

                String email = request.getParameter("email");
                String password = request.getParameter("password");


                for (int i = 0; i < customerList.size(); i++) {
                    if(customerList.get(i).getEmail().equals(email) && customerList.get(i).getPassword().equals(password)){

                        int customer_id = customerList.get(i).getCustomer_id();
                        String name = customerList.get(i).getName();
                        String phone = customerList.get(i).getPhone();
                        String address = customerList.get(i).getAdress();
                        String zipcode = customerList.get(i).getZipcode();
                        String city = customerList.get(i).getCity();
                        int role = customerList.get(i).getRole();
                        customer.add(new Customer(customer_id, name, email, password, phone, address, zipcode, city, role));
                        session.setAttribute("login", customer);
                    }

                }
                destination = "/WEB-INF/brugerside.jsp";
                break;

            case "register":

                String customer_name = request.getParameter("name");
                String customer_email = request.getParameter("email");
                String customer_password = request.getParameter("password");
                String customer_phone = request.getParameter("phone");
                String customer_address = request.getParameter("address");
                String customer_zipcode = request.getParameter("postnr");
                String customer_city = request.getParameter("by");

                Customer createCustomer = new Customer(customer_name, customer_email, customer_password, customer_phone, customer_address, customer_zipcode, customer_city, 0);

                try {
                    CustomerMapper.createCustomer(createCustomer);

                    destination = "/login.jsp";

                } catch (LoginSampleException ex) {
                    ex.printStackTrace();
                }

                break;

            case "generate_SVG":

                int width = Integer.parseInt(request.getParameter("width"));
                int length = Integer.parseInt(request.getParameter("length"));
                SVG svg = new SVG();

                session.setAttribute("svg", svg.createSVG(width,length));

                destination = "printDrawing.jsp";
                break;

            case "bygcarport":
                destination = "bestilling.jsp";
                break;

        }




        request.getRequestDispatcher(destination).forward(request,response);

//        processRequest( request, response );

    }

    /**
     Returns a short description of the servlet.

     @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
