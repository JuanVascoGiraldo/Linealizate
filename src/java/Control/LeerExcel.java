
package Control;

import Modelo.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.*;


public class LeerExcel {
    public static List<Usuario> LeerArchivo(File filename){
        List<Usuario> lista = new ArrayList<>();
        try{
            FileInputStream FIP = new  FileInputStream(filename);
            XSSFWorkbook wb = new XSSFWorkbook(FIP);
            XSSFSheet sh = wb.getSheetAt(0);
            Iterator rowI = sh.rowIterator();
            while(rowI.hasNext()){
                XSSFRow hsr = (XSSFRow) rowI.next();
                Iterator ite = hsr.cellIterator();
                //https://www.youtube.com/watch?v=nUnCSwIkss8
            }
        
        }catch(Exception e){
            System.out.println(e.getMessage());
        
        }
        return lista;
    }
}
