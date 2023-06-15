package Servlets;

import Control.Cifrado;
import Control.ControlUsuario;
import Control.Validacion;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarEstudiante extends HttpServlet {

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
                String nombre, correo, bol, idcif;
                nombre = request.getParameter("nombre");
                correo = request.getParameter("correo");
                bol = request.getParameter("boleta");
                idcif = request.getParameter("id");
                
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==1){//Admin
                        if(Validacion.Validarcorreo(correo) && Validacion.ValidarBoleta(bol) && Validacion.Validarnombre(nombre)){
                            Usuario nuevo = new Usuario();
                            System.out.println(bol);
                            nuevo.setEmail(correo);
                            nuevo.setBoleta(Long.valueOf(bol));
                            nuevo.setNombre(nombre);
                            nuevo.setId_cifrado(idcif);
                            boolean modificar= ControlUsuario.ModificarEstudiante(nuevo);
                            if(modificar){
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                        out.println("icon: 'success',");
                                        out.println("title: 'Correcto',");
                                        out.println("text: 'Se ha modificado el usuario'");
                                    out.println(" });");
                                out.println("</script>");
                            }else{
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                        out.println("icon: 'error',");
                                        out.println("title: 'Registro repetido',");
                                        out.println("text: 'La boleta o correo ya esta registrado'");
                                    out.println(" });");
                                out.println("</script>");
                            }
                        }else{
                            out.println("<script>");
                                out.println("Swal.fire({");
                                      out.println("icon: 'error',");
                                     out.println("title: 'Datos invalidos',");
                                     out.println("text: 'Ingresa caracteres validos'");
                                out.println(" });");
                            out.println("</script>");
                        }
                    }else{
                    
                        out.println("<script>location.href = './index.jsp'");
                    }
                }else{
                    out.println("<script>location.href = './index.jsp'");
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
