package com.overstock.unittesting;

import static com.overstock.unittesting.Medal.BRONZE;
import static com.overstock.unittesting.Medal.GOLD;
import static com.overstock.unittesting.Medal.NONE_TRY_AGAIN_NEXT_TIME;
import static com.overstock.unittesting.Medal.SILVER;
import static com.overstock.unittesting.TrainingEffort.COUCH_POTATO;
import static com.overstock.unittesting.TrainingEffort.HIGH;
import static com.overstock.unittesting.TrainingEffort.IRONMAN;
import static com.overstock.unittesting.TrainingEffort.MEDIUM;
import static com.overstock.unittesting.TrainingEffort.MEH;
import static org.junit.Assert.assertEquals;

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
