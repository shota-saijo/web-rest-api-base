package core.ui.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.application.UserApplicationService;
import io.javalin.Handler;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.eclipse.jetty.http.HttpStatus;

@Singleton
public class UserController implements Controller {

  @Inject
  private UserApplicationService applicationService;

  public Handler users() {
    return ctx -> ctx.json(applicationService.getUsers());
  }

  public Handler create() {
    return ctx -> {
      // body => command
      UserCreateCommand command = ctx.bodyValidator(UserCreateCommand.class)
          .check(c -> Objects.nonNull(c.getEmail()))
          .check(c -> c.getEmail().matches(".+@.+\\..+"))
          .check(c -> Objects.nonNull(c.getPassword()))
          .check(c -> c.getPassword().length() >= 8)
          .get();

      // command => result
      ctx.json(new CreatedResponse(applicationService.createUser(command).toString()));
      ctx.status(HttpStatus.CREATED_201);
    };
  }

  @Value
  public static class UserCreateCommand {
    private String email;
    private String password;
  }
}
