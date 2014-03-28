package org.unittesting;

import java.net.URI;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class CompetitionEvent {

  private final long id;

  private final URI uri;

  public CompetitionEvent(long id, URI uri) {
    this.id = id;
    this.uri = uri;
  }

  public long getId() {
    return id;
  }

  public URI getUri() {
    return uri;
  }

  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return Pojomatic.equals(this, obj);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }

}
