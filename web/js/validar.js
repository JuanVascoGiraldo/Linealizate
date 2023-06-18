let expresionboleta = /^([0-9])*$/;
let expresioncorreo = /^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
let expresioncontra = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,40}$/;
let expresionnombre = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]+$/
let expresiontextnumber = /^[a-zA-Z0-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,\. \? \¿]+$/;
let expresionlink = /^(https?|ftp):\/\/[^\\s/$.?#].[^\\s]*$/;
let expresiondrive = /^https?:\/\/drive\.google\.com\/file\/d\/[\w-]+\/view\?usp=drive_link$/;
var expresionforms = /^https?:\/\/docs\.google\.com\/forms\/d\/e\/[\w-]+(\/viewform\?usp=sf_link)?$/;


var loading = "<script>"+
            "Swal.fire({"+
            "title: 'Datos enviados',"+
            "html: 'Los datos fueron enviados espere un momento',"+
           "timer: 100000,"+
            "timerProgressBar: true,"+
            "didOpen: () => {"+
              "Swal.showLoading()"+
            "}"+
          "})"+
    "</script>";


function validarcorreo(correo) {
    var validar = expresioncorreo.test(correo);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ingrese un correo valido'
        });
    }
    return validar;
}

function validarcontrasena(contrasena) {
    var validar = expresioncontra.test(contrasena);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Ingrese una contraseña valida',
            text: 'La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.'
        });
    }
    return validar;
}

function validarnombre(nombre) {
    var validar = expresionnombre.test(nombre);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ingrese un nombre valido (solo letras)'
        });
        return false;
    } else if (nombre.length > 60 || nombre.length === 0) {
        Swal.fire({
            icon: 'error',
            title: 'El tamaño del nombre no es correcto',
            text: 'El nombre tiene que medir entre 1 y 20 caracteres'
        });
        return false;
    } else {
        return true;
    }
}

function validarboleta(boleta, msg){
    var validar = expresionboleta.test(boleta);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La '+msg+' no es valida'
        });
    }else if(boleta.length !=10){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La Boleta no es valida'
        });
        validar = false
    }
    return validar;
}

function validarlink(link){
    var validar = expresionlink.test(link);
    var validar2 = expresiondrive.test(link);
    var validar3 = expresionforms.test(link);
    if (!validar && !validar2 && !validar3) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'El link no es valido'
            }); 
    }else if(link.length >200 || link.length<10){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El link no es valid'
        });
        validar = false
    }else{
        validar= true;
    }
    return validar;
}

function validarTitulo(titulo){
    var validar = expresiontextnumber.test(titulo);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El titulo no es valido'
        });
    }else if(link.length >200 || link.length<10){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El titulo no es valida'
        });
        validar = false
    }
    return validar;
}



function validarBibliografia(bibliografia){
    var validar = expresiontextnumber.test(bibliografia);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La bibliografia no es valido'
        });
    }else if(link.length >200 || link.length<10){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La bibliografia no es valida'
        });
        validar = false
    }
    return validar;
}


function ValidarIS(){
    let boleta = document.getElementById("boletaI").value;
    let pass = document.getElementById("password").value;
    if(validarboleta(boleta, "Boleta") &&( validarcontrasena(pass)||validarboleta(pass, "Contraseña"))){
        $.post('IniciarSesion', {
            boleta: boleta,
            password: pass
        }, function(responseText) {
                $('#cambiar1').html(responseText);
        });
        //si entra lo redirigimos a la pagina que va y si no pues mostramos un swal
    }
}

function RecuparContra(){
    let boleta = document.getElementById("boletaR").value;
    if(validarboleta(boleta)){
        $.post('CorreoRecuperar', {
            boleta: boleta
        }, function(responseText) {
                $('#cambiar2').html(responseText);
        });
    }
}

function filtro(){
    let tipo = document.getElementById("tipo").value;
    let unidad= document.getElementById("unidad").value;
    let tema= document.getElementById("tema").value;

    $.post('GetMaterial', {
        tipo, 
        unidad, 
        tema
    }, function(responseText) {
            $('#contenidos').html(responseText);
    });
}

function unidad(){
    let unidad= document.getElementById("unidad").value;
    $.post('Temaunidad', {
        unidad
    }, function(responseText) {
            $('#cambiar1').html(responseText);
    });
     setTimeout(function() {
         filtro();   
        }, 500);
    
}

function CambiarContra(){
    let password = document.getElementById("password").value;
    let confpassword= document.getElementById("passwordconf").value;

    if (password!= confpassword) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Las contraseñas no coinciden'
        });
    } else if(validarcontrasena(password) && validarcontrasena(confpassword)){
        $.post('CambiarContra', {
            password
        }, function(responseText) {
                $('#cambiar2').html(responseText);
        });
    }
}

function imputcheck(tipo){
    let unidad= document.getElementById("unidad").value;
    $.post('GetTemas', {
        unidad
    }, function(responseText) {
            $('#cambiar1').html(responseText);
    });
    setTimeout(function() {
            if(tipo == 2){
                seleccionarTemas();
            }
        }, 500);
}

function Publicar(){
    let titulo =  document.getElementById("title").value;
    let tipo =  document.getElementById("tipo").value;
    let unidad =  document.getElementById("unidad").value;
    let link =  document.getElementById("link").value; 
    let bibliografia =  document.getElementById("Bibliografia").value;
    let temas = [];
    let Strtemas = "";
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            temas.push(checkbox.value);
            Strtemas += checkbox.value+",";
        }
    });
    if(temas.length ==0 ){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Selecciona un tema'
        });
    }else if(validarTitulo(titulo) && validarBibliografia(bibliografia) && validarlink(link) && tipo!=0 && unidad!=0){
        $('#notificacion').html(loading);

        $.post('publicar', {
            titulo,
            bibliografia,
            unidad,
            link,
            tipo,
            temas: Strtemas
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
    }
}

function Modificarpublicacion(id){
    let titulo =  document.getElementById("title").value;
    let tipo =  document.getElementById("tipo").value;
    let unidad =  document.getElementById("unidad").value;
    let link =  document.getElementById("link").value; 
    let bibliografia =  document.getElementById("Bibliografia").value;
    let temas = [];
    let Strtemas = "";
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            temas.push(checkbox.value);
            Strtemas += checkbox.value+",";
        }
    });
    if(temas.length ==0 ){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Selecciona un tema'
        });
    }else if(validarTitulo(titulo) && validarBibliografia(bibliografia) && validarlink(link) && tipo!=0 && unidad!=0){
        $('#notificacion').html(loading);

        $.post('modificar', {
            id,
            titulo,
            bibliografia,
            unidad,
            link,
            tipo,
            temas: Strtemas
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
    }
}

function RegistrarEstudiante(){
    let nombre =  document.getElementById("nombre").value;
    let boleta =  document.getElementById("boleta").value;
    let correo =  document.getElementById("correo").value;
    if(validarboleta(boleta, "Boleta") && validarnombre(nombre) && validarcorreo(correo)){
        reg();
        $('#notificacion').html(loading);

        $.post('RegistrarEstudiante', {
            nombre,
            boleta,
            correo
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
        setTimeout(function() {
            ObtenerEstudiante();
        }, 2000);
    }
}


function ModificarEstudiante(){
    let id = document.getElementById("idMod").value;
    let nombre =  document.getElementById("nomMod").value;
    let boleta =  document.getElementById("boletaMod").value;
    let correo =  document.getElementById("correoMod").value;
    if(validarboleta(boleta, "Boleta") && validarnombre(nombre) && validarcorreo(correo)){
        mod();
        $('#notificacion').html(loading);

        $.post('ModificarEstudiante', {
            id,
            nombre,
            boleta,
            correo
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
        setTimeout(function() {
            ObtenerEstudiante();
        }, 2000);
        
    }
}

function ObtenerEstudiante(){
    $.post('obtenerEstudiantes', {
        msg: "obtener"
    }, function(responseText) {
            $('#Estudiantes').html(responseText);
    });
}

function FiltrarMat(tipo, unidad, tema){
    $.post('GetMaterial', {
        tipo,
        unidad,
        tema
    }, function(responseText) {
            $('#material').html(responseText);
    });
}

function ObtenerMat(){
    $.post('Material', {
        msg:"go"
    }, function(responseText) {
            $('#material').html(responseText);
    });
}

function Estado(id){
    $('#notificacion').html(loading);
    $.post('CambiarEstado', {
        id
    }, function(responseText) {
            $('#notificacion').html(responseText);
    });
    
}

function Cerrarsesion(){
    location.href = "./CerrarSesion";
    
}

function EnviarModE(id){
    let ide = document.getElementById("idE");
    ide.value= id;
    
}

function EnviarModF(id, nombre, correo, boleta){
    console.log("miau")
    document.getElementById("idMod").value= id;
    document.getElementById("nomMod").value= nombre;
    document.getElementById("correoMod").value= correo;
    document.getElementById("boletaMod").value= boleta;
    
}

function Eliminar(){
    crm2();
    $('#notificacion').html(loading);
    let ide = document.getElementById("idE").value;
    $.post('EliminarEstudiante', {
            eliminar: ide
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
    setTimeout(function() {
            ObtenerEstudiante();
        }, 2000);
}

function ResContraV(){
    let password = document.getElementById("password").value;
    let confpassword= document.getElementById("confpassword").value;
    let token = document.getElementById("token").value;

    if (password!= confpassword) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Las contraseñas no coinciden'
        });
    } else if(validarcontrasena(password) && validarcontrasena(confpassword)){
        $('#cambiar').html(loading);
        $.post('Recontra', {
            password,
            token
        }, function(responseText) {
                $('#cambiar').html(responseText);
        });
    }
 }
 
 function Modificar(id, tip){
     let encodedValor = encodeURIComponent(id);
     if(tip==1){
         location.href = "./adminmodificarpublicacion.jsp?id="+encodedValor;
     }else{
         location.href = "./modificarpublicacion.jsp?id="+encodedValor;
     }
     
     
 }
 function  verMat(id){
     let encodedValor = encodeURIComponent(id);
     location.href = "./vermaterial.jsp?id="+encodedValor;
 }
 
 function EliminarGrupo(){
    crm();
    $('#notificacion').html(loading);
    $.post('EliminarGrupo', {
            msg: 'where Rol = ASDqWcasd=1as!'
        }, function(responseText) {
                $('#notificacion').html(responseText);
        });
    setTimeout(function() {
            ObtenerEstudiante();
        }, 2000);
 }
 
 