package modelo;

import config.Conexion;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tomas florio
 */
public class UsersDAO {
    Connection conexion;
    public UsersDAO(){
        Conexion con = new Conexion();
        conexion = con.getConection();
    }
    public boolean ingresarUser(String email, String passw){
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps=conexion.prepareStatement("SELECT * FROM usuarios WHERE email=?");
            ps.setString(1,email);
            rs = ps.executeQuery();
            while(rs.next()){
                return email.equals(rs.getString("email"))&& passw.equals(rs.getString("password"));
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }  
    }
    public boolean registerUser(Usuario user){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("INSERT INTO usuarios (nombre,apellido,email,passw) VALUES (?,?,?,?)");
            ps.setString(1,user.getNombre());
            ps.setString(2,user.getApellido());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getPassw());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
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
