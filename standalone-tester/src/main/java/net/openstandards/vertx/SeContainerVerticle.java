package net.openstandards.vertx;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import net.openstandards.event.TestEvent;
import net.openstandards.event.TextService;
import net.openstandards.server.StartupEvent;

public class SeContainerVerticle extends AbstractVerticle {
  private static final Logger log = LoggerFactory.getLogger(SeContainerVerticle.class);
  
  @Inject
  Event<TestEvent> event;

  @Inject
  Event<StartupEvent> startupEvent;

  SeContainerInitializer containerInitializer;
  SeContainer container;

  @Override
  public void start(Promise<Void> future) {
    log.info("Welcome to SeContainer!");

    containerInitializer = SeContainerInitializer.newInstance();
    SeContainer container = containerInitializer.initialize();
//      try (SeContainer container = containerInitializer.initialize()) {
    this.container = container;
    container.getBeanManager().fireEvent(new TestEvent("Welcome to SeContainerVerticle!"));
//      }

    log.info("Fired event in SeContainerVerticle::start");
  }

  @Override
  public void stop() {
    System.out.println("Shutting down SeContainerVerticle");
  }

  public void onEvent(@Observes ContainerInitialized initEvent, TextService textService) {
    event.fire(new TestEvent("SeContainerVerticle started " + initEvent.getContainerId()));
    startupEvent.fire(new StartupEvent());
  }

}
