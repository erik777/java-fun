package net.openstandards.event;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import org.jboss.weld.environment.se.events.ContainerInitialized;

@Singleton
public class TestObserver {
  public void observes(@Observes TestEvent event) {
    System.out.println("==> Observed event: " + event);
  }

  public String onEvent(@Observes ContainerInitialized event, TextService textService) {
    System.out.println("--> Observed ContainerInitialized event");
    return textService.parseText(event.getContainerId());
  }
}
