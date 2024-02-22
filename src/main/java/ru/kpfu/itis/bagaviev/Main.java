package ru.kpfu.itis.bagaviev;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final int PORT;

    static {
        String unparsedPort = System.getenv("PORT");
        int port = 8080;
        if (unparsedPort != null && !unparsedPort.isEmpty()) {
            port = Integer.parseInt(unparsedPort);
        }
        PORT = port;
    }

    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(getTempDirectory());
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");
        tomcat.start();
        tomcat.getServer().await();
    }

    private static String getTempDirectory() {
        try {
            File tempDirectory = File.createTempFile("tomcat.", "." + PORT);
            tempDirectory.delete();
            tempDirectory.mkdir();
            tempDirectory.deleteOnExit();
            return tempDirectory.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}