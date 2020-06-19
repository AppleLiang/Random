package com.test.objectmapper;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

public class Test {

  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper mapper = getMapper();
    SerializeObject serializeObject =
        SerializeObject.builder()
            .uid("1")
            .title("title1")
            .version(1L)
            .content(ImmutableList.of("content1", "content2"))
            .relatedEntry(ImmutableList.of("2"))
            .build();

    SimpleBeanPropertyFilter theFilter =
        SimpleBeanPropertyFilter.serializeAllExcept("related_entry");
    FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);

    String serializedValue = mapper.writeValueAsString(serializeObject);

    try {
      DeserializeObject deserializeObject =
          mapper.readValue(serializedValue, DeserializeObject.class);
      System.out.println();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static ObjectMapper getMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new GuavaModule());
    mapper.registerModule(new JodaModule());
    mapper.registerModule(new AfterburnerModule());
    mapper.registerModule(new ParameterNamesModule());
    mapper.registerModules(new Jdk8Module());
    mapper.registerModules(new JavaTimeModule());
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);

    //    SimpleBeanPropertyFilter theFilter =
    //        SimpleBeanPropertyFilter.serializeAllExcept("related_entry");
    //    FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
    //    mapper.setFilterProvider(filters);

    return mapper;
  }

  @Value
  @Builder
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  private static class SerializeObject {
    @NonNull private final String uid;
    @NonNull private final String title;
    @NonNull private final Long version;
    @NonNull private final ImmutableList<String> content;
    private final ImmutableList<String> relatedEntry;
  }

  @Value
  @Builder
  @JsonFilter("myFilter")
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  private static class DeserializeObject {
    @NonNull private final String uid;
    @NonNull private final String title;
    @NonNull private final Long version;
    @NonNull private final ImmutableList<String> content;
    //     private final ImmutableList<String> relatedEntry;

    private final ImmutableList<SerializeObject> relatedEntry;
  }
}
