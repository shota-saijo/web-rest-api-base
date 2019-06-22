package core;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;
import core.domain.repository.AuthRepository;
import core.domain.repository.UserRepository;
import core.domain.service.UserCreateFacadeService;
import core.domain.service.UserQueryService;
import core.domain.write.model.User.UserRole;
import core.infrastracture.converter.LocalDateTimeConverter;
import core.infrastracture.converter.UserRoleConverter;
import core.infrastracture.postgresql.PostgresUserCreateFacadeService;
import core.infrastracture.postgresql.PostgresqlAuthRepository;
import core.infrastracture.postgresql.PostgresqlUserQueryService;
import core.infrastracture.postgresql.PostgresqlUserRepository;
import core.ui.route.Route;
import core.ui.route.UserRoute;
import java.time.LocalDateTime;
import org.sql2o.converters.Converter;

public class CoreModule extends AbstractModule {

  @Override
  protected void configure() {
    Multibinder.newSetBinder(binder(), Route.class).addBinding().to(UserRoute.class);

    MapBinder<Class, Converter> converterBinder = MapBinder.newMapBinder(binder(), Class.class, Converter.class);
    converterBinder.addBinding(LocalDateTime.class).to(LocalDateTimeConverter.class);
    converterBinder.addBinding(UserRole.class).to(UserRoleConverter.class);

    bind(UserQueryService.class).to(PostgresqlUserQueryService.class);
    bind(UserRepository.class).to(PostgresqlUserRepository.class);
    bind(AuthRepository.class).to(PostgresqlAuthRepository.class);
    bind(UserCreateFacadeService.class).to(PostgresUserCreateFacadeService.class);
  }
}
