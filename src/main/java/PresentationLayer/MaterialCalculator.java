package PresentationLayer;

import FacadeLayer.MaterialFacade;
import Model.Material;

import java.io.IOException;
import java.util.ArrayList;
import javax.activation.MimeType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet( name = "MaterialCalculator", urlPatterns = { "/MaterialCalculator" } )
public class MaterialCalculator extends HttpServlet {



    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String destination = "index.jsp";

        String source = request.getParameter("source");
        HttpSession session = request.getSession();


        ArrayList<Material> materialSession;
        materialSession = (ArrayList<Material>) session.getAttribute("materials");
        if(materialSession == null){
            materialSession = new ArrayList<>();
        }



        switch(source){

            case "bygcarport":

                ArrayList<Material> materials = MaterialFacade.getMaterials();

                // hent parametre her

                for (int i = 0; i < materials.size(); i++) {

                    System.out.println("Looper igennem antal gange: " + i);

                    String productName = materials.get(i).getProduct_name();
                    materialSession.add(new Material(0, productName, "Dette er et material", 0));
                    session.setAttribute("materials", materialSession);

                }



                destination = "bestilling.jsp";

                break;
        }

        request.getRequestDispatcher(destination).forward(request,response);
    }



    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String destination = "index.jsp";


        request.getRequestDispatcher(destination).forward(request,response);
    }
}
