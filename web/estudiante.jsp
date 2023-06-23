<%@page import="Modelo.Usuario"%>
<%@page import="Control.Cifrado"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java"%>
<%
    HttpSession sesion = request.getSession(true);
    Usuario usu = (Usuario)sesion.getAttribute("usuario");
    if(usu!=null){
        try{
            int id_rol = Integer.valueOf(Cifrado.decrypt(usu.getRol_cifrado()));
            if(id_rol==2){//publicador
                 response.sendRedirect("./iniciopublicador.jsp");
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

    int err, count;
    try{
        err = Integer.valueOf(request.getParameter("err"));
    }catch(Exception e){
        err = 0;
    }
    
    try{
        count = Integer.valueOf(request.getParameter("cant"));
    }catch(Exception e){
        count = 0;
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
  <link rel="stylesheet" href="./css/estudiante.css"> 
  <link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <title>Estudiante</title>
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
      <li><a href="./inicioadmin.jsp">Inicio</a></li>
      <li><a href="./adminpublicaciones.jsp">Publicaciones</a></li>
      <li><a onclick="Cerrarsesion()">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <div class="arriba">
    <button class="ac" data-open="modalR1">Agregar Estudiante</button>
    <button class="ch" data-open="modalEstu">Registrar Grupo</button>
    <button class="cs" data-open="modal31">Eliminar Grupo</button>
  </div>
  
    <div id="Estudiantes">
        
    </div>

    <div id="notificacion">
        
    </div>

  <div class="modal" id="modalEstu">
    <div class="card">
        <form action="./excelregistro" method="POST" name="excel" enctype="multipart/form-data">
            <label for="" class="article">Registrar grupo</label><br>
            <hr class="linea">
            <label for="" class="article">Ingresa el archivo excel de los estudiantes</label>
            <br>
            <br>
            <div class="file-select" id="src-file1" >
              <input type="file" name="src-file1"  id="src-file1" aria-label="Archivo" accept=".xlsx, .xls" onchange="mostrarNombreArchivo(this)">
            </div>
            <br>
            <br>
            <button type="button" class="submit" id="enviar" onclick="document.excel.submit()">Registrar</button>
        </form>
    </div>
  </div>

  <div class="modal" id="modalR1">
    <div class="card">
        <form action="" name="registrar">
            <label for="" class="article">Registrar Estudiante</label><br>
            <br>
            <hr class="linea">
            <br>
            <input type="text" name="nombre" id="nombre" placeholder="Nombre del Estudiante" class="input" value="">
            <input type="text" name="correo" id="correo" placeholder="Correo electronico" class="input" value="">
            <input type="number" name="boleta" id="boleta" placeholder="Numero de boleta" class="input" min="2" max="9999999999"value="">
            <br>
            <button type="button" class="submit" id="enviar" onclick="RegistrarEstudiante()">Registrar</button>
        </form>
    </div>
  </div>

  <div class="modal" id="modalMod">
    <div class="card">
        <form action="" name="modificar">
            <label for="" class="article">Modificar Estudiante</label><br>
            <br>
            <hr class="linea">
            <br>
            <input type="hidden" name="idE" id="idMod" value="">
            <input type="text" name="nomMod" id="nomMod" placeholder="Nombre del Estudiante" class="input" value="">
            <input type="text" name="correoMod" id="correoMod" placeholder="Correo electronico" class="input" value="">
            <input type="number" name="boletaMod" id="boletaMod" placeholder="Numero de boleta" class="input" min="2" max="9999999999"value="">
            <br>
            <button type="button" class="submit" id="enviar" onclick="ModificarEstudiante()">Modificar</button>
        </form>
    </div>
  </div>

  <div class="modal" id="modal3">
      <div class="card">
          <form name="deshabilitar" action=""><input type="hidden" name="idE" id="idE" value=""></form>
          <h4>Cuidado, esta accion podra afectar el flujo del sistema</h4>
          <button class="cs" onclick="Eliminar()">Eliminar Cuenta</button>
          <button class="submit"  onclick="crm2()">No Eliminar Cuenta</button>
      </div>
  </div>

  <div class="modal" id="modal31">
    <div class="card">
        <h4>Cuidado, esta accion podra afectar el flujo del sistema</h4>
        <button class="cs" onclick="EliminarGrupo()">Eliminar Grupo</button>
        <button class="submit" onclick="crm()">No inhabilitar Grupo</button>
    </div>
</div>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
	<script src="./js/modal.js"></script>
  <script>
     ObtenerEstudiante();
    const fileLabel = document.getElementById('src-file1');
    fileLabel.setAttribute('data-nombre-archivo', "Seleccionar Archivo");
    fileLabel.classList.add('mostrar-archivo');

    function mostrarNombreArchivo(input) {
      if (input.files.length > 0) {
        fileLabel.setAttribute('data-nombre-archivo', input.files[0].name);
        fileLabel.classList.add('mostrar-archivo');
      } else {
        fileLabel.setAttribute('data-nombre-archivo', "Seleccionar Archivo");
        fileLabel.classList.add('mostrar-archivo');
      }
    }
    
    <%
        if(err==1){%>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Hay un error con el archivo revise'
            });
            <%
        
        }else if(err==2){%>
            Swal.fire({
                icon: 'warning',
                title: 'Precaución',
                text: 'No se registraron <%=count%> alumnos'
            });
            <%
        }else if(err==3){%>
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Hay un error con los registros revise que sea el formato correcto'
            });
            <%
        }else if(err==4){%>
            Swal.fire({
                icon: 'success',
                title: 'Correcto',
                text: 'Se registraron todos los estudiantes'
            });
            <%
        }
    
    %>
    
    
  </script>
</body>

</html>