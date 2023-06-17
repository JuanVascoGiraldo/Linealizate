
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
                String unidad = request.getParameter("unidad");
                String tema = request.getParameter("tema");
                String tipo = request.getParameter("tipo");
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol ==3){
                        if(Validacion.ValidarUnidadyTipo(unidad) && Validacion.ValidarUnidadyTipo(tipo)
                                && Validacion.ValidarTema(tema)){
                            
                            List<Material> matr = ControlMaterial.ObtenerMaterialEstudiante();
                            /*
                                 <div class="main_container">
                                    <div class="mini_header">
                                      &nbsp;
                                      &nbsp;<h2>Videos</h2>
                                        <h2>Unidad</h2>
                                        &nbsp;
                                        &nbsp;
                                    </div>
                                    <div class="titulomat">
                                        <h3>Calculo de determinantes</h3>
                                        <br>
                                        <h2>tema 1</h2>
                                        <br>
                                        <h2>tema 2</h2>
                                    </div>
                                    <div class="varmat">
                                        <a href="./vermaterial.html" class="ira">Ver material</a>
                                    </div>
                                    <br>
                                </div>
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
