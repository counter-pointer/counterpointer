package edu.brown.cs.counterpointer.main;

import edu.brown.cs.counterpointer.api.API;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {
  private static final int CACHE_TIME = 31536000;
  private static final int DEFAULT_PORT = 4567;
  private final API api = new API();

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private final String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);
    String herokuPort = System.getenv("PORT");
    int port;
    if (herokuPort == null) {
      port = (int) options.valueOf("port");
    } else {
      port = Integer.parseInt(herokuPort);
    }

    runSparkServer(port);
  }

  private void runSparkServer(int port) {
    Spark.port(port);
    Spark.externalStaticFileLocation("../frontend/client/build");
    Spark.staticFiles.expireTime(CACHE_TIME); // set expire time to a year

    // handler exceptions
    Spark.exception(RuntimeException.class, (e, req, res) -> {
      res.status(400);
      res.body(e.getMessage());
    });
    Spark.exception(Exception.class, (e, req, res) -> {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    });

    initializeSpark();
  }

  /**
   * Initializes Spark to handle API requests.
   */
  private void initializeSpark() {
    Spark.path("/api", () -> {
      Spark.post("/generate", api.postGenerate());
      Spark.post("/suggest", api.postSuggest());
      Spark.post("/check", api.postCheck());
    });
  }
}
