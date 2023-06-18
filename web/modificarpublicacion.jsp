<%@page import="Modelo.Catalogo"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Material"%>
<%@page import="Control.ControlMaterial"%>
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

            if(id_rol==3){//usuario
                 response.sendRedirect("./inicioestudiante.jsp");
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
        response.sendRedirect("./publicaciones.jsp");
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
        <jsp:forward page="publicaciones.jsp">
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
  <link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <title>Modificar</title>
</head>

<body>
  <nav class="navegacion">
    <input type="checkbox" id="check">
    <label for="check" class="checkbtn">
      <i class="fa-solid fa-bars"></i>
    </label>
    <div class="logoContainer" >
      <a href="principal.html">
        <img src="multimedia/logoESCOMBlanco.png" alt="principal" class="logo">
      </a>
      <h5 class="titulo">Linealizate</h5>
    </div>
    <ul>
      <li><a href="./iniciopublicador.jsp">Inicio</a></li>
      <li><a href="./publicaciones.jsp">Publicaciones</a></li>
      <li><a onclick="Cerrarsesion()">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <h3 class="header">Modificar publicacion</h3>
  <div class="formContainer">
    <div class="content">
      <form class="form" name="modificar" action="">
        <input class="input" type="text" name="Title" id="title" placeholder="Titulo" value="<%=mat.getTitulo() %>">
        <br />
        <div class="inputContainer">
          <h5>Tipo de material: </h5>
          <select class="select" name="Tipo" id="tipo">
            <option value="1" <%if(mat.getTipo()==1){%>selected<%}%>>Video</option>
            <option value="2" <%if(mat.getTipo()==2){%>selected<%}%>>Infografia</option>
            <option value="3" <%if(mat.getTipo()==3){%>selected<%}%>>Ejemplo</option>
            <option value="4" <%if(mat.getTipo()==4){%>selected<%}%>>Examenes</option>
            <option value="5" <%if(mat.getTipo()==5){%>selected<%}%>>Listas</option>
          </select>
        </div>
        <div class="inputContainer">
          <h5>Unidad: </h5>
           <select class="select" name="Unidad" id="unidad" onchange="javascript:imputcheck(2)">
                <option value="1" <%if(mat.getTemas().get(0).getUnidad()==1){%>selected<%}%>>Unidad 1</option>
                <option value="2" <%if(mat.getTemas().get(0).getUnidad()==2){%>selected<%}%>>Unidad 2</option>
                <option value="3" <%if(mat.getTemas().get(0).getUnidad()==3){%>selected<%}%>>Unidad 3</option>
                <option value="4" <%if(mat.getTemas().get(0).getUnidad()==4){%>selected<%}%>>Unidad 4</option>
          </select>
        </div>
        <div class="checkboxContainer" id="cambiar1">
          
        </div>
        <br>
        <input class="input" type="url" name="link" id="link" placeholder="Link" value="<%=mat.getLink() %>">
        <input class="input" type="text" placeholder="Bibliografia" id="Bibliografia"value="<%=mat.getBibliografia() %>">
        <br>
        <div class="buttonContainer">
          <button class="button" type="button" onclick="Modificarpublicacion('<%=idS%>')">Modificar</button>
        </div>
      </form>
    </div>
  </div>
        <div id="notificacion">
            
        </div>
  <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
	<script src="./js/modal.js"></script>
        <script>
            function seleccionarTemas(){
               let temas = [];
               <%
                   List<Catalogo> tem = mat.getTemas();
                   for(Catalogo t: tem){
                   %>
                          temas.push(<%=t.getId()%>);
                   <%
                   };
               %>
                let checkboxes = document.querySelectorAll('input[type="checkbox"]');
                checkboxes.forEach(function(checkbox) {
                    if (temas.includes(parseInt(checkbox.value))) {
                       checkbox.checked = true;
                    }
                });
            }
            setTimeout(function() {
                imputcheck(2);
            }, 2000);
        </script>
</body>

</html>