package core.ui.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.application.UserApplicationService;
import io.javalin.Handler;

@Singleton
public class UserController implements Controller {

  @Inject
  private UserApplicationService applicationService;

  public Handler users() {
    return ctx -> ctx.json(applicationService.getUsers());
  }
}
