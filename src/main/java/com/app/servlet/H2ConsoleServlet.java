import org.h2.server.web.JakartaWebServlet;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(
    urlPatterns = "/h2-console/*",
    initParams = {
        @WebInitParam(name = "webAllowOthers", value = "true")
    }
)
public class H2ConsoleServlet extends JakartaWebServlet {}
