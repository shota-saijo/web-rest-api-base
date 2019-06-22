package core.ui.route;

import static io.javalin.apibuilder.ApiBuilder.path;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.javalin.Javalin;
import io.javalin.security.AccessManager;
import java.util.Collections;
import java.util.Set;

@Singleton
public class Endpoint {

  @Inject(optional = true)
  private Set<Route> routes = Collections.emptySet();

  @Inject
  protected Javalin app;

  @Inject
  private AccessManager accessManager;

  public void boot() {
    bindRoutes();
  }

  private void bindRoutes() {

    app.accessManager(accessManager);

    routes.forEach(route ->
        app.routes(() ->
            path(route.getContextPath(), route.bindRoutes())
        )
    );
  }

}
