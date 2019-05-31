package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.service.UserQueryService;
import core.domain.write.model.User;
import java.util.List;
import org.sql2o.Connection;

public class PostgresqlUserQueryService implements UserQueryService {

  @Inject
  PostgresqlClient client;

  @Override
  public List<User> findAll() {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
            .append("USER_ID id ")
            .append(",EMAIL email ")
            .append(",USER_NAME userName ")
            .append(",USER_ROLE userRole ")
            .append(client.manageColumns())
          .append("FROM ")
            .append("USERS ");
      return connection.createQuery(query.toString())
        .executeAndFetch(User.class);
    }
  }
}