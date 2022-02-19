package controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.UsersDAO;

/**
 *
 * @author tomas
 */

@WebServlet(name="UsuariosController",urlPatterns={"/UsuariosController"}) 
public class UsuariosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException , IOException{
        UsersDAO userDAO = new UsersDAO();
        String accion;
        RequestDispatcher dispatcher = null;
        accion = request.getParameter("accion");
        if(accion == null || accion.isEmpty()){
            dispatcher = request.getRequestDispatcher("/");
        }else if(accion.equals("crear")){
            dispatcher = request.getRequestDispatcher("Vistas/crearUser.jsp");
        }else if(accion.equals("ingresar")){
            String usuario = request.getParameter("email");
            String passw = request.getParameter("passw");
            boolean ingresar = userDAO.ingresarUser(usuario,passw);
            if(ingresar){
                dispatcher = request.getRequestDispatcher("Vistas/characters.jsp");
            }else{
                dispatcher = request.getRequestDispatcher("/index.html");
            }
        }
        dispatcher.forward(request, response);
                
    }
    
}
