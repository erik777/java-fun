package net.openstandards.server;

import io.vertx.core.Vertx;
import net.openstandards.vertx.HelloVerticle;
import net.openstandards.vertx.SeContainerVerticle;

public class Server {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new SeContainerVerticle());
    vertx.deployVerticle(new HelloVerticle());
  }
}
