package core.ui.route;

import static core.domain.write.model.User.UserRole.PUBLIC;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.security.SecurityUtil.roles;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.ui.controller.UserController;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.Role;
import java.util.Set;

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
      get(controller.list(), roles(PUBLIC));
      get(":id", controller.get(), roles(PUBLIC));
      post(controller.create());
      patch(":id/change-name", controller.changeName(), roles(PUBLIC));
      patch(":id/change-email", controller.changeEmail(), roles(PUBLIC));
    };
  }
}
