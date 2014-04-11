package org.unittesting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import junit.framework.Assert;

public class ReportWriter extends TestWatcher {

  private final EventLoggerTest eventLoggerTest;

    private FileWriter fileWriter;
  
  public ReportWriter(EventLoggerTest eventLoggerTest, File file) {
    Assert.assertNotNull(eventLoggerTest);
    Assert.assertNotNull(file);
    this.eventLoggerTest = eventLoggerTest;
      try {
      fileWriter = new FileWriter(file);
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return super.apply(base, description);
  }

  @Override
  protected void succeeded(Description description) {
    super.succeeded(description);
    writeToFile(eventLoggerTest + ", success");
  }

  @Override
  protected void failed(Throwable e, Description description) {
    super.failed(e, description);
    writeToFile(eventLoggerTest + ", failed");
  }
  
  private void writeToFile(String content) {
    System.out.println(content);
    try {
      fileWriter.write(content);
      fileWriter.flush();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
