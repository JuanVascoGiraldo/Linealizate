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
		<title>Linealizate</title>
		<!-- ImportaciÃ³n de estilos -->
		<link rel="stylesheet" href="./css/reset.css">
		<link rel="stylesheet" href="./css/index.css"> 
		<link rel="stylesheet" href="./css/modal.css"> 
		<link rel="shortcut icon" href="./multimedia/logoESCOMBlanco.png">
		<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	</head>

	<body>
		<header>
			<div>
				<a href="https://www.escom.ipn.mx/"><img src="./imagenes/escudoESCOM.png" class="logoESCOM"></a>
				<a href="https://www.ipn.mx/"><img src="./imagenes/logoIPN.png" class="logoIPN"></a>
			</div>
		</header>
			<section>  
				<div class="form-box">
					<div class="form-value">
							<h2>Linealizate</h2>
							<br>
							<br>
							<h1>Login</h1>
							<form action="" name="IS">
								<div class="inputbox">
										<ion-icon name="albums-outline"></ion-icon>
										<input type="number" name="boleta" id="boletaI" min="0" max="999999999"  required>
										<label for="">Numero de Boleta</label>
								</div>

								<div class="inputbox">
									<ion-icon name="lock-closed-outline"></ion-icon>
									<input type="password"  name="password" id="password" required>
									<label for="">Password</label>
								</div>
								<br>
								<div>
								
								</div>
							</form>
							<button type="button" onclick="ValidarIS()"  class="enviar"> Log in</button>
							<button  type="button" class="borrar" data-open="modalR1" >Recuperar</button>
					</div>
				</div>
			</section>
			<div id="cambiar1">
								
			</div>
			<div class="modal" id="modalR1">
				<div class="card">
					<form action="" name="recuperar">
						<label for="" class="article">Recuperar ContraseÃ±a</label><br>
						<br>
						<hr class="linea">
						<br>
						<label for="" class="article">Ingresa tu numero de boleta</label><br>
						<input type="number"  name="boleta" id="boletaR" min="0" max="999999999"  class ="input"required>
						<br>
						<button type="button" onclick="RecuparContra()" class="submit" id="enviar">Enviar</button>
					</form>
				<div id="cambiar2">
										
				</div>
				</div>
			</div>
			<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
			<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
			<script src="./js/validar.js"></script>
			<script src="./js/modal.js"></script>
			
		</body>
</html>
