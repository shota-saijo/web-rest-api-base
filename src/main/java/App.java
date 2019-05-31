import com.google.inject.Guice;
import com.google.inject.Injector;
import core.ui.route.Endpoint;
import io.javalin.Javalin;

public class App {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AppModule());
    injector.getInstance(Endpoint.class).boot();
    injector.getInstance(Javalin.class).start();
  }

}
