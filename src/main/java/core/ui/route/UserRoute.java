package core.ui.route;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.ui.controller.UserController;
import io.javalin.apibuilder.EndpointGroup;

@Singleton
public class UserRoute extends Route {

  @Inject
  private UserController controller;

  @Override
  public String getContextPath() {
    return "users";
  }

  @Override
  public EndpointGroup bindRoutes() {
    return () -> {
      get(controller.users());
    };
  }
}
