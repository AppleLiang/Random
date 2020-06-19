package com.test.guice;

import com.google.inject.AbstractModule;

public class StringsModule extends AbstractModule {
  //  @Provides
  //  @Singleton
  //  @Named("string1")
  //  String provideCount() {
  //    return "hello world";
  //  }
  //
  //  @Provides
  //  @Singleton
  //  @Named("string2")
  //  String provideMessage() {
  //    return "hello world again";
  //  }

  //  @Provides
  //  @Singleton
  //  @Named("notAirbnb")
  //  ObjectMapper objectMapper() {
  //    return new ObjectMapper(new YAMLFactory())
  //        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  //        .registerModule(new JsonOrgModule());
  //  }
  //
  //  @Provides
  //  @Singleton
  //  @Named("yaml")
  //  ObjectMapper objectMapperYaml() {
  //    return new ObjectMapper(new YAMLFactory())
  //        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  //  }

  @Override
  protected void configure() {}
}
