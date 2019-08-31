package core.ui.controller;

import static core.util.MiscUtil.hasValue;
import static core.util.MiscUtil.isNullOrEmpty;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.application.UserApplicationService;
import core.application.command.UserCommand.ChangeEmailCommand;
import core.application.command.UserCommand.ChangeNameCommand;
import core.application.command.UserCommand.CreateCommand;
import core.application.model.UserContext;
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
          .check(c -> hasValue(c.getEmail()))
          .check(c -> c.getEmail().matches(".+@.+\\..+"))
          .check(c -> isNullOrEmpty(c.getPassword()))
          .check(c -> c.getPassword().length() >= 8)
          .get();

      // command => result
      ctx.json(new CreatedResponse(applicationService.createUser(command).toString()));
      ctx.status(HttpStatus.CREATED_201);
    };
  }

  public Handler changeName() {
    return ctx -> {
      ChangeNameCommand command = ctx.bodyValidator(ChangeNameCommand.class)
          .check(c -> hasValue(c.getUserName()))
          .get();

      applicationService.changeName(ctx.pathParam("id"), command);
      ctx.status(HttpStatus.NO_CONTENT_204);
    };
  }

  public Handler changeEmail() {
    return ctx -> {
      ChangeEmailCommand command = ctx.bodyValidator(ChangeEmailCommand.class)
          .check(c -> hasValue(c.getEmail()))
          .get();

      applicationService.changeEmail(ctx.pathParam("id"), command);
      ctx.status(HttpStatus.NO_CONTENT_204);
    };
  }
}
