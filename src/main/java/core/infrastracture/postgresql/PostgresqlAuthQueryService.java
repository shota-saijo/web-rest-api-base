package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.service.Auth;
import core.domain.service.AuthQueryService;
import org.sql2o.Connection;

public class PostgresqlAuthQueryService implements AuthQueryService {

  PostgresqlClient client;

  @Inject
  public PostgresqlAuthQueryService(PostgresqlClient client) {
    this.client = client;
  }

  @Override
  public Auth findByEmail(String email) {
    try (Connection connection = client.open()) {
      StringBuilder query = new StringBuilder();
      query
          .append("SELECT ")
            .append("AUTH.ID as userId ")
            .append(",AUTH.PASSWORD as password ")
          .append("FROM ")
            .append("USERS ")
            .append("INNER JOIN AUTH ")
            .append("ON USERS.ID = AUTH.ID ")
          .append("WHERE ")
            .append("USERS.EMAIL = :email ");
      return connection.createQuery(query.toString())
          .addParameter("email", email)
          .executeAndFetchFirst(Auth.class);
    }
  }
}
