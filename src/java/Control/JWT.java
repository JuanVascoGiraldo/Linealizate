
package Control;

import Modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;

public class JWT {
    
    private static String SKJWT = "N}epP}sgLH;*3=?fN5]nW875G7U7;7gm";
    
    public static String generateJWT(int subject) {
        try{
            Date now = new Date();
            Date expiration = new Date(now.getTime() + 1800000); //media hora

            return Jwts.builder()
                    .setSubject(Cifrado.encrypt(String.valueOf(subject)))
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS384, SKJWT)
                    .compact();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "";
        
        }
        
    }
    
    
    public static int readJWT(String jwt) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SKJWT).parseClaimsJws(jwt).getBody();
            String subject = claims.getSubject();
            return Integer.valueOf(Cifrado.decrypt(subject));
        } catch (Exception e) {
            System.out.println("Error al verificar el JWT: " + e.getMessage());
            return -1;
        }
    }
    
    
    
    
    
    

}
