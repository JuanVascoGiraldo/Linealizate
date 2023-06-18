
package Control;

import Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;


public class LeerExcel {
    public static List<Usuario> LeerArchivo(File filename){
        List<Usuario> lista = new ArrayList<>();
        try{
            FileInputStream FIP = new  FileInputStream(filename);
            XSSFWorkbook wb = new XSSFWorkbook(FIP);
            XSSFSheet sh = wb.getSheetAt(0);
            int numero_filas = sh.getLastRowNum();
            for(int i=1; i<=numero_filas; i++){
                Row fila = sh.getRow(i);
                Usuario nuevo = new Usuario();
                int numero_colum = fila.getLastCellNum();
                for(int j=0; j< numero_colum; j++){
                    Cell celda = fila.getCell(j);
                    if(j==0){
                        double boleta = celda.getNumericCellValue();
                        nuevo.setBoleta(Math.round(boleta));
                    }
                    
                    if(j==1){
                        nuevo.setNombre(celda.getStringCellValue().toLowerCase());
                    }
                    
                    if(j==2){
                        nuevo.setEmail(celda.getStringCellValue());  
                }}
                lista.add(nuevo);
            }
            FIP.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        
        }
        return lista;
    }
}
