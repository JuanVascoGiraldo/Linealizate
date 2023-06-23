
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
//        url = "jdbc:mysql://containers-us-west-161.railway.app:7080/railway";
//        userName = "root";
//        password = "ilBsQ6YIYU9RDWLA5zPV";
        /*url = "jdbc:mysql://localhost:3306/BDalgebra";
        userName = "root";
        password = "03042021";*/
        //url = "jdbc:mysql://root:ilBsQ6YIYU9RDWLA5zPV@containers-us-west-161.railway.app:7080/railway";
        Connection con = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties props = new Properties();
            props.setProperty("user", userName);
            props.setProperty("password", password);
            props.setProperty("authenticationPlugin", "caching_sha2_password");
            con = DriverManager.getConnection(url, props);
            //con = DriverManager.getConnection(url);
            
        
        }catch(Exception e){
            System.out.println("Error al conectar la BD");
            System.out.println(e.getMessage());
        }
        return con;
    }
}
