package Cliente.ServiceCliente.Config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import Cliente.ServiceCliente.Model.Cliente;

@Component
public class TokenConfig {

    private String secretKey = "ChaveSecreta";

    public String generatedToken(Cliente cliente) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withClaim("clienteId", cliente.getId())
                .withSubject(cliente.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

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