package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.unittesting.Medal.BRONZE;
import static org.unittesting.Medal.GOLD;
import static org.unittesting.Medal.NONE_TRY_AGAIN_NEXT_TIME;
import static org.unittesting.Medal.SILVER;
import static org.unittesting.TrainingEffort.COUCH_POTATO;
import static org.unittesting.TrainingEffort.HIGH;
import static org.unittesting.TrainingEffort.IRONMAN;
import static org.unittesting.TrainingEffort.MEDIUM;
import static org.unittesting.TrainingEffort.MEH;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CustomOlimpicTestRunner.class)
public class OlimpicsBusinessLogicTest {

  private OlimpicsBusinessLogic.Impl logic;

  @Before
  public void setUp() {
    logic = new OlimpicsBusinessLogic.Impl();
  }

  @Test
  public void testWinGoldEasily() {

    TrainingEffort me = HIGH;
    TrainingEffort[] competitors = new TrainingEffort[] { COUCH_POTATO, MEDIUM, MEH };

    Medal actual = logic.win(me, competitors);

    Medal expected = GOLD;
    assertEquals(expected, actual);
  }

  /**
   * Behavior Driven Development
   */
  @Test
  public void testWinGoldTightRace() {
    // GIVEN
    TrainingEffort me = HIGH;
    TrainingEffort[] competitors = new TrainingEffort[] { HIGH, HIGH, HIGH };
    // WHEN
    Medal actual = logic.win(me, competitors);
    // THEN
    Medal expected = GOLD;
    assertEquals(expected, actual);
  }

  @Test
  public void testWinSilverTightRace() {
    // GIVEN
    TrainingEffort me = HIGH;
    TrainingEffort[] competitors = new TrainingEffort[] { HIGH, HIGH, IRONMAN };
    // WHEN
    Medal actual = logic.win(me, competitors);
    // THEN
    Medal expected = SILVER;
    assertEquals(expected, actual);
  }

  @Test
  public void testWinBronze() {
    // GIVEN
    TrainingEffort me = MEDIUM;
    TrainingEffort[] competitors = new TrainingEffort[] { HIGH, HIGH, MEH };
    // WHEN
    Medal actual = logic.win(me, competitors);
    // THEN
    Medal expected = BRONZE;
    assertEquals(expected, actual);
  }

  @Test
  public void testNoMedal() {
    // GIVEN
    TrainingEffort me = MEDIUM;
    TrainingEffort[] competitors = new TrainingEffort[] { IRONMAN, HIGH, IRONMAN };
    // WHEN
    Medal actual = logic.win(me, competitors);
    // THEN
    Medal expected = NONE_TRY_AGAIN_NEXT_TIME;
    assertEquals(expected, actual);
  }

}
