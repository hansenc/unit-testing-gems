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

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Ignore
public class FastPerformanceTrackerTest
{

    @Mock
    private MedalFetcher medalFetcher;

    @Mock
    private EventLogger eventLogger;

    private PerformanceTracker underTest;

    @Mock
    private Mapper mapper;

    @Captor
    private ArgumentCaptor<Event> eventCaptor;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        underTest = new PerformanceTracker.FastImpl(medalFetcher, eventLogger, mapper);
    }

    @Test
    public void test() throws Exception
    {
        // GIVEN
        int size = 100;
        Collection<TrackRequest> requestCollection = createRequestCollection(size);
        // WHEN
        Collection<Event> actual = underTest.track(requestCollection);
        // THEN
        verify(eventLogger, times(size)).save(any(Event.class));
        assertEquals(2, actual.size());
        verify(eventLogger, times(size)).save(any(EventAudit.class));
    }

    private Collection<TrackRequest> createRequestCollection(int size) throws URISyntaxException
    {
        TrainingEffort me = MEDIUM;
        CompetitionEvent competitionEvent1 = new CompetitionEvent(124, new URI("http://bostonmarathon.com"));
        Collection<TrainingEffort> competitors = Lists.newArrayList(IRONMAN, HIGH, MEDIUM);
        Collection<TrackRequest> requestCollection = Sets.newHashSet();
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
