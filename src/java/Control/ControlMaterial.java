
package Control;

import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


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
    
    
    
}
