package core.ui.route;

import com.google.inject.Inject;
import core.application.AuthApplicationService;
import core.application.exception.UnauthorizationException;
import core.application.model.UserContext;
import core.domain.service.UserQueryService;
import core.domain.write.model.User;
import core.domain.write.model.User.UserRole;
import core.domain.write.model.UserId;
import io.javalin.Context;
import io.javalin.Handler;
import io.javalin.security.AccessManager;
import io.javalin.security.Role;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class AccessManagerImpl implements AccessManager {

  private UserQueryService userQueryService;

  private AuthApplicationService authApplicationService;

  @Inject
  public AccessManagerImpl(UserQueryService userQueryService,
      AuthApplicationService authApplicationService) {
    this.userQueryService = userQueryService;
    this.authApplicationService = authApplicationService;
  }

  @Override
  public void manage(@NotNull Handler handler, @NotNull Context ctx,
      @NotNull Set<Role> permittedRoles) throws Exception {
    if (permittedRoles.isEmpty()) {
      handler.handle(ctx);
      return;
    }

    UserId userId = authApplicationService.verifyToken(ctx.header("Authorization"));

    User user = userQueryService.findById(userId);

    if (Objects.isNull(user)) {
      throw new UnauthorizationException("user not found");
    }

    UserRole userRole = user.getUserRole();

    if (!permittedRoles.contains(userRole)) {
      throw new UnauthorizationException("user role permit denied");
    }

    ctx.register(UserContext.class, new UserContext(userId, userRole));

    handler.handle(ctx);
  }
}
