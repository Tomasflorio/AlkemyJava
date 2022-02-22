package controlador;

import java.awt.Image;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Personaje;
import modelo.PersonajeDAO;

/**
 *
 * @author tomas florio
 */
@WebServlet(name = "PersonajeController", urlPatterns = {"/PersonajeController"})
public class PersonajeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PersonajeDAO personajeDAO = new PersonajeDAO();
        String accion;
        RequestDispatcher dispatcher;
        dispatcher = null;
        accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("/");
        } else if (accion.equals("modificar")) {
            dispatcher = request.getRequestDispatcher("Vistas/modificar.jsp");
        } else if (accion.equals("actualizar")) {
            int id_per = Integer.parseInt(request.getParameter("id_per")); //fijarse que este bien el id
            String nombre = request.getParameter("nombre");
            String historia = request.getParameter("historia");
            int edad = Integer.parseInt(request.getParameter("edad"));
            int peso = Integer.parseInt(request.getParameter("peso"));
            int id_pelicula = Integer.parseInt(request.getParameter("pelaso"));//fijarse que este bien 
            Image imagen = (Image) request.getAttribute("imagen");
            Personaje perso = new Personaje(id_per, nombre, edad, peso, historia, id_pelicula, imagen);
            personajeDAO.actualizarPersonaje(perso);
            dispatcher = request.getRequestDispatcher("Vistas/character.jsp");
        } else if (accion.equals("nuevo")) {
            dispatcher = request.getRequestDispatcher("Vistas/nuevo.jsp");
        } else if (accion.equals("insert")) {
            int id_per = Integer.parseInt(request.getParameter("id_per")); //fijarse que este bien el id
            String nombre = request.getParameter("nombre");
            String historia = request.getParameter("historia");
            int edad = Integer.parseInt(request.getParameter("edad"));
            int peso = Integer.parseInt(request.getParameter("peso"));
            int id_pelicula = Integer.parseInt(request.getParameter("pelaso"));//fijarse que este bien 
            Image imagen = (Image) request.getAttribute("imagen");
            Personaje perso = new Personaje(id_per, nombre, edad, peso, historia, id_pelicula, imagen);
            personajeDAO.insertarPersonaje(perso);
            dispatcher = request.getRequestDispatcher("Vistas/character.jsp");
        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));// fijarse nombre id
            personajeDAO.eliminarPersonaje(id);
            dispatcher = request.getRequestDispatcher("Vistas/characters.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/index.html");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
