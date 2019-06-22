package core.ui.route;

import static io.javalin.apibuilder.ApiBuilder.path;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import core.application.exception.TokenInvalidException;
import core.application.exception.UnauthorizationException;
import io.javalin.Javalin;
import io.javalin.security.AccessManager;
import java.util.Collections;
import java.util.Set;
import org.eclipse.jetty.http.HttpStatus;

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

    app.exception(TokenInvalidException.class, (exception, ctx) -> {
      ctx.status(HttpStatus.UNAUTHORIZED_401);
      ctx.result(exception.getMessage());
    });

    app.exception(UnauthorizationException.class, (exception, ctx) -> {
      ctx.status(HttpStatus.UNAUTHORIZED_401);
      ctx.result(exception.getMessage());
    });

    routes.forEach(route ->
        app.routes(() ->
            path(route.getContextPath(), route.bindRoutes())
        )
    );
  }

}
