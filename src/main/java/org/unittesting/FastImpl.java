package org.unittesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FastImpl implements PerformanceTracker
{

    private SlowImpl slowImpl;
    private Mapper mapper;
    private EventLogger eventLogger;
    private MedalFetcher medalFetcher;

    public FastImpl(MedalFetcher medalFetcher, EventLogger eventLogger, Mapper mapper)
    {
        this(new SlowImpl(medalFetcher, eventLogger, mapper), medalFetcher, eventLogger, mapper);
    }

    public FastImpl(SlowImpl slowImpl, MedalFetcher medalFetcher, EventLogger eventLogger, Mapper mapper)
    {
        this.slowImpl = slowImpl;
        this.medalFetcher = medalFetcher;
        this.eventLogger = eventLogger;
        this.mapper = mapper;
    }

    @Override
    public Event track(TrackRequest trackRequest)
    {
        return slowImpl.track(trackRequest);
    }

    @Override
    public Collection<Event> track(Collection<TrackRequest> trackRequests)
    {
        ExecutorService fetcherThreadPool = Executors.newFixedThreadPool(10);
        ExecutorService loggerThreadPool = Executors.newFixedThreadPool(10);
        Collection<Future<Event>> allSubmitted = new ArrayList<>();
        Collection<Event> events = new ArrayList<>();
        for(TrackRequest eachRequest : trackRequests)
        {
            Future<Event> submitted = fetcherThreadPool.submit(new MedalFetcherTask(eachRequest));
            allSubmitted.add(submitted);
        }
        for(Future<Event> eachSubmission : allSubmitted)
        {
            try
            {
                events.add(eachSubmission.get());
            }
            catch(InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        for(Event eachEvent : events)
        {
            loggerThreadPool.execute(new AuditorTask(eachEvent));
        }
        return events;
    }

    private final class MedalFetcherTask implements Callable<Event>
    {
        private final TrackRequest trackRequest;

        MedalFetcherTask(TrackRequest request)
        {
            this.trackRequest = request;
        }

        @Override
        public Event call() throws Exception
        {
            Medal medal = medalFetcher.getMedal(trackRequest.getCompetitionEvent());
            Event event = new Event.Builder()
                    .withCompetitionEvent(trackRequest.getCompetitionEvent())
                    .withCompetitors(trackRequest.getCompetitors())
                    .withDate(new Date())
                    .withMedal(medal)
                    .withMyCondition(trackRequest.getMe())
                    .build();
            return event;
        }
    }

    private final class AuditorTask implements Runnable
    {
        private final Event event;

        AuditorTask(Event event)
        {
            this.event = event;
        }

        @Override
        public void run()
        {
            EventAudit eventAudit = mapper.map(event);
            // NETWORK CALLS
            eventLogger.save(event);
            eventLogger.save(eventAudit);
        }
    }

}