
package Filtro;

import Control.Cifrado;
import Control.ControlMaterial;
import Control.Validacion;
import Modelo.Catalogo;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Material extends HttpServlet {

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
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol ==2 || id_rol==1){
                        int ide = Integer.valueOf(Cifrado.decrypt(usu.getId_cifrado()));
                        List<Modelo.Material> mats = ControlMaterial.ObtenerMaterialAdmin(ide);
                        /*
                            <div class="main_container">
                                <div class="mini_header2">
                                    <h2>Videos</h2>
                                    <h2>Unidad</h2>
                                </div>
                                <div class="pregunta">
                                    <h3>Calculo de determinantes</h3>
                                    <h5>tema 1, tema 2, tema 3</h5>
                                    <h5>link</h5>
                                    <h5>bibliografia</h5>
                                </div>
                                <div class="flex">
                                    <button class="question" onclick="">Modificar</button>
                                    <button class="cs" onclick="">Desactivar</button>
                                </div>
                            </div>
                        */
                        int tipp = id_rol;
                       mats.forEach((Modelo.Material mat)->{
                            String palabra = "";
                            String tipo = (mat.getEstado()==1)?"mini_header2":"mini_header3";
                            String tipo2 = (mat.getEstado()==1)?"cs":"cs2";
                            palabra=(1==mat.getEstado())?"Desactivar": "Activar";
                            out.println("<div class=\"main_container\">");
                                out.println("<div class=\""+tipo+"\">");
                                    out.println("<h2>"+Validacion.CambiarTipo(mat.getTipo())+"</h2>");
                                    out.println("<h2> Unidad "+mat.getTemas().get(0).getUnidad()+"</h2>");
                                out.println("</div>");
                                out.println("<div class=\"pregunta\">");
                                    out.println("<h3> Titulo: "+mat.getTitulo()+"</h3>");
                                    out.println("<h6>Temas:</h6>"); 
                                    for(Catalogo tem: mat.getTemas()){
                                       out.println("<h6>"+tem.getDes()+"</h6>"); 
                                    }
                                    out.println("<h6>Link:</h6>"); 
                                    out.println("<h5>"+mat.getLink()+"</h5>");
                                    out.println("<h6>Bibliografía:</h6>"); 
                                    out.println("<h5>"+mat.getBibliografia()+"</h5>");
                                out.println("</div>");
                                out.println("<div class=\"flex\">");
                                    out.println("<button class=\"question\" onclick=\"Modificar('"+mat.getId_cifrado()+"',"+tipp+")\">Modificar</button>");
                                    out.println("<button class=\""+tipo2+"\" onclick=\"Estado('"+mat.getId_cifrado()+"')\">"+palabra+"</button>");
                                out.println("</div>");
                            out.println("</div>");
                       });
                       
                       
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
