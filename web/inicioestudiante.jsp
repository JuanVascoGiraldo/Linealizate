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
  <link rel="stylesheet" href="./css/modal.css"> 
  <link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <title>Inicio</title>
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
      <li><a href="./material.jsp">Material</a></li>
      <li><a href="#" data-open="modalR1" >Contraseña</a></li>
      <li><a onclick="Cerrarsesion()">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <div class="modal" id="modalR1">
    <div class="card">
        <form action="" name="">
            <label for="" class="article">Cambiar Contraseña</label><br>
            <br>
            <hr class="linea">
            <br>
            <label for="" class="article">Ingresa tu nueva contraseña</label><br>
            <input type="password" name="password" id="password" class ="input" required>
            <br>
            <label for="" class="article">Confirma tu nueva contraseña</label><br>
            <input type="password" name="passwordconf" id="passwordconf" class ="input" required>
            <br>
            <button type="button" class="submit" id="enviar" onclick="CambiarContra()">Cambiar</button>
        </form>
    <div id="cambiar2">
                            
    </div>
    </div>
    
</div>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
	<script src="./js/modal.js"></script>
</body>

</html>