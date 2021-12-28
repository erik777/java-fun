package net.openstandards.event;

import java.lang.invoke.MethodHandles;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TestObserver {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  
  public void observes(@Observes TestEvent event) {
    log.info("==> Observed event: " + event);
  }

  public String onEvent(@Observes ContainerInitialized event, TextService textService) {
    log.info("--> Observed ContainerInitialized event ");
    return textService.parseText(event.getContainerId());
  }
}
