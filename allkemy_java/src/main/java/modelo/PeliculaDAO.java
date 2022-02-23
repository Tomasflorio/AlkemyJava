package modelo;

import config.Conexion;
import java.awt.Image;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas florio
 */
public class PeliculaDAO {
    Connection conexion;
    public PeliculaDAO(){
        Conexion con = new Conexion();
        conexion = con.getConection();
    }
    public List<Pelicula> listarPelicula(){
       PreparedStatement ps;
       ResultSet rs;
       List<Pelicula> lista = new ArrayList<>();
       try{
           ps = conexion.prepareStatement("SELECT * FROM pelicula_serie");
           rs = ps.executeQuery();
           
           while(rs.next()){
               int idpel = rs.getInt("id_pel");
               String titulo = rs.getString("titulo");
               Date fecha = rs.getDate("fecha_cre"); 
               Integer calif = rs.getInt("calificacion");
               int idper = rs.getInt("perso_asoc");
               Image imagen = (Image) rs.getBlob("imagen");
               Pelicula alumnos = new Pelicula(idpel, titulo,fecha,calif,idper,imagen);
               lista.add(alumnos);
           }
           return lista;
           
       }catch(SQLException e){
           System.out.println(e.toString());
           return null;
       }
    }
    public Pelicula mostrarPelicula(int _id){
       PreparedStatement ps;
       ResultSet rs;
       Pelicula pelicula = null;
       
       try{
            ps = conexion.prepareStatement("SELECT * FROM "
                    + "pelicula_serie WHERE id=?");
            ps.setInt(1,_id);
           rs = ps.executeQuery();
           while(rs.next()){
               int idpel = rs.getInt("id_pel");
               String titulo = rs.getString("titulo");
               Date fecha = rs.getDate("fecha_cre"); 
               Integer calif = rs.getInt("calificacion");
               int idper = rs.getInt("perso_asoc");
               Image imagen = (Image) rs.getBlob("imagen");
              pelicula = new Pelicula(idpel, titulo,fecha,calif,idper,imagen);
           } return pelicula;
       }catch(SQLException e){
           System.out.println(e.toString());
           return null;
       }
       
    }
    public boolean insertarPelicula(Pelicula pelicula){
        PreparedStatement ps;
        try{
             ps = conexion.prepareStatement("INSERT INTO pelicula_serie (titulo,fecha_cre,calificacion,perso_asoc,imagen) VALUES (?,?,?,?,?)");
             ps.setString(1,pelicula.getTitulo());
             ps.setDate(2, (Date) pelicula.getFecha());
             ps.setInt(3,pelicula.getCalif());
             ps.setInt(4, pelicula.getIdper());
             ps.setBlob(5, (Blob) pelicula.getImagen());
             ps.execute();
             return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
        
    }
    public boolean actualizarPelicula(Pelicula pelicula){
        PreparedStatement ps;
        try{
             ps = conexion.prepareStatement("UPDATE pelicula_serie SET titulo=?, fecha_cre=?, calificacion=?, perso_asoc=?, imagen=? where id=?");
             ps.setString(1,pelicula.getTitulo());
             ps.setDate(2, (Date) pelicula.getFecha());
             ps.setInt(3,pelicula.getCalif());
             ps.setInt(4, pelicula.getIdper());
             ps.setBlob(5, (Blob) pelicula.getImagen());

             ps.execute();
             return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean eliminarPelicula(int _id){
        PreparedStatement ps;
          try{
            ps = conexion.prepareStatement("DELETE FROM pelicula WHERE id=?");
            ps.setInt(1,_id);
            ps.execute();
            return true;
          }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}
