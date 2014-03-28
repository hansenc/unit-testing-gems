package com.overstock.unittesting;

import org.apache.log4j.Logger;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class CustomOlimpicTestRunner extends BlockJUnit4ClassRunner {

  Logger LOGGER = Logger.getLogger(CustomOlimpicTestRunner.class);

  public CustomOlimpicTestRunner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected void runChild(FrameworkMethod method, RunNotifier notifier) {
    LOGGER.debug("About to run " + method.getName());
    super.runChild(method, notifier);
    LOGGER.debug("After running " + method.getName());
  }

  @Override
  protected Statement withBeforeClasses(Statement statement) {
    initializeConfig();
    initializeDb();
    return super.withBeforeClasses(statement);
  }

  @Override
  protected Statement withAfterClasses(Statement statement) {
    return super.withAfterClasses(statement);
  }

  private void initializeConfig() {
    // TODO Auto-generated method stub

  }

  private void initializeDb() {
    // TODO Auto-generated method stub

  }
}
