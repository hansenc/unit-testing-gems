package org.unittesting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.unittesting.TrainingEffort.HIGH;
import static org.unittesting.TrainingEffort.IRONMAN;
import static org.unittesting.TrainingEffort.MEDIUM;

import java.util.Arrays;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class IsMetalTest {

  private OlimpicsBusinessLogic.Impl logic;

  @Before
  public void setUp() {
    logic = new OlimpicsBusinessLogic.Impl();
  }

  @DataPoints
  public static TrainingEffort[] me = new TrainingEffort[] { IRONMAN, HIGH, MEDIUM };

  @DataPoints
  public static TrainingEffort[][] competitionICanBeat = new TrainingEffort[][] {
     { HIGH, HIGH, HIGH, HIGH } 
    ,{ MEDIUM, HIGH, IRONMAN } 
    ,{ IRONMAN, HIGH, IRONMAN } 
    ,{ MEDIUM, HIGH, IRONMAN } 
  };

  @Theory
  public void testMetal(TrainingEffort me, TrainingEffort[] competitionICanBeat) {
    assumeTrue(me != MEDIUM);
    Medal actual = logic.win(me, competitionICanBeat);
    System.out.println("me: " + me + " against " + Arrays.asList(competitionICanBeat));
    assertTrue(actual.isMetal());
  }

}
