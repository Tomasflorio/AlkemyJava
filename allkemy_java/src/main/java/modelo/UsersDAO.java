package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    
}
