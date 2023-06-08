
package Control;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ControlMaterial {
    
    public static List<Catalogo> ObtenerTemas(int unidad){
        //filto para cuando se seleccione una unidad
        List<Catalogo> temas = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select * from CTema where id_unidad = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, unidad);
            rs = ps.executeQuery();
            if(rs.next()){
                Catalogo ob = new Catalogo();
                ob.setDes(rs.getString("nom_tema"));
                ob.setId(rs.getInt("id_tema"));
                temas.add(ob);
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
        return temas;
    }
    
    
    public static List<Material> ObtenerMaterialEstudiante(){ 
        //se guardara esta lista en la sesion cada que se carga la pagina material de estudiante
        //cuando se aplica un filtro, se va acceder a la lista de la sesion para filtrar
        //este filtro se va ser mediante un servlet
        List<Material> material = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select id_material, titulo_publi, id_tipo from MMaterial where estado = 1";
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Material mat = new Material();
                mat.setId_cifrado(Cifrado.encrypt(String.valueOf(rs.getInt("id_material"))));
                mat.setTitulo(rs.getString("titulo_publi"));
                mat.setTipo(rs.getInt("id_tipo"));
                mat.setTemas(ObtenerTemaMaterial(rs.getInt("id_material"), con));
                material.add(mat);
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
        return material;
    }
    
    public static List<Catalogo> ObtenerTemaMaterial(int id, Connection con){
        List<Catalogo> temas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select CTema.id_tema, CTema.nom_tema, CTema.id_unidad from ETemaMaterial "
                    + "INNER JOIN CTema ON ETemaMaterial.id_tema = CTema.id_tema "
                    + "where ETemaMaterial.id_material = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                Catalogo ob = new Catalogo();
                ob.setDes(rs.getString("CTema.nom_tema"));
                ob.setId(rs.getInt("CTema.id_tema"));
                temas.add(ob);
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return temas;
    }
    
    public static boolean AgregarMaterial(Material mat){
        boolean resultado = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "insert into MMaterial (titulo_publi, link_publi, bibliografia_publi, estado, id_tipo, id_usu) values (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, mat.getTitulo());
            ps.setString(2, mat.getLink());
            ps.setString(3, mat.getBibliografia());
            ps.setInt(4, 1);
            ps.setInt(6, mat.getUsu());
            ps.setInt(5, mat.getTipo());
            int estado = ps.executeUpdate();
            if(estado>1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    sql = "insert into ETemaMaterial (id_material, id_tema) values (?,?)";
                    for(Catalogo m: mat.getTemas()){
                        ps = con.prepareCall(sql);
                        ps.setInt(1, id);
                        ps.setInt(2, m.getId());
                        estado = ps.executeUpdate();
                        if(estado==0){
                            resultado = false;
                            break;
                        }else{
                            resultado = true;
                        }
                    }
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
    
    public static Material ConsultarMaterial(String id_cifrado){ //Se usa en el jsp
        Material material = new Material();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select * from MMaterial where id_material=?";
            ps = con.prepareStatement(sql);
            int id= Integer.valueOf(Cifrado.decrypt(id_cifrado));
            ps.setInt(1, id);
            rs= ps.executeQuery();
            if(rs.next()){
                material.setBibliografia(rs.getString("bibliografia_publi"));
                material.setTitulo(rs.getString("titulo_publi"));
                material.setEstado(rs.getInt("estado"));
                material.setTipo(rs.getInt("id_tipo"));
                material.setLink(rs.getString("link_publi"));
                material.setId_cifrado(id_cifrado);
                material.setTemas(ObtenerTemaMaterial(id, con));
            }else{
                material.setEstado(-1);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            material.setEstado(-1);
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
        return material;
    }
    
    public static boolean CambiarEstado(String id_cifrado){
        boolean resultado = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int estado=0;
        try{
            con = ConexionBD.getConnection();
            String sql = "select estado from MMaterial where id_material=?";
            ps = con.prepareStatement(sql);
            int id= Integer.valueOf(Cifrado.decrypt(id_cifrado));
            ps.setInt(1, id);
            rs= ps.executeQuery();
            if(rs.next()){
                estado = rs.getInt("estado");
                estado = (estado==0)? 1:0;
                sql = "update MMaterial set estado = ? where id_material = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1,estado);
                ps.setInt(2, id);
                int estatus = ps.executeUpdate();
                if(estatus>0){
                    resultado = true;
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
    
    
    public static boolean ModificarMaterial(Material mat){
         boolean resultado = false, result;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            String sql = "select * from MMaterial where id_material = ?";
            ps = con.prepareCall(sql);
            int id = Integer.valueOf(Cifrado.decrypt(mat.getId_cifrado()));
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                if(!(mat.getBibliografia().equals(rs.getString("bibliografia_publi"))&&
                    mat.getLink().equals(rs.getString("link_publi"))&&
                    mat.getTitulo().equals(rs.getString("titulo_publi"))&&
                    mat.getTipo() == rs.getInt("id_tipo"))){
                    
                    if(ModificarTablaMat( mat, id,  con)){
                        result = true;
                        resultado = true;
                    }else{
                        result = false;
                    }
                    
                }else{
                    result = true;
                }
                
                List<Catalogo> temas = ObtenerTemaMaterial( id, con);
                
                if(result && !temas.equals(mat.getTemas())){
                    //ver que temas sigun y cuales son nuevas.
                    //solucion facil elimniar todos y volver a añadirlos
                    resultado = true;
                }else{
                    resultado = false;
                
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
    
    
    public static boolean ModificarTablaMat(Material mat, int id, Connection con){
        boolean resultado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "update MMaterial set titulo_publi = ? , link_publi = ?, bibliografia_publi = ?"
                        + ", id_tipo= ? where id_material = ?";
            ps = con.prepareCall(sql);
            ps.setString(1, mat.getTitulo());
            ps.setString(2, mat.getLink());
            ps.setString(3, mat.getBibliografia());
            ps.setInt(4, mat.getTipo());
            ps.setInt(5, id);
            int estatus = ps.executeUpdate();
            if(estatus>0){
                resultado = true;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            resultado = false;
        }finally{
            try{
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
    
}
