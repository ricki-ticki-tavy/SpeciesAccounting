package org.ricki.catalog;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class Main {

  public static int httpPort;
  public static Tomcat tomcat;
  private static File tmp;


  private static void exitWithError(String error) {
    System.out.println(error);
    System.exit(1);
  }

  public static void main(String[] args) {
//    System.setProperty("http.port", "8080");
    if (System.getProperty("http.port") == null) {
      exitWithError("Не задан параметр -Dhttp.port");
    }
    httpPort = Integer.parseInt(System.getProperty("http.port"));
    if (httpPort == 0) {
      exitWithError("Неверно указан порт");
    }

    tmp = new File(System.getProperty("user.dir"));// + "/work/Tomcat/localhost/ROOT/WEB-INF");
    tmp.delete();
    tmp.mkdirs();
    tmp.deleteOnExit();

    tomcat = new Tomcat();
    tomcat.setBaseDir(tmp.getAbsolutePath());
    tomcat.setPort(httpPort);
    tomcat.setHostname("localhost");
    tomcat.getHost().setAppBase(".");

    try {
      tomcat.addWebapp("/", ".");
    } catch (ServletException se) {
      se.printStackTrace();
      exitWithError(se.getLocalizedMessage());
    }

    tomcat.getConnector();
    try {
      tomcat.start();
    } catch (LifecycleException lce) {
      lce.printStackTrace();
      exitWithError(lce.getLocalizedMessage());
    }

    tomcat.getServer().await();
  }
}
