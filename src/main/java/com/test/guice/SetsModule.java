package com.test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;

public class SetsModule extends AbstractModule {

  @ProvidesIntoSet
  @Named("set1")
  @Singleton
  public TestInterface provideClass1() {
    return new TestClass1();
  }

  @ProvidesIntoSet
  @Named("set1")
  @Singleton
  public TestInterface provideClass2() {
    return new TestClass2();
  }

  @ProvidesIntoSet
  @Named("set2")
  @Singleton
  public TestInterface provideClass3() {
    return new TestClass1();
  }

  @ProvidesIntoSet
  @Named("set2")
  @Singleton
  public TestInterface provideClass4() {
    return new TestClass2();
  }

  @Override
  protected void configure() {}

  public static class TestClass1 implements TestInterface {}

  public static class TestClass2 implements TestInterface {}
}
