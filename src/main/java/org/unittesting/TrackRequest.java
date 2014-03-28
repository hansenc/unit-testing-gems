package org.unittesting;

import java.util.Collection;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.google.common.base.Preconditions;

@AutoProperty
public class TrackRequest {
  private final TrainingEffort me;

  private final Collection<TrainingEffort> competitors;

  private final CompetitionEvent competitionEvent;

  public TrainingEffort getMe() {
    return me;
  }

  public Collection<TrainingEffort> getCompetitors() {
    return competitors;
  }

  public CompetitionEvent getCompetitionEvent() {
    return competitionEvent;
  }

  private TrackRequest(Builder builder) {
    this.me = builder.me;
    this.competitors = builder.competitors;
    this.competitionEvent = builder.competitionEvent;
  }

  public static class Builder {

    private TrainingEffort me;

    private Collection<TrainingEffort> competitors;

    private CompetitionEvent competitionEvent;

    public Builder withMe(TrainingEffort me) {
      this.me = me;
      return this;
    }

    public Builder withCompetitors(Collection<TrainingEffort> competitors) {
      this.competitors = competitors;
      return this;
    }

    public Builder withCompetitionEvent(CompetitionEvent competitionEvent) {
      this.competitionEvent = competitionEvent;
      return this;
    }

    public TrackRequest build() {
      validate();
      return new TrackRequest(this);
    }

    private void validate() {
      Preconditions.checkNotNull(me, "me may not be null");
      Preconditions.checkNotNull(competitors, "competitors may not be null");
      Preconditions.checkNotNull(competitionEvent, "competitionEvent may not be null");
    }
  }

  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return Pojomatic.equals(this, obj);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }
}
