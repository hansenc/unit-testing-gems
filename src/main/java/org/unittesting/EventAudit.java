package org.unittesting;

import java.util.Collection;
import java.util.Date;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.google.common.base.Preconditions;

@AutoProperty
public class EventAudit {

  private final TrainingEffort myCondition;

  private final Collection<TrainingEffort> competitors;

  private final Medal medal;

  private final CompetitionEvent competitionEvent;

  private final Date date;

  public TrainingEffort getMyCondition() {
    return myCondition;
  }

  public Collection<TrainingEffort> getCompetitors() {
    return competitors;
  }

  public Medal getMedal() {
    return medal;
  }

  public Date getDate() {
    return date;
  }

  public CompetitionEvent getCompetitionEvent() {
    return competitionEvent;
  }

  private EventAudit(Builder builder) {
    this.myCondition = builder.myCondition;
    this.competitors = builder.competitors;
    this.medal = builder.medal;
    this.competitionEvent = builder.competitionEvent;
    this.date = builder.date;
  }

  public static class Builder {

    private TrainingEffort myCondition;

    private Collection<TrainingEffort> competitors;

    private Medal medal;

    private CompetitionEvent competitionEvent;

    private Date date;

    public Builder withMyCondition(TrainingEffort myCondition) {
      this.myCondition = myCondition;
      return this;
    }

    public Builder withCompetitors(Collection<TrainingEffort> competitors) {
      this.competitors = competitors;
      return this;
    }

    public Builder withMedal(Medal medal) {
      this.medal = medal;
      return this;
    }

    public Builder withCompetitionEvent(CompetitionEvent competitionEvent) {
      this.competitionEvent = competitionEvent;
      return this;
    }

    public Builder withDate(Date date) {
      this.date = date;
      return this;
    }

    public EventAudit build() {
      validate();
      return new EventAudit(this);
    }

    private void validate() {
      Preconditions.checkNotNull(myCondition, "myCondition may not be null");
      Preconditions.checkNotNull(competitors, "competitors may not be null");
      Preconditions.checkNotNull(medal, "medal may not be null");
      Preconditions.checkNotNull(competitionEvent, "competitionEvent may not be null");
      Preconditions.checkNotNull(date, "date may not be null");
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
