package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import org.junit.Test;

public class TrackRequestTest {

  @Test
  public void test() {
    CompetitionEvent competitionEvent = mock(CompetitionEvent.class);
    Collection<TrainingEffort> competitors = mock(Collection.class);
    TrainingEffort me = TrainingEffort.COUCH_POTATO;
    TrackRequest actual = new TrackRequest.Builder()
        .withCompetitionEvent(competitionEvent)
        .withCompetitors(competitors)
        .withMe(me)
        .build();
    assertEquals(competitionEvent, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(me, actual.getMe());
  }

}
