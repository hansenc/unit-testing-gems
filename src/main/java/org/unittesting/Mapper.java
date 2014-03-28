package com.overstock.unittesting;

public interface Mapper {

  EventAudit map(Event event);

  public static class Impl implements Mapper {

    @Override
    public EventAudit map(Event event) {
      return new EventAudit.Builder()
          .withCompetitionEvent(event.getCompetitionEvent())
          .withCompetitors(event.getCompetitors())
          .withDate(event.getDate())
          .withMedal(event.getMedal())
          .withMyCondition(event.getMyCondition())
          .build();
    }

  }
}
