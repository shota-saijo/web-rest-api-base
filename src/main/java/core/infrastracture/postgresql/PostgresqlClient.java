package core.infrastracture.postgresql;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.typesafe.config.Config;
import java.util.Map;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.Converter;
import org.sql2o.quirks.NoQuirks;

@Singleton
public class PostgresqlClient {

  private final Sql2o sql2o;

  @Inject
  private PostgresqlClient(Config config, Map<Class, Converter> converterMap) {
    this.sql2o = new Sql2o(
        config.getString("db.url"),
        config.getString("db.user"),
        config.getString("db.password"),
        new NoQuirks(converterMap)
    );
  }

  public Connection beginTransaction() {
    return sql2o.beginTransaction();
  }

  public Connection open() {
    return sql2o.open();
  }

  public String manageColumns() {
    StringBuilder builder = new StringBuilder();
    builder
        .append(", CREATED_BY createdBy ")
        .append(", CREATED_AT createdAt ")
        .append(", UPDATED_BY updatedBy ")
        .append(", UPDATED_AT updatedAt ");
    return builder.toString();
  }

}
