package core.ui.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.application.UserApplicationService;
import io.javalin.Handler;
import java.util.Objects;
import lombok.Value;
import org.eclipse.jetty.http.HttpStatus;

@Singleton
public class UserController implements Controller {

  @Inject
  private UserApplicationService applicationService;

  public Handler list() {
    return ctx -> ctx.json(applicationService.getUsers());
  }

  public Handler get() {
    return ctx -> {};
  }

  public Handler create() {
    return ctx -> {
      // body => command
      CreateCommand command = ctx.bodyValidator(CreateCommand.class)
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

  public Handler changeName() {
    return ctx -> {
      ctx.status(HttpStatus.NO_CONTENT_204);
    };
  }

  public Handler changeEmail() {
    return ctx -> {
      ctx.status(HttpStatus.NO_CONTENT_204);
    };
  }

  public Handler delete() {
    return ctx -> {
      ctx.status(HttpStatus.NO_CONTENT_204);
    };
  }

  // ============================================================================================

  @Value
  public static class CreateCommand {
    private String email;
    private String password;
  }

  @Value
  public static class ChangeNameCommand {
    private String id;
    private String userName;
  }

  @Value
  public static class ChangeEmailCommand {
    private String id;
    private String email;
  }

  @Value
  public static class DeleteCommand {
    private String id;
  }
}
