package fidelity.clase6.configuration;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtils implements Serializable {
    
    private final String secret = "fidelity";
    private Date expirationDate = new Date(System.currentTimeMillis() + 604800L + 1000);

    public String generateToken(HashMap<String, Object> claims) throws UnsupportedEncodingException {
        //Date expirationDate = new Date(System.currentTimeMillis() + 604800L + 1000);
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8")).
        setClaims(claims).
        setExpiration(expirationDate).
        compact();
    }
    
    public String generateToken(UserDetails userDetails) throws UnsupportedEncodingException {
        HashMap<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).
                setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8")).compact();
    }

    public boolean validateToken(String token) throws UnsupportedEncodingException {
        try {
            Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Error en la firma del token JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException  ex) {
            System.err.println("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException  ex) {
            System.err.println("WT claims vacío " + ex.getMessage());
        }

        return false;
    }

}
