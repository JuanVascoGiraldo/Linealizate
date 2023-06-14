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
      <a href="principal.html">
        <img src="multimedia/logoESCOMBlanco.png" alt="principal" class="logo">
      </a>
      <h5 class="titulo">Linealizate</h5>
    </div>
    <ul>
      <li><a href="">Inicio</a></li>
      <li><a href="#">Cerrar Sesion</a></li>
      <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
  </nav>
  <h3 class="header2">Material</h3>
  <div class="formContainer">
    <div class="content2">
        <div class="inputContainer">
          <h5>Tipo de material: </h5>
          <select class="select" name="Tipo" id="tipo" onchange="javascript:filtro()">
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
          <select class="select2" name="Unidad" id="unidad" onchange="javascript:unidad()">
            <option disabled hidden value="0">Selecciona la unidad</option>
            <option value="1">Unidad 1</option>
            <option value="2">Unidad 2</option>
            <option value="3">Unidad 3</option>
          </select>
        </div>
        <div id="cambiar1">
          <div class="inputContainer">
            <h5>Tema </h5>
            <select class="select2" name="tema" id="tema" onchange="javascript:filtro()">
              <option disabled hidden value="0">Selecciona el tema de tu interes</option>
              <option value="1">EliminaciÃ³n de Gauss y de Gauss-Gordan con pivoteo</option>
              <option value="2">RepresentaciÃ³n matricial de un sistema de ecuaciones</option>
              <option value="3">CÃ¡lculo de determinantes</option>
              <option value="4">Inversa de una Matriz</option>
              <option value="5">La inversa de una matriz a travÃ©s de su adjunta</option>
              <option value="6">SoluciÃ³n de sistema de ecuaciones lineales</option>
              <option value="7">Espacios vectoriales de distintos gÃ©neros</option>
              <option value="8">Subespacios vectoriales de distintos gÃ©neros</option>
              <option value="9">CombinaciÃ³nes lineales</option>
              <option value="10">Espacio generado</option>
              <option value="11">Dependencia e independencia lineal</option>
              <option value="12">Bases de un Espacio Vectorial</option>
              <option value="13">DimensiÃ³n de un Espacio Vectorial</option>
              <option value="14">Rango y Nulidad de una matriz</option>
              <option value="15">Matriz Cambio de Base</option>
              <option value="16">Proceso de ortonormalizaciÃ³n de Gram-Schmid</option>
              <option value="17">Propiedades de las Transformaciones Lineales</option>
              <option value="18">Imagen y Kernel de una transformaciÃ³n lineal</option>
              <option value="19">Isomorfismos</option>
              <option value="20">CÃ¡lculo de vectores caracteristicos</option>
              <option value="21">DiagonalizaciÃ³n de matrices</option>
              <option value="22">Matrices SimÃ©tricas y diagonalizaciÃ³n ortogonal</option>
              <option value="23">Formas CuadrÃ¡ticas y secciones cÃ³nicas</option>
              <option value="24">Aplicaciones a Ecuaciones diferenciales matriciales</option>
              <option value="25">RepresentaciÃ³n matricial de una transformaciÃ³n lineal</option>
              <option value="26">Sistema de ecuaciones lineales homogÃ©neas</option>
            </select>
          </div>
        </div>
        
    </div>
  </div>
  <div id="contenidos">

  </div>
  <div class="main_container">
      <div class="mini_header">
        &nbsp;
        &nbsp;<h2>Videos</h2>
          <h2>Unidad</h2>
          &nbsp;
          &nbsp;
      </div>
      <div class="titulomat">
          <h3>Calculo de determinantes</h3>
          <br>
          <h2>tema 1</h2>
          <br>
          <h2>tema 2</h2>
      </div>
      <div class="varmat">
          <a href="./vermaterial.html" class="ira">Ver material</a>
      </div>
      <br>
  </div>




    
  <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
</div>
</body>

</html>