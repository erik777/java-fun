package net.openstandards.vertx;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import net.openstandards.event.TestEvent;
import net.openstandards.server.StartupEvent;

public class HelloVerticle extends AbstractVerticle {
  private static final Logger log = LoggerFactory.getLogger(HelloVerticle.class);
  
  String id;
  
  public HelloVerticle() {}
  public HelloVerticle(String id) {
    this.id = id;
  }
  
  @Inject
  Event<TestEvent> event;

  @Override
  public void start(Promise<Void> future) {
    log.info("===========================");
    log.info("Welcome to HelloVerticle {}!", id);
    log.info("===========================");
  }
  
  @Override
  public void stop() {
    log.info("===========================");
    log.info("Shutting down HelloVerticle");
    log.info("===========================");
  } 
  
  public void onStartup(@Observes StartupEvent startup) {
    event.fire(new TestEvent("HelloVerticle started " + id));
    log.info("Fired TestEvent in HelloVerticle::onStartup");
  }
}
