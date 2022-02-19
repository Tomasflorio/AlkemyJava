package config;

/**
 *
 * @author tomas florio
 */

import java.sql.*;

public class Conexion {
    public String driver = "com.mysql.cj.jdbc.Driver";
    public Connection getConection(){
        Connection c = null;
        try{
            Class.forName(driver);
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/allkemy_java",
                    "root"," ");
        }catch(ClassNotFoundException | SQLException error){
            System.out.println(error.toString());
        }
        return c;
    }
    /*public static void main(String[] lelis) throws SQLException{
        Connection conexion = null;
        Conexion con = new Conexion();
        conexion = con.getConection();
        
        PreparedStatement ps;
        ResultSet rs;
        ps = conexion.prepareStatement("SELECT * FROM participantes");
        rs = ps.executeQuery();
        while(rs.next()){
          int id = rs.getInt("id");
          String nombre = rs.getString("nombres");
          String apellidos = rs.getString("apellidos");
          String email = rs.getString("email");
          int telefono = rs.getInt("telefono");
            System.out.println("Id: "+ id + "\n Nombre: "+ nombre+
                    "\n Apellido: "+ "\n Email: "+email + "\n Telefono: "
                    +telefono);
        }
        
        
    }*/
}