package org.unittesting;

public interface OlimpicsBusinessLogic {

  public Medal win(TrainingEffort myTrainingEffort, TrainingEffort... competitorsTrainingEffort);

  public static class Impl implements OlimpicsBusinessLogic {

    @Override
    public Medal win(TrainingEffort myTrainingEffort, TrainingEffort... competitorsTrainingEffort) {
      Competitors competitors = new Competitors();
      competitors.addCompetitors(competitorsTrainingEffort);
      int myRanking = competitors.getBetterTrained(myTrainingEffort);
      return Medal.getMedal(myRanking);
    }

  }
}
