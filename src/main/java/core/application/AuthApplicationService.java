package core.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import core.application.exception.UnauthorizationException;
import core.domain.read.model.Auth;
import core.domain.service.AuthQueryService;
import core.domain.service.UserQueryService;
import core.domain.write.model.Token;
import core.domain.write.model.UserId;
import core.ui.controller.AuthController.LoginCommand;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthApplicationService {

  private Config config;
  private UserQueryService userQueryService;
  private AuthQueryService authQueryService;

  @Inject
  public AuthApplicationService(Config config, UserQueryService userQueryService, AuthQueryService authQueryService) {
    this.config = config;
    this.userQueryService = userQueryService;
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
    Auth auth = authQueryService.findByEmail(loginCommand.getEmail());

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (!encoder.matches(loginCommand.getPassword(), auth.getPassword())) {
      throw new UnauthorizationException("password is incorrect");
    }

    UserId userId = UserId.of(auth.getUserId());
    Token token = publishToken(userId);

    return new LoginResult(userId, token);

  }

  @Value
  public static class LoginResult {

    private UserId userId;
    private Token token;
  }
}
