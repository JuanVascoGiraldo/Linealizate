
package Servlets;

import Control.Cifrado;
import Control.ControlUsuario;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class obtenerEstudiantes extends HttpServlet {

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
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("usuario")!= null){
                try{
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==1){//Admin
                       List<Usuario> estudiantes = ControlUsuario.EncontrarEstudiantes();
                       estudiantes.forEach(est->{
                                out.println("<div class=\"alinear\">");
                                out.println("<div class=\"flex\">");
                                    out.println("<div class=\"m\">");
                                        out.println("<img src=\"./imagenes/estudiar.png\">");
                                    out.println("</div>");
                                    out.println("<div></div>");
                                    out.println("<div class=\"text\">");
                                        out.println("<h3>"+est.getNombre()+"</h3>");
                                        out.println("<hr>");
                                        out.println("<h4>"+est.getEmail()+"</h4>");
                                        out.println("<h4>"+est.getBoleta()+"</h4>");
                                    out.println("</div>");
                                    out.println("<div></div>");
                                    out.println("<div></div>");
                                    out.println("<div></div>");
                                    out.println("<div></div>");
                                out.println("</div>");
                                out.println("<div class=\"btn\">");
                                    out.println("<button type=\"button\" class=\"cs\" onclick=\"EnviarModE('"+est.getId_cifrado()+"')\" data-open=\"modal3\">Eliminar</button>");
                                    out.println("<button type=\"button\" onclick=\"EnviarModF('"+est.getId_cifrado()+"', '"+est.getNombre()+"', '"+est.getEmail()+"', '"+est.getBoleta()+"')\" class=\"mc\" data-open=\"modalMod\">Modificar</button>");
                                out.println("</div>");
                                out.println("</div>");
                       });
                       out.println("<script>definirmod()</script>");
                    }else{
                        out.println("<script>location.href = './index.jsp'");
                    }
                }catch(Exception e){
                    System.out.println("id invalido");
                    out.println("<script>");
                        out.println("Swal.fire({");
                              out.println("icon: 'error',");
                             out.println("title: 'Oops...',");
                             out.println("text: 'Error'");
                        out.println(" });");
                    out.println("</script>");
                }
            }else{
                out.println("<script>location.href = './index.jsp'");
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
