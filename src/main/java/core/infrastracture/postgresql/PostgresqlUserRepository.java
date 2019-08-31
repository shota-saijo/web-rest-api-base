package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.repository.UserRepository;
import core.domain.write.model.User;
import org.sql2o.Connection;
import org.sql2o.Query;

public class PostgresqlUserRepository implements UserRepository {

  PostgresqlClient client;

  @Inject
  public PostgresqlUserRepository(PostgresqlClient client) {
    this.client = client;
  }

  @Override
  public void insert(User user, Connection conn) {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO USERS ( ")
          .append("ID ")
          .append(",EMAIL ")
          .append(",USER_NAME ")
          .append(",USER_ROLE ")
          .append(client.commandManageColumns())
        .append(" ) VALUES ( ")
          .append(":id ")
          .append(",:email ")
          .append(",:userName ")
          .append(",:userRole ")
          .append(client.commandManageColumnParameters())
        .append(" ) ");

    Query query = conn.createQuery(sql.toString())
        .addParameter("email", user.getEmail())
        .addParameter("userName", user.getUserName())
        .addParameter("userRole", user.getUserRole().toString());

    client.setManageColumnParameters(query, user).executeUpdate();
  }

  @Override
  public void update(User user) {
    try (Connection conn = client.beginTransaction()) {
      StringBuilder sql = new StringBuilder();
      sql.append("UPDATE USERS SET( ")
          .append(",EMAIL ")
          .append(",USER_NAME ")
          .append(",USER_ROLE ")
          .append(client.commandUpdateManageColumns())
          .append(" ) VALUES ( ")
          .append(",:email ")
          .append(",:userName ")
          .append(",:userRole ")
          .append(client.commandUpdateManageColumnParameters())
          .append(" ) ")
          .append("WHERE ")
          .append("id = :id");

      Query query = conn.createQuery(sql.toString())
          .addParameter(":id", user.getId().value())
          .addParameter("email", user.getEmail())
          .addParameter("userName", user.getUserName())
          .addParameter("userRole", user.getUserRole().toString());

      query.executeUpdate().commit();
    }
  }
}
