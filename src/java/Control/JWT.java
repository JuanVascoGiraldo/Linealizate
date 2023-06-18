
package Control;

import Modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import java.util.Map;

public class JWT {
    private static Map<String, String> env = System.getenv();
    private static String SKJWT = env.get("JWT");
    
    public static String generateJWT(int subject) {
        try{
            Date now = new Date();
            Date expiration = new Date(now.getTime() + 1800000); //media hora

            return Jwts.builder()
                    .setSubject(Cifrado.encrypt(String.valueOf(subject)))
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, SKJWT)
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
