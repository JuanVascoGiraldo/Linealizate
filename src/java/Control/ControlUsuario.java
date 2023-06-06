
package Control;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ControlUsuario {
    
    public static boolean RegistrarEstudiante(Usuario usu){
        boolean resultado=false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            usu.setPassword(Cifrado.encrypt(String.valueOf(usu.getBoleta())));
            con = ConexionBD.getConnection();
            String sql = "select * from MUsuario where boleta_usu = ?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, usu.getBoleta());
            rs = ps.executeQuery();
            if(!rs.next()){
                sql = "insert into MUsuario (boleta_usu, nom_usu,rol_usu, contra_usu, correo_usu) values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setLong(1, usu.getBoleta());
                ps.setString(2, usu.getNombre());
                ps.setInt(3,3);
                ps.setString(4, usu.getPassword());
                ps.setString(5, usu.getEmail());
                int Estatus = ps.executeUpdate();
                if(Estatus > 0){
                    resultado= true;
                }
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        
        return resultado;
    }
    
    public static Usuario InisiarSesioon(long boleta, String pass){
        Usuario usu = new Usuario();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select * from MUsuario where boleta_usu = ? and contra_usu = ?";
            ps  = con.prepareStatement(sql);
            ps.setLong(1, boleta);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next()){
                usu.setId_cifrado(Cifrado.encrypt(String.valueOf(rs.getInt("id_usu"))));
                usu.setBoleta(boleta);
                usu.setEmail(rs.getString("correo_usu"));
                usu.setRol(rs.getInt("rol_usu"));
                usu.setNombre(rs.getString("nom_usu"));
            }else{
                usu.setNombre("no encontrado");
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            usu.setNombre("error");
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return usu;
    }
    
    public static Usuario RecuperarPassword(long boleta){
        Usuario usu = new Usuario();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select MUsuario.correo_usu MUsuario.id_usu from MUsuario where boleta_usu = ?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, boleta);
            rs = ps.executeQuery();
            if(rs.next()){
                usu.setEmail(rs.getString("MUsuario.correo_usu"));
                usu.setId(rs.getInt("MUsuario.id_usu"));
            }else{
                 usu.setEmail("NA");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            usu.setEmail("ERROR");
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return usu;
    }
    
    public static boolean CambiarPassword(int id, String password){
        boolean resultado = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "update MUsuario set contra_usu = ? where id_usu = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, Cifrado.encrypt(password));
            ps.setInt(2, id);
            int Estatus = ps.executeUpdate();
            if(Estatus > 0){
                resultado= true;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            resultado = false;
        }finally{
            try{
                con.close();
                ps.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return resultado;
    }
    
    public static boolean ModificarEstudiante(Usuario mod){
        boolean resultado = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "Select MUsuario.boleta_usu from MUsuario where id_usu = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.valueOf(Cifrado.decrypt(mod.getId_cifrado())));
            rs = ps.executeQuery();
            if(!rs.next()){//no existe el id a modificar
               return false;
            }
            if(rs.getLong("MUsuario.boleta_usu")==mod.getBoleta()){//no se va a modificar la boleta
                sql = "update MUsuario set nom_usu = ? , correo_usu = ? where id_usu = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, mod.getNombre());
                ps.setString(2, mod.getEmail());
                ps.setInt(3, Integer.valueOf(Cifrado.decrypt(mod.getId_cifrado())) );
                int estado = ps.executeUpdate();
                if(estado>0){
                    return true;
                }
            }else{//se va a modificar la boleta
                //tenemos que ver la boleta no exista ya
                sql = "select * from MUSuario where boleta_usu = ?";
                ps = con.prepareStatement(sql);
                ps.setLong(1, mod.getBoleta());
                rs = ps.executeQuery();
                if(!rs.next()){
                    sql = "update MUsuario set nom_usu = ? , correo_usu = ? , boleta_usu = ? where id_usu = ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, mod.getNombre());
                    ps.setString(2, mod.getEmail());
                    ps.setLong(3, mod.getBoleta());
                    ps.setInt(4, Integer.valueOf(Cifrado.decrypt(mod.getId_cifrado())) );
                    int estado = ps.executeUpdate();
                    if(estado>0){
                        return true;
                    }
                }else{
                    return false;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            resultado = false;
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return resultado;
    }
    
    public static List<Usuario> EncontrarEstudiantes(){
        List<Usuario> estudiantes = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "Select MUsuario.nom_usu, MUsuario.id_usu, MUsuario.correo_usu, MUsuario.boleta_usu where rol_usu = 3";
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setEmail(rs.getNString("MUsuario.correo_usu"));
                usu.setNombre(rs.getNString("MUsuario.nom_usu"));
                usu.setBoleta(rs.getLong("MUsuario.boleta_usu"));
                usu.setId_cifrado(Cifrado.encrypt(String.valueOf(rs.getInt("MUsuario.id_usu"))));
                estudiantes.add(usu);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return estudiantes;
    }
    
}
