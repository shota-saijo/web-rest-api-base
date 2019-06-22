package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.service.AuthQueryService;
import core.domain.write.model.Auth;
import core.domain.write.model.UserId;
import org.sql2o.Connection;

public class PostgresqlAuthQueryService implements AuthQueryService {

  PostgresqlClient client;

  @Inject
  public PostgresqlAuthQueryService(PostgresqlClient client) {
    this.client = client;
  }

  @Override
  public Auth findByUserId(UserId userId) {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
          .append("ID id ")
          .append(",PASSWORD password ")
          .append(",USER_NAME userName ")
          .append(",USER_ROLE userRole ")
          .append(client.queryManageColumns())
          .append("FROM ")
          .append("AUTH ")
          .append("WHERE ")
          .append("ID = :userId");
      return connection.createQuery(query.toString())
          .addParameter("userId", userId.toString())
          .executeAndFetchFirst(Auth.class);
    }
  }
}
