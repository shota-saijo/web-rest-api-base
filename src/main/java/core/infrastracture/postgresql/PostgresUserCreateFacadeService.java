package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.repository.AuthRepository;
import core.domain.repository.UserRepository;
import core.domain.service.UserCreateFacadeService;
import core.domain.write.model.Auth;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
import org.sql2o.Connection;

public class PostgresUserCreateFacadeService implements UserCreateFacadeService {

  private PostgresqlClient client;

  private UserRepository userRepository;

  private AuthRepository authRepository;

  @Inject
  public PostgresUserCreateFacadeService(PostgresqlClient client, UserRepository userRepository,
      AuthRepository authRepository) {
    this.client = client;
    this.userRepository = userRepository;
    this.authRepository = authRepository;
  }

  @Override
  public UserId createUser(User user, Auth auth) {
    try (Connection conn = client.beginTransaction()) {

      userRepository.insert(user, conn);

      authRepository.insert(auth, conn);

      conn.commit();

      return user.getId();
    }
  }
}
