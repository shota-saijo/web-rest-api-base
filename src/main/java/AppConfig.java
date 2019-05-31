import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.ui.adaptor.LocalDateTimeAdaptor;
import io.javalin.Javalin;
import io.javalin.json.JavalinJson;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppConfig {

  private static final Logger logger = LoggerFactory.getLogger("application");

  private static final Javalin APP;

  static {
    Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdaptor())
        .create();
    JavalinJson.setFromJsonMapper(gson::fromJson);
    JavalinJson.setToJsonMapper(gson::toJson);

    APP = Javalin.create()
        .contextPath("api")
        .disableStartupBanner()
        .requestLogger((ctx, timeMs) -> logger.info(ctx.method() + " " + ctx.path() + " " + ctx.body()))
        .port(7000);
  }

  private AppConfig() {
  }

  public static Javalin getApp() {
    return APP;
  }
}
