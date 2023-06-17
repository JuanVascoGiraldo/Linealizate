/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtro;

import Control.Cifrado;
import Control.ControlMaterial;
import Control.Validacion;
import Modelo.Catalogo;
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

/**
 *
 * @author Juanv
 */
public class GetTemas extends HttpServlet {

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
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==2){
                        if(Validacion.ValidarUnidadyTipo(unidad)){
                            int uni = Integer.valueOf(unidad);
                            List<Catalogo> temas = new ArrayList<>();
                            temas = ControlMaterial.ObtenerTemas(uni);
                            if(!temas.isEmpty()){
                                out.println("<h5>Temas:</h5>");
                                    out.println("<div class=\"checkbox\">");
                                    temas.forEach((tem)->{
                                                out.println("<div class=\"checkboxItem\">");
                                                    out.println("<input type=\"checkbox\" name=\"tema\" id=\""+tem.getId()+"\" value=\""+tem.getId()+"\">");
                                                    out.println("<label for=\"tema1\">"+tem.getDes()+"</label>");
                                                out.println("</div>");
                                    });
                                out.println("</div>");  
                            }else{
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                          out.println("icon: 'error',");
                                         out.println("title: 'Oops...',");
                                         out.println("text: 'Unidad no valida'");
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
