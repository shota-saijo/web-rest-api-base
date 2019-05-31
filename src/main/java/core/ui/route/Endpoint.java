package core.ui.route;

import static io.javalin.apibuilder.ApiBuilder.path;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Collections;
import java.util.Set;

@Singleton
public class Endpoint {

  @Inject(optional = true)
  private Set<Route> routes = Collections.emptySet();

  public void boot() {
    bindRoutes();
  }

  private void bindRoutes() {
    routes.forEach(route ->
        route.app.routes(() ->
            path(route.getContextPath(), route.bindRoutes())
        )
    );
  }

}
