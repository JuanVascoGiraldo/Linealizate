package Control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validacion {
    
    private static final String ExpCorreo = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private static final String Expnombre = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ]+$";
    private static final String ExpContra = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,40}$";
    private static final String Exptexto = "^[a-zA-Z0-9àáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑ \\? \\¿,\\.[\\]()]+$";
    private static final String ExpFechapre = "^(?:3[01]|[12][0-9]|0?[1-9])([\\-/.])(0?[1-9]|1[1-2])\\1\\d{4}$";
    private static final String ExpFiltro = "^\\d{2}$";
    private static final String ExpBoleta = "^\\d{10}$";
    private static final String ExpLink = "^(https?|ftp):\\/\\/[^\\s/$.?#].[^\\s]*$";
    
    
    public static boolean Validarcorreo(String correo){
        Pattern pattern = Pattern.compile(ExpCorreo);
        Matcher matcher = pattern.matcher(correo);
        if(matcher.matches()){
           if(correo.length() > 60 || correo.length() < 10){
               System.out.println("Tamaño del coreeo no valido");
               return false;
           }else{
               return true;
           }
        }else{
            System.out.println("Correo no valido");
            return false;
        }
      }
    
    public static boolean Validarcontra(String contra){
        Pattern pattern = Pattern.compile(ExpContra);
        Matcher matcher = pattern.matcher(contra);
        return matcher.matches();
      }
    
    public static boolean Validarnombre(String nombre){
        Pattern pattern = Pattern.compile(Expnombre);
        Matcher matcher = pattern.matcher(nombre);
        if(matcher.matches()){
           if(nombre.length() > 60 || nombre.length() == 0){
               return false;
           }else{
               return true;
           }
        }else{
            return false;
        }
      }
   
    public static boolean ValidarBoleta(String boleta){
        Pattern pattern = Pattern.compile(ExpBoleta);
        Matcher matcher = pattern.matcher(boleta);
        if(matcher.matches()){
               return true;
        }else{
            return false;
        }
    }
    
    public static boolean ValidarTitulo(String titulo){
        Pattern pattern = Pattern.compile(Exptexto);
        Matcher matcher = pattern.matcher(titulo);
        if(matcher.matches()){
           if(titulo.length() > 100 || titulo.length() == 0){
               return false;
           }else{
               return true;
           }
        }else{
            return false;
        }
    }
    
    public static boolean ValidarLink(String link){
        Pattern pattern = Pattern.compile(ExpLink);
        Matcher matcher = pattern.matcher(link);
        if(matcher.matches()){
           if(link.length() > 200 || link.length() < 10){
               return false;
           }else{
               return true;
           }
        }else{
            return false;
        }
    }
    
    public static boolean ValidarBibliografia(String biblio){
        Pattern pattern = Pattern.compile(Exptexto);
        Matcher matcher = pattern.matcher(biblio);
        if(matcher.matches()){
           if(biblio.length() > 150 || biblio.length() ==0){
               return false;
           }else{
               return true;
           }
        }else{
            return false;
        }
    }
    
    public static boolean ValidarReporte(String reporte){
        Pattern pattern = Pattern.compile(Exptexto);
        Matcher matcher = pattern.matcher(reporte);
        if(matcher.matches()){
           if(reporte.length() > 150 || reporte.length() == 0){
               return false;
           }else{
               return true;
           }
        }else{
            return false;
        }
    }
}
