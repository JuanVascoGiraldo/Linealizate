<%@page import="Control.Cifrado"%>
<%@page import="Modelo.Usuario"%>
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

            if(id_rol==3){//usuario
                 response.sendRedirect("./inicioestudiante.jsp");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
    String token = "";
    if(request.getParameter("token")== null){
        response.sendRedirect("index.html");
    }else{
        token = request.getParameter("token");
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
		<title>Linealizate</title>
		<!-- ImportaciÃ³n de estilos -->
		<link rel="stylesheet" href="./css/reset.css">
		<link rel="stylesheet" href="./css/index.css"> 
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
                                    <h1>Recuperar Contraseña</h1>
                                    <form action="" name="cambiarpasss">
                                            <div class="inputbox">
                                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                                    <input type="password"  name="password" id="password" required>
                                                    <label for="">Password</label>
                                            </div>

                                            <div class="inputbox">
                                                    <ion-icon name="lock-closed-outline"></ion-icon>
                                                    <input type="password"  name="confpassword" id="confpassword" required>
                                                    <label for="">Confirma Password</label>
                                            </div>
                                            <br>
                                            <div>
                                                <input type="hidden" id="token" value="<%=token%>">
                                            </div>
                                    </form>
                                    <button type="button"  class="enviar" onclick="ResContraV()">Cambiar</button>
                                </div>
				</div>
			</section>
			<div id="cambiar">
								
			</div>
			
			<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
			<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
			<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
			<script src="./js/validar.js"></script>
			
		</body>
</html>
