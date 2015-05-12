package org.test.cxf.cond;

import static org.junit.Assert.assertNotNull;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;

public class TestServer {

  @Test
  public void testServer() throws Exception {
    TestServer ts = new TestServer();
    ts.start();

    URL url = new URL(endpoint + "/get");
    Object content = url.getContent();
    assertNotNull(content);

    ts.stop();
  }

  private URI endpoint = URI.create("http://localhost:8080/test");

  private Server server;

  public void start() {
    try {
      final ServletContextHandler contextHandler = createContextHandler();
      final InetSocketAddress isa = new InetSocketAddress(endpoint.getHost(), endpoint.getPort());
      server = new Server(isa);

      server.setHandler(contextHandler);
      server.start();
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void stop() {
    try {
      if (server != null) {
        server.stop();
      }
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  private ServletContextHandler createContextHandler() {
    final CXFNonSpringJaxrsServlet odataServlet = new CXFNonSpringJaxrsServlet();
    final ServletHolder odataServletHolder = new ServletHolder(odataServlet);
    odataServletHolder.setInitParameter("javax.ws.rs.Application",
        "org.test.cxf.cond.rest.RestApplication");

    final ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    contextHandler.addServlet(odataServletHolder, endpoint.getPath() + "/*");
    return contextHandler;
  }

  public URI getEndpoint() {
    return endpoint;
  }

}
