<%@page import="java.util.List"%>
<%@page import="Modelo.Catalogo"%>
<%@page import="Control.Validacion"%>
<%@page import="Control.ControlMaterial"%>
<%@page import="Modelo.Material"%>
<%@page import="Modelo.Usuario"%>
<%@page import="Control.Cifrado"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java"%>
<%
    HttpSession sesion = request.getSession(true);
    Usuario usu = (Usuario)sesion.getAttribute("usuario");
    if(usu!=null){
        try{
            int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
            if(id_rol==1){//Admin
                response.sendRedirect("./inicioadmin.jsp");
            }

            if(id_rol==2){//publicador
                 response.sendRedirect("./iniciopublicador.jsp");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
                        %> 
                    <jsp:forward page="index.jsp">
                        <jsp:param name="Error" value="Es obligatorio identificarse" />
                    </jsp:forward>
            <%
        }
    }else{
        %> 
        <jsp:forward page="index.jsp">
            <jsp:param name="Error" value="Es obligatorio identificarse" />
        </jsp:forward>
<%
    }
    String idS = "";
    if(request.getParameter("id")== null){
        response.sendRedirect("./material.jsp");
    }else{
        idS = request.getParameter("id");
    }
    Material mat = new Material();
    try{
        mat = ControlMaterial.ConsultarMaterial(idS);
        if(mat.getEstado() == -1){
            throw new Exception("id invalido");
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
%> 
        <jsp:forward page="material.jsp">
            <jsp:param name="Error" value="identificador no valido" />
        </jsp:forward>
<%
    }
    
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <% 
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires", 0);
    %>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="./css/Barra.css">
  <link rel="stylesheet" href="./css/styles.css">
  <link rel="stylesheet" href="./css/material.css">
  <link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <title>Material</title>
</head>

<body>
  <nav class="navegacion">
    <input type="checkbox" id="check">
    <label for="check" class="checkbtn">
      <i class="fa-solid fa-bars"></i>
    </label>
    <div class="logoContainer" >
        <a href="#">
            <img src="multimedia/logoESCOMBlanco.png" alt="principal" class="logo">
        </a>
        <h5 class="titulo">Linealizate</h5>
    </div>
    <ul>
        <li><a href="./material.jsp">volver</a></li>
        <li><a onclick="Cerrarsesion()">Cerrar Sesion</a></li>
        <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
    </nav>
    <h4 class="header2">Titulo: <%=mat.getTitulo() %></h4>
    <h4 class="header2">Tipo: <%=Validacion.CambiarTipo(mat.getTipo()) %></h4>
    <h4 class="header2">Unidad <%=mat.getTemas().get(0).getUnidad()%></h4>
    <%
        for(Catalogo cat: mat.getTemas()){
        %>
        <h6 class="header2"><%=cat.getDes()%></h6>
        <%
        }
    %>
    
    <br>
    <%
        String link = Validacion.Cambiarlink(mat.getLink());
        String[] str = link.split("youtube");
        if(str.length != 1){%>
    
    <div class="video">
       <iframe width="100%" height="400" src="<%=link%>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe> 
    </div>
    <%    
        }else{
            str = link.split("drive");
            if(str.length!=1){%>
    
        <iframe src="<%=link%>" width="80%" height="600px" style="margin-left: 10%;"></iframe>
    <%
            }else{
                str = link.split("forms");
                if(str.length!=1){%>
            <div class="video2" >
                <iframe src="<%=link%>" width="100%" height="700" frameborder="0" marginheight="0" marginwidth="0">Cargando…</iframe>
            </div>
        <%
                }else{
                    %>
            <h4 class="header2">link: <%=link%></h4>
    <%
                }
                
            }
        }
        
    %>
    
    <br>
    <h4 class="header2">Bibliografia:  <%=mat.getBibliografia() %></h4>
    
    <h4 class="header2">Videos de los temas</h4>
    <%
        for(Catalogo c: mat.getTemas()){
            List<Material> vid = ControlMaterial.ObtenerVideosTemas(c.getId());
            %>
            <h5 class="header2"><%=c.getDes() %></h5>
    <%
            for(Material vidm: vid){
                %>
    
                <div class="video">
                    <iframe width="100%" height="400" src="<%=Validacion.Cambiarlink(vidm.getLink()) %>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe> 
                </div>
                <br>
                <%   
            }
        }
    %>
    
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
</div>
</body>

</html>