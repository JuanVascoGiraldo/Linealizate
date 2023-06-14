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
        <li><a href="">volver</a></li>
        <li><a href="#">Cerrar Sesion</a></li>
        <li class="fa-solid fa-user" style="color: #FFF;"></li>
    </ul>
    </nav>
    <h4 class="header2">Titulo ooooo</h4>
    <h4 class="header2">Tipo de video</h4>
    <h4 class="header2">Unidad</h4>
    <h4 class="header2">Temas 1, Temas 2, Temas 3</h4>
    <br>
    <div class="video">
       <iframe width="100%" height="315" src="https://www.youtube.com/watch?v=iogcY_4xGjo" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe> 
    </div>
    
    <iframe src="file:///C:/Users/Juanv/Downloads/Lista%203%20MyE.pdf" width="80%" height="600px" style="margin-left: 10%;"></iframe>
    
    <br>
    <h4 class="header2">Bibliografia</h4>
    
    <h4 class="header2">Videos de los temas</h4>







    
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="./js/validar.js"></script>
</div>
</body>

</html>