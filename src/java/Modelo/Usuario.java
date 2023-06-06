package Modelo;


public class Usuario {
    long boleta;
    int id, rol;
    String nombre, password, email, id_cifrado;

    public Usuario() {
    }

    public long getBoleta() {
        return boleta;
    }

    public void setBoleta(long boleta) {
        this.boleta = boleta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_cifrado() {
        return id_cifrado;
    }

    public void setId_cifrado(String id_cifrado) {
        this.id_cifrado = id_cifrado;
    }
    
    
    
    
}
