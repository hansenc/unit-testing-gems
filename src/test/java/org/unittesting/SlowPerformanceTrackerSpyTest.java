package com.overstock.unittesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class SlowPerformanceTrackerSpyTest {
  
  @Mock
  private MedalFetcher medalFetcher;
  
  @Mock
  private EventLogger eventLogger;

  private PerformanceTracker underTest;

  @Mock
  private Mapper mapper;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new PerformanceTracker.SlowImpl(medalFetcher, eventLogger, mapper);
    //SPY
    underTest = spy(underTest);
  }

  @Test
  public void testWithSpy() {
    // GIVEN
    TrackRequest request3 = mock(TrackRequest.class);
    TrackRequest request2 = mock(TrackRequest.class);
    TrackRequest request1 = mock(TrackRequest.class);
    Event event1 = mock(Event.class);
    Event event2 = mock(Event.class);
    Event event3 = mock(Event.class);
    Collection<TrackRequest> requests = Lists.newArrayList(request1, request2, request3);
    doReturn(event1).when(underTest).track(request1);
    doReturn(event2).when(underTest).track(request2);
    doReturn(event3).when(underTest).track(request3);
    // WHEN
    Collection<Event> actual = underTest.track(requests);
    // THEN
    assertEquals(3, actual.size());
    assertTrue(actual.contains(event1));
    assertTrue(actual.contains(event2));
    assertTrue(actual.contains(event3));
  }

  @Test
  public void testFast() {
      // GIVEN
      TrackRequest request3 = mock(TrackRequest.class);
      TrackRequest request2 = mock(TrackRequest.class);
      TrackRequest request1 = mock(TrackRequest.class);
      Event event1 = mock(Event.class);
      Event event2 = mock(Event.class);
      Event event3 = mock(Event.class);
      Collection<TrackRequest> requests = Lists.newArrayList(request1, request2, request3);
      doReturn(event1).when(underTest).track(request1);
      doReturn(event2).when(underTest).track(request2);
      doReturn(event3).when(underTest).track(request3);
      // WHEN
       Collection<Event> actual = underTest.track(requests);
      // THEN
      assertEquals(3, actual.size());
      assertTrue(actual.contains(event1));
      assertTrue(actual.contains(event2));
      assertTrue(actual.contains(event3));
      verify(underTest, times(3)).track(any(TrackRequest.class));
  }

}
