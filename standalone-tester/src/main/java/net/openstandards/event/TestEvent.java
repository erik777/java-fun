package net.openstandards.event;

import javax.inject.Singleton;

public class TestEvent {
  private String message;
  
  public TestEvent() {}
  
  public TestEvent(String message) {
    this.message = message;
    System.out.println("Constructed TestEvent " + this.message);
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
