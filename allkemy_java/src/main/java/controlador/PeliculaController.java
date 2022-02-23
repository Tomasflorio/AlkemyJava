/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Pelicula;
import modelo.PeliculaDAO;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author tflorio
 */
@WebServlet(name = "PeliculaController", urlPatterns = {"/PeliculaController"})
public class PeliculaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        String accion;
        RequestDispatcher dispatcher;
        dispatcher = null;
        accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("/");
        }else if(accion.equals("modificarpel")){
            dispatcher = request.getRequestDispatcher("Vistas/modificarpel.jsp");         
        }else if(accion.equals("actualizarpel")){
            try {
                int idpel = Integer.parseInt(request.getParameter("id_per")); //fijarse que este bien el id
                String titulo = request.getParameter("nombre");
                String feha = request.getParameter("fecha");
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(feha);
                int calif = Integer.parseInt(request.getParameter("edad"));
                int idper = Integer.parseInt(request.getParameter("idper"));
                Image imagen = (Image) request.getAttribute("imagen");
                Pelicula pelicula = new Pelicula(idpel, titulo, fecha, calif, idper, imagen);
                peliculaDAO.actualizarPelicula(pelicula);
                dispatcher = request.getRequestDispatcher("Vistas/pelicula.jsp");
            } catch (ParseException ex) {
                System.out.println(ex.toString());
            }
        } else if(accion.equals("nuevapel")){
            dispatcher = request.getRequestDispatcher("Vistas/nuevopel.jsp");
        } else if(accion.equals("insertpel")){
            try {
                int idpel = Integer.parseInt(request.getParameter("id_per")); //fijarse que este bien el id
                String titulo = request.getParameter("nombre");
                String feha = request.getParameter("fecha");
                Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(feha);
                int calif = Integer.parseInt(request.getParameter("edad"));
                int idper = Integer.parseInt(request.getParameter("idper"));
                Image imagen = (Image) request.getAttribute("imagen");
                Pelicula pelicula = new Pelicula(idpel, titulo, fecha, calif, idper, imagen);
                peliculaDAO.insertarPelicula(pelicula);
                dispatcher = request.getRequestDispatcher("Vistas/pelicula.jsp");
            } catch (ParseException ex) {
                System.out.println(ex.toString());
            }
        } else if(accion.equals("eliminarpel")){
            int idpel = Integer.parseInt(request.getParameter("id_per")); //fijarse que este bien el id
            peliculaDAO.eliminarPelicula(idpel);
            dispatcher = request.getRequestDispatcher("Vistas/pelicula.jsp");
        }else{
            dispatcher = request.getRequestDispatcher("/");
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
