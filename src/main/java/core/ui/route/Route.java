package core.ui.route;

import com.google.inject.Inject;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public abstract class Route {
  @Inject
  protected Javalin app;

  public abstract String getContextPath();

  public abstract EndpointGroup bindRoutes();
}
