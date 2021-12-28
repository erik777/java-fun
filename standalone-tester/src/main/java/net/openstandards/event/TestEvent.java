package net.openstandards.event;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEvent {
  private static final Logger log = LoggerFactory.getLogger(TestEvent.class);
  
  private String message;
  
  public TestEvent() {}
  
  public TestEvent(String message) {
    this.message = message;
    log.info("Constructed TestEvent " + this.message);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "TestEvent [message=" + message + "]";
  }
  
  
}
