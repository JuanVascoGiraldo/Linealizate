let expresionboleta = /^([0-9])*$/;
let expresioncorreo = /^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$/;
let expresioncontra = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,40}$/;
let expresionnombre = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]+$/
let expresiontextnumber = /^[a-zA-Z0-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,\. \? \¿]+$/;
let expresionlink = /^(https?|ftp):\/\/[^\\s/$.?#].[^\\s]*$/;

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
    var validar = expresiononlytext.test(nombre);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Ingrese un nombre valido (solo letras)'
        });
        return false;
    } else if (nombre.length > 60 || nombre.length == 0) {
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

function validarboleta(boleta){
    var validar = expresionboleta.test(boleta);
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La Boleta no es valida'
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
    if (!validar) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'El link no es valido'
        });
    }else if(link.length >200 || link.length<10){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'La Boleta no es valida'
        });
        validar = false
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
    if(validarboleta(boleta) && validarcontrasena(pass)){
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
        $.post('Recuperar', {
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

    $.post('ObtenerMaterial', {
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
    filtro();
}

function CambiarContra(){
    let password = document.getElementById("password").value;
    let confpassword= document.getElementById("passwordconf").value;

    if (pass != confpas) {
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

function imputcheck(){
    let unidad= document.getElementById("unidad").value;
    $.post('Temaunidad', {
        unidad
    }, function(responseText) {
            $('#cambiar1').html(responseText);
    });
}

function Publicar(){
    let titulo =  document.getElementById("title").value;
    let tipo =  document.getElementById("tipo").value;
    let unidad =  document.getElementById("unidad").value;
    let link =  document.getElementById("link").value; 
    let bibliografia =  document.getElementById("bibliografia").value;
    let temas = [];
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            temas.push(checkbox.value);
        }
    });
    if(temas.length ==0 ){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Selecciona un tema'
        });
    }else if(validarTitulo(titulo) && validarBibliografia(bibliografia) && validarlink(link) && tipo!=0 && unidad!=0){
        document.publicar.submit();
    }
}

function Modificarpublicacion(){
    let titulo =  document.getElementById("title").value;
    let tipo =  document.getElementById("tipo").value;
    let unidad =  document.getElementById("unidad").value;
    let link =  document.getElementById("link").value; 
    let bibliografia =  document.getElementById("bibliografia").value;
    let temas = [];
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            temas.push(checkbox.value);
        }
    });
    if(temas.length ==0 ){
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Selecciona un tema'
        });
    }else if(validarTitulo(titulo) && validarBibliografia(bibliografia) && validarlink(link) && tipo!=0 && unidad!=0){
        document.modificar.submit();
    }
}

function RegistrarEstudiante(){
    let nombre =  document.getElementById("nombre").value;
    let boleta =  document.getElementById("boleta").value;
    let correo =  document.getElementById("correo").value;
    if(validarboleta(boleta) && validarnombre(nombre) && validarcorreo(correo)){
        $('#registro').html(loading);

        $.post('RegistrarEstudiante', {
            nombre,
            boleta,
            correo
        }, function(responseText) {
                $('#registro').html(responseText);
        });
    }
}


function ModificarEstudiante(){
    let nombre =  document.getElementById("nombre").value;
    let boleta =  document.getElementById("boleta").value;
    let correo =  document.getElementById("correo").value;
    if(validarboleta(boleta) && validarnombre(nombre) && validarcorreo(correo)){
        $('#modifi').html(loading);

        $.post('ModificarEstudiante', {
            nombre,
            boleta,
            correo
        }, function(responseText) {
                $('#modifi').html(responseText);
        });
    }
}

function ObtenerEstudiante(){
    $.post('obtenerEstudiantes', {
        msg: "obtener"
    }, function(responseText) {
            $('#Estudiantes').html(responseText);
    });
}





