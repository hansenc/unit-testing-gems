package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;

public class EventAuditTest {

  @Test
  public void test() {
    Collection<TrainingEffort> competitors = mock(Collection.class);
    Date date = mock(Date.class);
    Medal medal = Medal.BRONZE;
    TrainingEffort myCondition = TrainingEffort.COUCH_POTATO;
    CompetitionEvent competitionEventAudit = mock(CompetitionEvent.class);

    EventAudit actual = new EventAudit.Builder()
        .withCompetitionEvent(competitionEventAudit)
        .withCompetitors(competitors)
        .withDate(date)
        .withMedal(medal)
        .withMyCondition(myCondition)
        .build();
    assertEquals(competitionEventAudit, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(date, actual.getDate());
    assertEquals(medal, actual.getMedal());
    assertEquals(myCondition, actual.getMyCondition());
  }

}
