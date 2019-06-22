package core.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import core.domain.write.model.Token;
import core.domain.write.model.UserId;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class AuthApplicationService {

  private Config config;

  @Inject
  public AuthApplicationService(Config config) {
    this.config = config;
  }

  public Token publishToken(String userId) {
    Algorithm algorithm =  Algorithm.HMAC256(config.getString("secret.key"));
    String token = JWT.create()
        .withIssuer(config.getString("app.name"))
        .withClaim("userId", userId.toString())
        .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.UTC)))
        .sign(algorithm);
    return new Token(token);
  }

  public UserId verifyToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(config.getString("secret.key"));
    JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer(config.getString("app.name"))
        .build();
    DecodedJWT jwt = verifier.verify(Token.ofByHeader(token).toString());
    return UserId.of(jwt.getClaim("userId").asString());
  }
}
