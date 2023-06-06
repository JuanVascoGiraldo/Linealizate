
package Control;

import java.sql.*;

public class ConexionBD {
    public static Connection getConnection(){
        String url, userName, password;
        
        /*url = "jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_b3b88c0337e26c9";
        userName = "b21ae80ea3e3cb";
        password = "f1eab042";*/
        url = "jdbc:mysql://localhost:3306/BDalgebra";
        userName = "root";
        password = "03042021";
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(url, userName, password);
            
            System.out.println("Conexion Exitosa con la BD");
        
        }catch(Exception e){
            System.out.println("Error al conectar la BD");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
