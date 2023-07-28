
package Filtro;

import Control.Cifrado;
import Control.ControlMaterial;
import Control.Validacion;
import Modelo.Catalogo;
import Modelo.Material;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetMaterial extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                String unidadS = request.getParameter("unidad");
                String temaS = request.getParameter("tema");
                String tipoS = request.getParameter("tipo");
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol ==3){
                        if(Validacion.ValidarUnidadyTipo(unidadS) && Validacion.ValidarUnidadyTipo(tipoS)
                                && Validacion.ValidarTema(temaS)){
                            int unidad, tema, tipo;
                            unidad = Integer.valueOf(unidadS);
                            tema = Integer.valueOf(temaS);
                            tipo = Integer.valueOf(tipoS);
                            
                            List<Material> matr = ControlMaterial.ObtenerMaterialEstudiante();
                            List<Material> mostrar = new ArrayList<>();
                            if(tipo== 0 && tema ==0 && unidad == 0){
                                mostrar = matr;
                            }else{
                                boolean seguir=false;
                                for(Material mat: matr){
                                    seguir=false;
                                    if(mat.getTemas().get(0).getUnidad() == unidad || unidad ==0){
                                        seguir = true;
                                    }
                                    
                                    if((mat.getTipo() == tipo || tipo ==0)&&seguir ){
                                        seguir = true;
                                    }else{
                                        seguir =false;
                                    }
                                    if((tema != 0)&&seguir ){
                                        for(Catalogo c: mat.getTemas()){
                                            if(c.getId() == tema){
                                                seguir = true;
                                                break;
                                            }else{
                                                seguir = false;
                                            }
                                        }
                                    }
                                    
                                    
                                    if(seguir){
                                        mostrar.add(mat);
                                    }
                                }
                            }
                            System.out.println(mostrar.size());
                            mostrar.forEach((mat)->{
                                out.println("<div class=\"main_container\">");
                                        out.println("<div class=\"mini_header\">");
                                          out.println("&nbsp;");
                                          out.println("&nbsp;<h2>"+Validacion.CambiarTipo(mat.getTipo())+"</h2>");
                                            out.println("<h2>aaaaaUnidad "+mat.getTemas().get(0).getUnidad()+"</h2>");
                                            out.println("&nbsp;");
                                            out.println("&nbsp;");
                                        out.println("</div>");
                                        out.println("<div class=\"titulomat\">");
                                            out.println("<h3>"+mat.getTitulo()+"</h3>");
                                            out.println("<br>");
                                            out.println("<h5>Temas</h5>"); 
                                            for(Catalogo tem: mat.getTemas()){
                                               out.println("<h6>"+tem.getDes()+"</h6>"); 
                                            }
                                        out.println("</div>");
                                        out.println("<div class=\"varmat\">");
                                            out.println("<a onclick=\" verMat('"+mat.getId_cifrado()+"')\" class=\"ira\">Ver material</a>");
                                        out.println("</div>");
                                        out.println("<br>");
                                    out.println("</div>");
                            });
                            
                            /*
                                 
                                */
                           
                            
                            
                        }else{
                            out.println("<script>");
                                out.println("Swal.fire({");
                                      out.println("icon: 'error',");
                                     out.println("title: 'Oops...',");
                                     out.println("text: 'Solo ingresa caracteres validos'");
                                out.println(" });");
                            out.println("</script>");
                        
                        }
                    }else{
                        out.println("<script>");
                            out.println("location.href = './index.jsp'");
                        out.println("</script>");
                    
                    }
                }else{
                    out.println("<script>");
                        out.println("location.href = './index.jsp'");
                    out.println("</script>");
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
                out.println("<script>");
                    out.println("Swal.fire({");
                          out.println("icon: 'error',");
                         out.println("title: 'Oops...',");
                         out.println("text: 'Error'");
                    out.println(" });");
                out.println("</script>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
