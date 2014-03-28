package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.unittesting.Medal.BRONZE;
import static org.unittesting.Medal.GOLD;
import static org.unittesting.Medal.NONE_TRY_AGAIN_NEXT_TIME;
import static org.unittesting.Medal.SILVER;

import org.junit.Test;

public class MedalTest {

  @Test
  public void testMetal() {
    for (Medal each : Medal.values()) {
      boolean expected = each.ordinal() < 3 ? true:false;
      assertEquals(expected, each.isMetal());
    }
  }

  @Test
  public void testGetMedal() throws Exception {
    testMedal(GOLD, 0);
    testMedal(SILVER, 1);
    testMedal(BRONZE, 2);
    testMedal(NONE_TRY_AGAIN_NEXT_TIME, 3);
    testMedal(NONE_TRY_AGAIN_NEXT_TIME, 4);
    testMedal(NONE_TRY_AGAIN_NEXT_TIME, 5);
    testMedal(NONE_TRY_AGAIN_NEXT_TIME, 10);
    testMedal(NONE_TRY_AGAIN_NEXT_TIME, 100);
  }

  protected void testMedal(Medal medal, int input) {
    assertEquals(medal , Medal.getMedal(input));
  }
}
