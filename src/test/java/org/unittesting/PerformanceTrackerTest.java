package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.unittesting.TrainingEffort.HIGH;
import static org.unittesting.TrainingEffort.IRONMAN;
import static org.unittesting.TrainingEffort.MEDIUM;

import java.net.URI;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class PerformanceTrackerTest {

  @Mock
  private MedalFetcher medalFetcher;

  @Mock
  private EventLogger eventLogger;

  @Mock
  private Mapper mapper;

  private PerformanceTracker underTest;

  @Captor
  private ArgumentCaptor<Event> eventCaptor;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new SlowImpl(medalFetcher, eventLogger, mapper);
  }

  @Test
  public void test() throws Exception {
    // GIVEN
    TrainingEffort me = MEDIUM;
    Collection<TrainingEffort> competitors = Lists.newArrayList(IRONMAN, HIGH, MEDIUM);
    CompetitionEvent competitionEvent = new CompetitionEvent(124, new URI("http://bostonmarathon.com"));
    Medal medal = Medal.BRONZE;
    when(medalFetcher.getMedal(competitionEvent)).thenReturn(medal);
    TrackRequest request = new TrackRequest.Builder()
        .withCompetitionEvent(competitionEvent)
        .withCompetitors(competitors)
        .withMe(me)
        .build();
    //when(mapper.map(any(Event.class))).thenReturn(eventAudit); <-- getting out of hand
    // WHEN
    Event actual = underTest.track(request);
    // THEN
    verify(eventLogger).save(eventCaptor.capture());
    Event actualEvent = eventCaptor.getValue();
    assertEquals(medal, actualEvent.getMedal());
    assertEquals(me, actual.getMyCondition());
    assertEquals(competitionEvent, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(medal, actual.getMedal());
    verify(eventLogger).save(any(EventAudit.class));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testBDD() throws Exception {
    TrainingEffort me = MEDIUM;
    Collection<TrainingEffort> competitors = mock(Collection.class);
    CompetitionEvent competitionEvent = mock(CompetitionEvent.class);
    Medal medal = Medal.BRONZE;
    EventAudit eventAudit = mock(EventAudit.class);
    // GIVEN
    given(medalFetcher.getMedal(competitionEvent)).willReturn(medal);
    given(mapper.map(any(Event.class))).willReturn(eventAudit);
    TrackRequest request = new TrackRequest.Builder()
        .withCompetitionEvent(competitionEvent)
        .withCompetitors(competitors)
        .withMe(me)
        .build();
    // WHEN
    Event actual = underTest.track(request);
    // THEN
    verify(eventLogger).save(eventCaptor.capture());
    Event actualEvent = eventCaptor.getValue();
    assertEquals(medal, actualEvent.getMedal());
    assertEquals(me, actual.getMyCondition());
    assertEquals(competitionEvent, actual.getCompetitionEvent());
    assertEquals(competitors, actual.getCompetitors());
    assertEquals(medal, actual.getMedal());
  }

}
