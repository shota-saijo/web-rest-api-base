import com.google.inject.AbstractModule;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import core.CoreModule;
import io.javalin.Javalin;

public class AppModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Javalin .class).toInstance(AppConfig.getApp());
    bind(Config .class).toInstance(ConfigFactory.load());
    install(new CoreModule());
  }
}
