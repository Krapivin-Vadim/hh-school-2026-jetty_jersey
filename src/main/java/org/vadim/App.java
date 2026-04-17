package org.vadim;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler("/path");
        ResourceConfig config = new ResourceConfig();
        config.packages("org.vadim");
        ServletContainer servlet = new ServletContainer(config);
        ServletHolder servletHolder = new ServletHolder(servlet);
        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
        try {
            server.start();
            server.join();
        }
        finally {
            server.destroy();
        }

    }
}
