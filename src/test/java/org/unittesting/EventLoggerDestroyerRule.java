package org.unittesting;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class EventLoggerDestroyerRule implements TestRule {

  private Event event;

  private EventLogger eventLogger;

  public EventLoggerDestroyerRule() {
    this.eventLogger = new EventLogger.Impl();
  }

  public void setEvent(Event event) {
    this.event = event;
  }

//  @Override
  public Statement apply(final Statement base, final Description description) {
    return new Statement() {
      public void evaluate() throws Throwable {
        System.out.println("Before executing: " + description);
        base.evaluate();
        eventLogger.delete(event);
      }
    };
  }
}
