package modelo;

import config.Conexion;
import java.awt.Image;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas florio
 */
public class PersonajeDAO {
        Connection conexion;
    public PersonajeDAO(){
        Conexion con = new Conexion();
        conexion = con.getConection();
    }
    public List<Personaje> listarPesonajes(){
        PreparedStatement ps;
        ResultSet rs;
        List<Personaje> lista = new ArrayList<>();
        try{
            ps = conexion.prepareStatement("SELECT * FROM personajes");
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_per");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int peso = rs.getInt("peso");
                String historia = rs.getString("historia");
                int pel = rs.getInt("pelicula_serie");
                Image imagen = (Image) rs.getBlob("imagen");
                Personaje personaje = new Personaje(id, nombre, edad,  peso,  historia,pel,imagen);
                lista.add(personaje);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
        
    }
    public Personaje mostrarPersonaje(int _id){
       PreparedStatement ps;
       ResultSet rs;
       Personaje personaje = null;
       
       try{
            ps = conexion.prepareStatement("SELECT * FROM "
                    + "personajes WHERE id=?");
            ps.setInt(1,_id);
           rs = ps.executeQuery();
           while(rs.next()){
               int id = rs.getInt("id_per");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                int peso = rs.getInt("peso");
                String historia = rs.getString("historia");
                int pel = rs.getInt("pelicula_serie");
                Image imagen = (Image) rs.getBlob("imagen");
               
               personaje = new Personaje(id, nombre, edad,  peso,  historia,pel,imagen);
           } return personaje;
           
       }catch(SQLException e){
           System.out.println(e.toString());
           return null;
       }
       
    }
     public boolean insertarPersonaje(Personaje personaje){
        PreparedStatement ps;
        try{
             ps = conexion.prepareStatement("INSERT INTO personaje (nombre,edad,peso,historia,pelicula_serie,imagen) VALUES (?,?,?,?,?,?)");
             ps.setString(1,personaje.getNombre());
             ps.setInt(2,personaje.getEdad());
             ps.setInt(3,personaje.getPeso());
             ps.setString(4, personaje.getHistoria());
             ps.setInt(5, personaje.getId_pelicula());
             ps.setBlob(6, (Blob) personaje.getImagen());
             ps.execute();
             return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        
        
    }
    public boolean actualizarPersonaje(Personaje personaje){
        PreparedStatement ps;
        try{
             ps = conexion.prepareStatement("UPDATE personaje SET nombre=?, edad=?, peso=?, historia=?, pelicula_serie=?,imagen=? WHERE id=?");
             ps.setString(1,personaje.getNombre());
             ps.setInt(2,personaje.getEdad());
             ps.setInt(3,personaje.getPeso());
             ps.setString(4, personaje.getHistoria());
             ps.setInt(5, personaje.getId_pelicula());
             ps.setBlob(6, (Blob) personaje.getImagen());
             ps.execute();
             return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean eliminarPersonaje(int _id){
        PreparedStatement ps;
          try{
            ps = conexion.prepareStatement("DELETE FROM personajes WHERE id=?");
            ps.setInt(1,_id);
            ps.execute();
            return true;
          }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
}
