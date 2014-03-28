package org.unittesting;

import static org.mockito.Mockito.mock;

import java.net.URI;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.Lists;

public class EventLoggerTest {

  private EventLogger eventLogger;

  @Rule
  public ReportCreator reportCreator = new ReportCreator();
  
  @Rule
  public EventLoggerDestroyerRule rule = new EventLoggerDestroyerRule();

  @Rule
  public ReportWriter writer = new ReportWriter(this, reportCreator.getFile());
  
  @Before
  public void setUp() {
    eventLogger = new EventLogger.Impl();
  }

  @Test(timeout = 150)
  public void testSave() throws Exception {
    Event event = new Event.Builder()
        .withCompetitionEvent(new CompetitionEvent(123, new URI("http://xterra.com")))
        .withCompetitors(Lists.newArrayList(
          TrainingEffort.IRONMAN,
          TrainingEffort.MEDIUM,
          TrainingEffort.HIGH,
          TrainingEffort.COUCH_POTATO))
        .withDate(new Date())
        .withMedal(Medal.SILVER)
        .withMyCondition(TrainingEffort.HIGH)
        .build();
    rule.setEvent(event);
    eventLogger.save(event);
  }
  
  @Test
  public void testDelete() throws Exception {
    Event event = mock(Event.class);
    //NOT MUCH HAPPENING HERE
    eventLogger.delete(event);
  }

  @Test
  public void testSaveAudit() throws Exception {
    EventAudit eventAudit = mock(EventAudit.class);
    //NOT MUCH HAPPENING HERE
    eventLogger.save(eventAudit);
  }
}
