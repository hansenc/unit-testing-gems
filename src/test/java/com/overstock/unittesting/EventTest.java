package com.overstock.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

public class EventTest {

  @Test
  public void test() {
    Collection<TrainingEffort> competitors = mock(Collection.class);
    Date date = mock(Date.class);
    Medal medal = Medal.BRONZE;
    TrainingEffort myCondition = TrainingEffort.COUCH_POTATO;
    CompetitionEvent competitionEvent = mock(CompetitionEvent.class);

    Event actual = new Event.Builder()
        .withCompetitionEvent(competitionEvent)
        .withCompetitors(competitors)
        .withDate(date)
        .withMedal(medal)
        .withMyCondition(myCondition)
        .build();
    assertEquals(competitionEvent, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(date, actual.getDate());
    assertEquals(medal, actual.getMedal());
    assertEquals(myCondition, actual.getMyCondition());
  }

}
