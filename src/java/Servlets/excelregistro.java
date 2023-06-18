
package Servlets;

import Control.Cifrado;
import Control.ControlUsuario;
import Control.Validacion;
import Modelo.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/ProcesoArchivo")
@MultipartConfig
public class excelregistro extends HttpServlet {

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
                try{
                HttpSession sesion = request.getSession(true);
                if(sesion.getAttribute("usuario")!= null){
                    Usuario usu = (Usuario)sesion.getAttribute("usuario");
                    int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
                    if(id_rol==1){
                        Part filePart = request.getPart("src-file1");
                        // Obtener el nombre del archivo
                        String fileName = filePart.getSubmittedFileName();

                        // Guardar el archivo en el servidor
                        File uploadsDir = new File("./");
                        File file = new File(uploadsDir, fileName);

                        try (InputStream inputStream = filePart.getInputStream()) {
                            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }

                        List<Usuario> estudiantes = Control.LeerExcel.LeerArchivo(file);
                        boolean seguir;
                        int count = 0;
                        if(estudiantes.size()!=0){
                            for(Usuario usus: estudiantes){
                                if(Validacion.ValidarBoleta(String.valueOf(usus.getBoleta())) && Validacion.Validarcorreo(usus.getEmail()) &&
                                        Validacion.Validarnombre(usus.getNombre())){
                                        seguir = ControlUsuario.RegistrarEstudiante(usus);
                                        if(!seguir)count++;
                                }else{
                                    count++;
                                }
                            }
                            if(count ==0){
                                response.sendRedirect("./estudiante.jsp?err=4");
                            }else if(count<estudiantes.size()){
                                response.sendRedirect("./estudiante.jsp?err=2&&cant="+count);
                            }else{
                                response.sendRedirect("./estudiante.jsp?err=3");
                            }
                        }else{
                            response.sendRedirect("./estudiante.jsp?err=1");
                        }
                        
                    }else{
                    
                        response.sendRedirect("./estudiante.jsp?err=1");
                    }
                }else{
                    response.sendRedirect("./index.jsp");
                }
            }catch(Exception e){
                    System.out.println(e.getMessage());
                    response.sendRedirect("./estudiante.jsp?err=1");
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
