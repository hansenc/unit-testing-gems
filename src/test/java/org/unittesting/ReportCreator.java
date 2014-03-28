package org.unittesting;

import java.io.File;
import java.io.IOException;

import org.junit.rules.TemporaryFolder;

public class ReportCreator extends TemporaryFolder {

  private File folder;
  
  private File file;
  
  
  public ReportCreator() {
    folder = super.newFolder("whateverFolder");
    try {
      file = super.newFile("whateverFile.csv");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void create() throws IOException {
  }


  @Override
  public File getRoot() {
    return folder;
  }

  public File getFile() {
    return file;
  }


  @Override
  protected void after() {
  }

}
