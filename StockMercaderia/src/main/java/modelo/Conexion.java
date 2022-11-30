package modelo;

import static java.lang.Class.forName;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

public class Conexion {
    public Connection con;
    // Declaramos los datos de conexion a la bd
    //private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="root";
    private static final String pass="root";
    private static final String url="jdbc:mysql://localhost:3306/Mercaderia";
    
    public Connection getConnection(){
        // Reseteamos a null la conexion a la bd
        
        try{
            //Class.forName(driver);
            // Nos conectamos a la bd
            con= (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con!=null){
                System.out.println("conectado");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        
        
        catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        return con;
        
       
    }
    public Connection cerrarConexion(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }   
        con=null;
        return con;
      }
    
    
}