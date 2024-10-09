package com.example.restapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    Dotenv dotenv = Dotenv.load();
    private String secretKey = dotenv.get("SECRET_KEY");
    private Algorithm algorithm = Algorithm.HMAC256(secretKey);

//    //create a JWT
    public String generateToken(UserDetails userDetails) {
        String token = null;
        try {
            return token = JWT.create()
                    .withIssuer("auth0") //  Identifica a entidade principal que emitiu o JWT.
                    .withSubject(userDetails.getUsername()) // Identifica o usuário do JWT.
                    .withIssuedAt(new Date()) // Data de emissão do JWT.
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10h de expiração do JWT.
                    .sign(algorithm); // Algoritmo de que criptografa o payload(os clains).
        } catch (
                JWTCreationException exception) {
            System.out.println(exception.getMessage());
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    //verify a JWT
    public DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build(); // Cria um verificador com o algoritmo.
            return verifier.verify(token); // Verifica o token no objeto vericador.
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());

        }
        return null;
    }


    public String getUsername(String token) { // extract username
        return JWT.decode(token).getSubject(); // get the username
    }

    public String getClaims(String token) {
        try {
            return JWT.decode(token).getPayload();
        } catch (JWTDecodeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean isTokenExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public  Date getExpiration(String token) { // get the expiration time.
        return JWT.decode(token).getExpiresAt();
    }
}
