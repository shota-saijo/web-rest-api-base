package core.ui.controller;

import com.google.inject.Inject;
import core.application.AuthApplicationService;
import core.application.AuthApplicationService.LoginResult;
import io.javalin.Handler;
import java.util.Objects;
import lombok.Value;
import org.eclipse.jetty.http.HttpStatus;

public class AuthController implements Controller {

  @Inject
  private AuthApplicationService authApplicationService;

  public Handler login() {
    return ctx -> {
      // body => command
      LoginCommand command = ctx.bodyValidator(LoginCommand.class)
          .check(c -> Objects.nonNull(c.getEmail()))
          .check(c -> Objects.nonNull(c.getPassword()))
          .get();

      // command => result
      LoginResult result = authApplicationService.login(command);

      ctx.status(HttpStatus.OK_200);
      ctx.json(new LoginResponse(result.getUserId().toString(), result.getToken().toString()));
    };
  }

  // ============================================================================================

  @Value
  public class LoginCommand {
    private String email;
    private String password;
  }

  @Value
  public class LoginResponse {
    private String userId;
    private String token;
  }
}
