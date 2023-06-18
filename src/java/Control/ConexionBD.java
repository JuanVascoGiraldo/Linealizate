
package Control;

import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class ConexionBD {
    public static Connection getConnection(){
        String url, userName, password;
        Map<String, String> env = System.getenv();
        url = env.get("URL_BD");
        userName = env.get("usename_BD");
        password = env.get("password_BD");
        /*url = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_449a4cf964b0f2c";
        userName = "b56ecd52f2dffc";
        password = "6c53858a";*/
        /*url = "jdbc:mysql://localhost:3306/BDalgebra";
        userName = "root";
        password = "03042021";*/
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
