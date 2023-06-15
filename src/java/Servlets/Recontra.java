
package Servlets;

import Control.Cifrado;
import Control.ControlUsuario;
import Control.JWT;
import Control.Validacion;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Recontra extends HttpServlet {

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
                String token = request.getParameter("token");
                String pass = request.getParameter("password");
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")== null){
                    if(token!= null && pass!= null){
                        int id= JWT.readJWT(token);
                        if(id>=0){
                            if(Validacion.Validarcontra(pass)){
                                boolean seg = ControlUsuario.CambiarPassword(id, pass);
                                if(seg){
                                    out.println("<script>");
                                    out.println("Swal.fire({");
                                        out.println("icon: 'success',");
                                        out.println("title: 'Correcto',");
                                        out.println("text: 'La contraseña se ha cambiado'");
                                    out.println(" });");
                                    out.println("setTimeout(function() {");
                                         out.println("location.href = './index.jsp' ");
                                     out.println("}, 2000);");
                                out.println("</script>");
                                }else{
                                    out.println("<script>");
                                        out.println("Swal.fire({");
                                              out.println("icon: 'error',");
                                             out.println("title: 'Oops...',");
                                             out.println("text: 'Error intenta mas tarde'");
                                        out.println(" });");
                                    out.println("</script>");
                                }
                            }else{
                                out.println("<script>");
                                    out.println("Swal.fire({");
                                          out.println("icon: 'error',");
                                         out.println("title: 'Oops...',");
                                         out.println("text: 'Ingresa solo caracteres validos'");
                                    out.println(" });");
                                out.println("</script>");
                            }
                        }else{
                            out.println("<script>");
                                out.println("Swal.fire({");
                                      out.println("icon: 'error',");
                                     out.println("title: 'Oops...',");
                                     out.println("text: 'El token es invalido'");
                                out.println(" });");
                            out.println("</script>");

                        }
                    
                    }else{
                        out.println("<script>");
                            out.println("Swal.fire({");
                                  out.println("icon: 'error',");
                                 out.println("title: 'Oops...',");
                                 out.println("text: 'Llena todos los campos'");
                            out.println(" });");
                        out.println("</script>");
                    }
                
                }else{
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==1){//Admin
                        out.println("<script>location.href= './inicioadmin.jsp'</script>");
                    }
                    
                    if(id_rol==2){//publicador
                        out.println("<script>location.href= './iniciopublicador.jsp'</script>");
                    }
                    
                    if(id_rol==3){//usuario
                        out.println("<script>location.href= './inicioestudiante.jsp'</script>");
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
                out.println("<script>");
                    out.println("Swal.fire({");
                          out.println("icon: 'error',");
                         out.println("title: 'Oops...',");
                         out.println("text: 'error'");
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
