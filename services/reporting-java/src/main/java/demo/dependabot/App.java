package demo.dependabot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class App {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8081);
    ServletContextHandler context = new ServletContextHandler();
    context.setContextPath("/");
    context.addServlet(new ServletHolder(new HealthServlet()), "/health");
    server.setHandler(context);
    server.start();
    server.join();
  }

  static class HealthServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("application/json");
      resp.getWriter().write("{\"service\":\"java\",\"status\":\"ok\"}");
    }
  }
}
