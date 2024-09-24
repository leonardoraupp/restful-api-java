package com.example.restapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtil {

    private String secretKey = "noneyet";
    private Algorithm algorithm = Algorithm.HMAC256(secretKey);

    //create a JWT
    public String generateToken(String subject) {
        String token = null;
        try {
            return token = JWT.create()
                    .withIssuer("auth0") //  Identifica a entidade principal que emitiu o JWT.
                    .withSubject(subject) // Identifica o usuário do JWT.
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
}
