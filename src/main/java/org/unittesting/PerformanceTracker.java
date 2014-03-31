package org.unittesting;

import java.util.Collection;

public interface PerformanceTracker
{

    Event track(TrackRequest trackRequest);

    Collection<Event> track(Collection<TrackRequest> trackRequests);
}
