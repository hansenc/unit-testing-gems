package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MapperTest {

  private Mapper mapper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mapper = new Mapper.Impl();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void test() {
    CompetitionEvent competitionEvent = mock(CompetitionEvent.class);
    Collection<TrainingEffort> competitors = mock(Collection.class);
    Date date = mock(Date.class);
    Medal medal = Medal.BRONZE;
    TrainingEffort myCondition = TrainingEffort.COUCH_POTATO;
    Event event = new Event.Builder()
        .withCompetitionEvent(competitionEvent)
        .withCompetitors(competitors)
        .withDate(date)
        .withMedal(medal)
        .withMyCondition(myCondition)
        .build();
    EventAudit actual = mapper.map(event);
    assertEquals(date, actual.getDate());
    assertEquals(competitionEvent, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(date, actual.getDate());
    assertEquals(medal, actual.getMedal());
    assertEquals(myCondition, actual.getMyCondition());
  }

  @Test
  public void testAllEventMethodsInvokedInMapper() throws Exception {
    EventAnswer answer = new EventAnswer();
    Event mock = Mockito.mock(Event.class, answer);
    try {
      mapper.map(mock);
    }
    catch (Exception e) {
      //We don't care about any exceptions thrown from builder, just that buildLineItem invokes all getters 
    }
    finally {
      assertFalse("Missing getter invocation: " + answer.getMissingGetInvocations(), answer.isMissingGetInvocations());
    }
  }
}
