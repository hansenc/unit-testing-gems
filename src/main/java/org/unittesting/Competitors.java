package org.unittesting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

public class Competitors {
  private Map<TrainingEffort, Integer> competition = Maps.newHashMap();

  public void addCompetitor(TrainingEffort trainingEffort) {
    Integer previousValue = competition.get(trainingEffort);
    if (previousValue == null) {
      previousValue = 0;
    }
    int howMany = previousValue + 1;
    competition.put(trainingEffort, howMany);
  }

  public void addCompetitors(TrainingEffort... trainingEffort) {
    for (TrainingEffort each : trainingEffort) {
      addCompetitor(each);
    }
  }

  public int getBetterTrained(TrainingEffort trainingEffort) {
    int betterTrainedCount = 0;
    for (Entry<TrainingEffort, Integer> eachEntry : competition.entrySet()) {
      if (eachEntry.getKey().compareTo(trainingEffort) < 0) {
        betterTrainedCount = betterTrainedCount + eachEntry.getValue();
      }
    }
    return betterTrainedCount;
  }
}
