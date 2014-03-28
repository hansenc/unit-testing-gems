package org.unittesting;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class MedalFetcherTest {

  private MedalFetcher medalFetcher;
  
  
  @Before
  public void setUp() {
    medalFetcher = new MedalFetcher.Impl();
  }
  @Test
  public void test() {
    CompetitionEvent competitionEvent = mock(CompetitionEvent.class);
    Medal actual = medalFetcher.getMedal(competitionEvent);
    assertEquals(MedalFetcher.Impl.GOLD, actual);
    
  }

}
