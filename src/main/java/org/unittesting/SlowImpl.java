package org.unittesting;

import java.util.Collection;
import java.util.Date;

import com.google.common.collect.ImmutableList;

public class SlowImpl implements PerformanceTracker
{

    private final MedalFetcher medalFetcher;

    private final EventLogger eventLogger;

    private final Mapper mapper;

    public SlowImpl(MedalFetcher medalFetcher, EventLogger eventLogger, Mapper mapper)
    {
        this.medalFetcher = medalFetcher;
        this.eventLogger = eventLogger;
        this.mapper = mapper;
    }

    @Override
    public Event track(TrackRequest trackRequest)
    {
        // NETWORK CALL
        Medal medal = medalFetcher.getMedal(trackRequest.getCompetitionEvent());
        Event event = new Event.Builder()
                .withCompetitionEvent(trackRequest.getCompetitionEvent())
                .withCompetitors(trackRequest.getCompetitors())
                .withDate(new Date())
                .withMedal(medal)
                .withMyCondition(trackRequest.getMe())
                .build();
        EventAudit eventAudit = mapper.map(event);
        // NETWORK CALLS
        eventLogger.save(event);
        eventLogger.save(eventAudit);
        return event;
    }

    @Override
    public Collection<Event> track(Collection<TrackRequest> trackRequests)
    {
        ImmutableList.Builder<Event> listBuilder = new ImmutableList.Builder<Event>();
        for(TrackRequest each : trackRequests)
        {
            Event event = track(each);
            listBuilder.add(event);
        }
        return listBuilder.build();
    }

}