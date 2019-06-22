package core.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import core.application.exception.UnauthorizationException;
import core.domain.service.AuthQueryService;
import core.domain.service.UserQueryService;
import core.domain.write.model.Auth;
import core.domain.write.model.Password;
import core.domain.write.model.Token;
import core.domain.write.model.UserId;
import core.ui.controller.AuthController.LoginCommand;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import lombok.Value;

public class AuthApplicationService {

  private Config config;
  private AuthQueryService authQueryService;

  @Inject
  public AuthApplicationService(Config config, AuthQueryService authQueryService) {
    this.config = config;
    this.authQueryService = authQueryService;
  }

  public Token publishToken(UserId userId) {
    Algorithm algorithm = Algorithm.HMAC256(config.getString("secret.key"));
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

  public LoginResult login(LoginCommand loginCommand) {
    UserId userId = UserId.of(loginCommand.getUserId());
    Password password = Password.of(loginCommand.getPassword());

    Auth auth = authQueryService.findByUserId(userId);

    if (Objects.isNull(auth)) {
      throw new UnauthorizationException("user not found");
    }

    if (!Objects.equals(auth.getPassword(), password)) {
      throw new UnauthorizationException("password is incorrect");
    }

    Token token = publishToken(userId);

    return new LoginResult(userId, token);

  }

  @Value
  public static class LoginResult {

    private UserId userId;
    private Token token;
  }
}
