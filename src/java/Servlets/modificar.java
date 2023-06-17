
package Servlets;

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


public class modificar extends HttpServlet {

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
                String idM = request.getParameter("id");
                String titulo = request.getParameter("titulo");
                String bibliografia = request.getParameter("bibliografia");
                String unidad = request.getParameter("unidad");
                String link = request.getParameter("link");
                String tipo = request.getParameter("tipo");
                String StrTema = request.getParameter("temas");
                List<Catalogo> temas = new ArrayList<>();
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                     Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==2 || id_rol ==1){
                        String[] temSt;
                        temSt = StrTema.split(",");
                        boolean seguir = true;
                        for (String tema : temSt) {
                            if(Validacion.ValidarTema(tema)){
                                Catalogo cat = new Catalogo();
                                cat.setId(Integer.valueOf(tema));
                                temas.add(cat);
                            }else{
                                seguir = false;
                            }
                        }
                        if(Validacion.ValidarTitulo(titulo) && Validacion.ValidarBibliografia(bibliografia)
                                && Validacion.ValidarUnidadyTipo(unidad) && Validacion.ValidarLink(link)
                                && Validacion.ValidarUnidadyTipo(tipo)&&seguir && !temas.isEmpty() &&idM!= null){
                            Material mat = new Material();
                            mat.setBibliografia(bibliografia);
                            mat.setLink(link);
                            mat.setTemas(temas);
                            mat.setTipo(Integer.valueOf(tipo));
                            mat.setTitulo(titulo);
                            mat.setId_cifrado(idM);
                            boolean seguirM= ControlMaterial.ModificarMaterial(mat);
                            if(seguirM){
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                        out.println("icon: 'success',");
                                        out.println("title: 'Correcto',");
                                        out.println("text: 'Se ha modificado'");
                                    out.println(" });");
                                    out.println("setTimeout(function() {");
                                         if(id_rol==2)out.println("location.href = './publicaciones.jsp' ");
                                         if(id_rol==1)out.println("location.href = './adminpublicaciones.jsp' ");
                                     out.println("}, 1000);");
                                out.println("</script>");
                            
                            }else{
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                          out.println("icon: 'error',");
                                         out.println("title: 'Oops...',");
                                         out.println("text: 'Ocurrio un error'");
                                    out.println(" });");
                                out.println("</script>");
                            
                            }
                        
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
