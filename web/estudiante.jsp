<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java"%>

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
      <a href="principal.html">
        <img src="multimedia/logoESCOMBlanco.png" alt="principal" class="logo">
      </a>
      <h5 class="titulo">Linealizate</h5>
    </div>
    <ul>
      <li><a href="">Estudiantes</a></li>
      <li><a href="">Publicaciones</a></li>
      <li><a href="#" data-open="modalR1" >ContraseÃ±a</a></li>
      <li><a href="#">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <div class="arriba">
    <button class="ac" data-open="modalR1">Agregar Estudiante</button>
    <button class="ch" data-open="modalEstu">Registrar Grupo</button>
    <button class="cs" data-open="modal31">Eliminar Grupo</button>
  </div>
  
  <div class="alinear">
    <div class="flex">
        <div class="m">
            <img src="./imagenes/estudiar.png">
        </div>
        <div></div>
        <div class="text">
            <h3>Juan esteban vasco firaldo</h3>
            <hr>
            <h4>juan@gmail.com</h4>
            <h4>1010101001</h4>
        </div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
    <div class="btn">
        <button class="cs" data-open="modal3">Eliminar</button>
        <button onclick="" class="mc" data-open="modalMod">Modificar</button>
    </div>
  </div>

  <div class="alinear">
    <div class="flex">
        <div class="m">
            <img src="./imagenes/estudiar.png">
        </div>
        <div></div>
        <div class="text">
            <h3>Juan esteban vasco firaldo</h3>
            <hr>
            <h4>juan@gmail.com</h4>
            <h4>1010101001</h4>
        </div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
    <div class="btn">
        <button class="cs" data-open="modal3">Eliminar</button>
        <button onclick="" class="mc" data-open="modalMod">Modificar</button>
    </div>
  </div>

  <div class="modal" id="modalEstu">
    <div class="card">
        <form action="" name="excel">
            <label for="" class="article">Registrar grupo</label><br>
            <hr class="linea">
            <label for="" class="article">Ingresa el archivo excel de los estudiantes</label>
            <br>
            <br>
            <div class="file-select" id="src-file1" >
              <input type="file" name="src-file1" aria-label="Archivo" accept=".xlsx, .xls" onchange="mostrarNombreArchivo(this)">
            </div>
            <br>
            <br>
            <button type="button" class="submit" id="enviar" onclick="document.excel.submit()">Registrar</button>
        </form>
        <div id="excel">

        </div>
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
        <div id="registro">

        </div>
    </div>
  </div>

  <div class="modal" id="modalMod">
    <div class="card">
        <form action="" name="modificar">
            <label for="" class="article">Modificar Estudiante</label><br>
            <br>
            <hr class="linea">
            <br>
            <input type="text" name="nomMod" id="nomMod" placeholder="Nombre del Estudiante" class="input" value="">
            <input type="text" name="correoMod" id="correoMod" placeholder="Correo electronico" class="input" value="">
            <input type="number" name="boletaMod" id="boletaMod" placeholder="Numero de boleta" class="input" min="2" max="9999999999"value="">
            <br>
            <button type="button" class="submit" id="enviar" onclick="ModificarEstudiante()">Modificar</button>
        </form>
        <div id="modifi">

        </div>
    </div>
  </div>

  <div class="modal" id="modal3">
      <div class="card">
          <form name="deshabilitar" action=""><input type="hidden" name="id" id="id" value=""></form>
          <h4>Cuidado, esta acciÃ³n podrÃ­a afectar el flujo del sistema</h4>
          <button class="cs" onclick="Deshabilitar()">Eliminar Cuenta</button>
          <button class="submit"  onclick="crm2()">No inhabilitar cuenta</button>
      </div>
  </div>

  <div class="modal" id="modal31">
    <div class="card">
        <h4>Cuidado, esta acciÃ³n podrÃ­a afectar el flujo del sistema</h4>
        <button class="cs" onclick="EliminarGrupo()">Eliminar Grupo</button>
        <button class="submit" onclick="crm()">No inhabilitar Grupo</button>
    </div>
</div>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
	<script src="./js/modal.js"></script>
  <script>
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
  </script>
</body>

</html>