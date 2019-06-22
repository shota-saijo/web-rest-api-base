package core.ui.route;

import io.javalin.apibuilder.EndpointGroup;

public abstract class Route {

  public abstract String getContextPath();

  public abstract EndpointGroup bindRoutes();
}
