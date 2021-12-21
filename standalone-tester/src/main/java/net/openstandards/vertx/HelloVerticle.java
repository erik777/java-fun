package net.openstandards.vertx;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import net.openstandards.event.TestEvent;
import net.openstandards.server.StartupEvent;

public class HelloVerticle extends AbstractVerticle {
  
  @Inject
  Event<TestEvent> event;

  @Override
  public void start(Promise<Void> future) {
      System.out.println("Welcome to HelloVerticle!");
  }
  
  @Override
  public void stop() {
    System.out.println("Shutting down HelloVerticle");
  } 
  
  public void onStartup(@Observes StartupEvent startup) {
    event.fire(new TestEvent("HelloVerticle started"));
    System.out.println("Fired TestEvent in HelloVerticle::onStartup");
  }
}
