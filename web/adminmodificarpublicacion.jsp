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
  <link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <title>Publicar</title>
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
      <li><a href="">Inicio</a></li>
      <li><a href="">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <h3 class="header">Modificar publicacion</h3>
  <div class="formContainer">
    <div class="content">
      <form class="form" action="" name="modificar">
        <input class="input" type="text" name="Title" id="title" placeholder="Titulo">
        <br />
        <div class="inputContainer">
          <h5>Tipo de material: </h5>
          <select class="select" name="Tipo" id="tipo">
            <option disabled hidden value="0">Selecciona el tipo de material</option>
            <option value="1">Video</option>
            <option value="2">Infografia</option>
            <option value="3">Ejemplo</option>
            <option value="4">Examenes</option>
            <option value="5">Listas</option>
          </select>
        </div>
        <div class="inputContainer">
          <h5>Unidad: </h5>
          <select class="select" name="Unidad" id="unidad" onchange="javascript:imputcheck()">
            <option disabled hidden value="0">Selecciona la unidad</option>
            <option value="1">Unidad 1</option>
            <option value="2">Unidad 2</option>
            <option value="3">Unidad 3</option>
          </select>
        </div>
        <div class="checkboxContainer" id="cambiar1">
          <h5>Temas:</h5>
          <div class="checkbox">
            <div class="checkboxItem">
              <input type="checkbox" name="tema" id="tema1" value="1">
              <label for="tema1">Tema 1</label>
            </div>
            <div class="checkboxItem">
              <input type="checkbox" name="tema" id="tema2" value="2">
              <label for="tema1">Tema 2</label>
            </div>
            <div class="checkboxItem">
              <input type="checkbox" name="tema" id="tema3" value="3">
              <label for="tema1">Tema 3</label>
            </div>
            <div class="checkboxItem">
              <input type="checkbox" name="tema" id="tema4" value="4">
              <label for="tema1">Tema 4</label>
            </div>
          </div>
        </div>
        <br>
        <input class="input" type="url" name="link" id="link" placeholder="Link">
        <input class="input" type="text" placeholder="Bibliografia">
        <br>
        <div class="buttonContainer">
          <button class="button" type="button" onclick="Modificarpublicacion()">Moficar</button>
        </div>
      </form>
    </div>
  </div>
</body>

</html>