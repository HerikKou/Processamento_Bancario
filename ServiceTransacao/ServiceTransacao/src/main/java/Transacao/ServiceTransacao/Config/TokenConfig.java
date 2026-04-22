package Transacao.ServiceTransacao.Config;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


@Component
public class TokenConfig {

    private String secretKey = "ChaveSecreta";

    
    public Optional<JWTClienteData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return Optional.of(JWTClienteData.builder()
                    .id(decode.getClaim("clienteId").asLong())
                    .email(decode.getSubject())
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}