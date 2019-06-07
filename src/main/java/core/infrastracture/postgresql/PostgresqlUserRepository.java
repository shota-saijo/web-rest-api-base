package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.repository.UserRepository;
import core.domain.write.model.User;
import core.domain.write.model.UserId;
import org.sql2o.Connection;
import org.sql2o.Query;

public class PostgresqlUserRepository implements UserRepository {

  @Inject
  PostgresqlClient client;

  @Override
  public UserId insert(User user) {
    try (Connection conn = client.beginTransaction()) {
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

      conn.commit(true);

      return user.getId();
    }
  }
}
