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

public class OlimpicsBusinessLogicImprovedTest {

  private OlimpicsBusinessLogic.Impl logic;

  @Before
  public void setUp() {
    logic = new OlimpicsBusinessLogic.Impl();
  }

  @Test
  public void test() {
    testIt(HIGH, new TrainingEffort[] { COUCH_POTATO, MEDIUM, MEH }, GOLD);
    testIt(HIGH, new TrainingEffort[] { HIGH, HIGH, HIGH }, GOLD);
    testIt(HIGH, new TrainingEffort[] { HIGH, HIGH, IRONMAN }, SILVER);
    testIt(MEDIUM, new TrainingEffort[] { HIGH, HIGH, MEH }, BRONZE);
    testIt(MEDIUM, new TrainingEffort[] { IRONMAN, HIGH, IRONMAN }, NONE_TRY_AGAIN_NEXT_TIME);
  }

  private void testIt(TrainingEffort me, TrainingEffort[] competitors, Medal expected) {
    // WHEN
    Medal actual = logic.win(me, competitors);
    // THEN
    assertEquals(expected, actual);
  }
}
