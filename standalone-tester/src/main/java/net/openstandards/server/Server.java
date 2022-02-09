package net.openstandards.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import net.openstandards.vertx.HelloVerticle;

public class Server {
  private static final Logger log = LoggerFactory.getLogger(Server.class);
  
  public static void main(String[] args) {
    new Server().start();
  }
  
  public void start() {
    log.debug("new VertxOptions().getWorkerPoolSize: " + new VertxOptions().getWorkerPoolSize());
    Vertx.clusteredVertx(new VertxOptions(), v -> {
      if (v.succeeded()) {
        Vertx vertx = v.result();
        launch(vertx);
      }
    });
  }
  
  public void launch(Vertx vertx) {
      // Worker verticles are for blocking code.  Creating a thread pool of 40 for them (default 20)
  //  Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
    
    // Designate this a worker (blocking) verticale 
    vertx.deployVerticle("net.openstandards.vertx.SeContainerVerticle"
        , 
        new DeploymentOptions().setInstances(2)
  //  new DeploymentOptions().setInstances(2).setWorker(true)
    );
  
    // an event loop (non-blocking) verticle
  //  vertx.deployVerticle("net.openstandards.vertx.HelloVerticle"
  //      , new DeploymentOptions().setInstances(2).setWorker(true)
  //      );
    vertx.deployVerticle(new HelloVerticle("H1"), new DeploymentOptions().setWorker(true));
    vertx.deployVerticle(new HelloVerticle("H2"), new DeploymentOptions().setWorker(true));
  }
}
