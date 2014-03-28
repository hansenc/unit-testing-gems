package com.overstock.unittesting;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.Test;

public class CompetitionEventTest {

  @Test
  public void test() throws Exception {
    long id = 1243;
    URI uri = new URI("http://o.co");
    CompetitionEvent actual = new CompetitionEvent(id, uri);
    assertEquals(id, actual.getId());
    assertEquals(uri, actual.getUri());
  }

}
