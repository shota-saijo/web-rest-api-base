package core.infrastracture.postgresql;

import com.google.inject.Inject;
import core.domain.repository.AuthRepository;
import core.domain.write.model.Auth;
import org.sql2o.Connection;
import org.sql2o.Query;

public class PostgresqlAuthRepository implements AuthRepository {

  private PostgresqlClient client;

  @Inject
  public PostgresqlAuthRepository(PostgresqlClient client) {
    this.client = client;
  }

  @Override
  public void insert(Auth auth, Connection conn) {
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO AUTH ( ")
        .append("ID ")
        .append(",PASSWORD ")
        .append(client.commandManageColumns())
        .append(" ) VALUES ( ")
        .append(":id ")
        .append(",:password ")
        .append(client.commandManageColumnParameters())
        .append(" ) ");

    Query query = conn.createQuery(sql.toString())
        .addParameter("password", auth.getPassword().toString());

    client.setManageColumnParameters(query, auth).executeUpdate();
  }
}

