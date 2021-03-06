package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.service.UserQueryService;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
import java.util.List;
import org.sql2o.Connection;

public class PostgresqlUserQueryService implements UserQueryService {

  PostgresqlClient client;

  @Inject
  public PostgresqlUserQueryService(PostgresqlClient client) {
    this.client = client;
  }

  @Override
  public List<User> findAll() {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
            .append("ID id ")
            .append(",EMAIL email ")
            .append(",USER_NAME userName ")
            .append(",USER_ROLE userRole ")
            .append(client.queryManageColumns())
          .append("FROM ")
            .append("USERS ");
      return connection.createQuery(query.toString())
        .executeAndFetch(User.class);
    }
  }

  @Override
  public User findById(UserId userId) {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
          .append("ID id ")
          .append(",EMAIL email ")
          .append(",USER_NAME userName ")
          .append(",USER_ROLE userRole ")
          .append(client.queryManageColumns())
          .append("FROM ")
          .append("USERS ")
          .append("WHERE ")
          .append("ID = :userId");
      return connection.createQuery(query.toString())
          .addParameter("userId", userId.toString())
          .executeAndFetchFirst(User.class);
    }
  }

  @Override
  public User findByEmail(String email) {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
          .append("ID id ")
          .append(",EMAIL email ")
          .append(",USER_NAME userName ")
          .append(",USER_ROLE userRole ")
          .append(client.queryManageColumns())
          .append("FROM ")
          .append("USERS ")
          .append("WHERE ")
          .append("EMAIL = :email");
      return connection.createQuery(query.toString())
          .addParameter("email", email)
          .executeAndFetchFirst(User.class);
    }
  }
}
