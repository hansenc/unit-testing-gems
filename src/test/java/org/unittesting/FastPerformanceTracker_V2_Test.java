package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.unittesting.TrainingEffort.HIGH;
import static org.unittesting.TrainingEffort.IRONMAN;
import static org.unittesting.TrainingEffort.MEDIUM;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;

public class FastPerformanceTracker_V2_Test
{

    @Mock
    private MedalFetcher medalFetcher;

    
    private EventLogger instrumentedEventLogger;

    @Mock
    private EventLogger eventLogger;

    private PerformanceTracker underTest;

    @Mock
    private Mapper mapper;

    @Captor
    private ArgumentCaptor<Event> eventCaptor;

    @Mock
    private SlowImpl slowImpl;
    
    @Mock
    private TrackRequest singleTrackRequest;
    
    
    @Mock
    private Event expectedEvent;
    
    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        
    }

    static class InstrumentedEventLogger implements EventLogger
    {

        private final CountDownLatch countDownLatch;
        private final EventLogger eventLogger;

        public InstrumentedEventLogger(CountDownLatch countDownLatch, EventLogger eventLogger)
        {
            this.countDownLatch = countDownLatch;
            this.eventLogger = eventLogger;
        }

        @Override
        public void delete(Event event)
        {
            throw new UnsupportedOperationException();
            
        }

        @Override
        public void save(Event event)
        {
            countDownLatch.countDown();
            eventLogger.save(event);
        }

        @Override
        public void save(EventAudit eventAudit)
        {
            countDownLatch.countDown();
            eventLogger.save(eventAudit);
        }



    }

    @Test
    public void test() throws Exception
    {
        // GIVEN
        int size = 100;
        Collection<TrackRequest> requestCollection = createRequestCollection(size);
        CountDownLatch countDownLatch = new CountDownLatch(200);
        instrumentedEventLogger = new InstrumentedEventLogger(countDownLatch, eventLogger);
        underTest = new FastImpl(medalFetcher, instrumentedEventLogger, mapper);
        // WHEN
        Collection<Event> actual = underTest.track(requestCollection);
        countDownLatch.await();
        // THEN
        verify(eventLogger, times(size)).save(any(Event.class));
        assertEquals(100, actual.size());
        verify(eventLogger, times(size)).save(any(EventAudit.class));
    }

    @Test
    public void testSlow() {
        //GIVEN
        when(slowImpl.track(singleTrackRequest)).thenReturn(expectedEvent);
        //WHEN
        underTest = new FastImpl(slowImpl, medalFetcher, instrumentedEventLogger, mapper);
        Event actualEvent = underTest.track(singleTrackRequest);
        //THEN
        assertEquals(expectedEvent, actualEvent);
    }
    
    private Collection<TrackRequest> createRequestCollection(int size) throws URISyntaxException
    {
        TrainingEffort me = MEDIUM;
        CompetitionEvent competitionEvent1 = new CompetitionEvent(124, new URI("http://bostonmarathon.com"));
        Collection<TrainingEffort> competitors = Lists.newArrayList(IRONMAN, HIGH, MEDIUM);
        Collection<TrackRequest> requestCollection = Lists.newArrayList();
        for(int i = 0; i < size; i++)
        {
            requestCollection.add(new TrackRequest.Builder()
                    .withCompetitionEvent(competitionEvent1)
                    .withCompetitors(competitors)
                    .withMe(me)
                    .build());
        }
        Medal medal = Medal.BRONZE;
        when(medalFetcher.getMedal(competitionEvent1)).thenReturn(medal);

        return requestCollection;
    }

}
