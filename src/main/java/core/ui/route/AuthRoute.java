package core.ui.route;

import static io.javalin.apibuilder.ApiBuilder.post;

import com.google.inject.Inject;
import core.ui.controller.AuthController;
import io.javalin.apibuilder.EndpointGroup;

public class AuthRoute extends Route {

  @Inject
  private AuthController controller;

  @Override
  public String getContextPath() {
    return "auth";
  }

  @Override
  public EndpointGroup bindRoutes() {
    return () -> {
      post("login", controller.login());
    };
  }
}
