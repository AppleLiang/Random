package com.test.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

public class Test {

  public static void main(String args[]) throws ExecutionException, InterruptedException {
    //    DateTimeFormatter yyyyMMdd =
    // DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);
    //
    //    DateTimeFormatter yyyyMMddHHmmss =
    //        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'H:mm:ss").withZone(ZoneOffset.UTC);
    //
    //    //    ZonedDateTime startsAt = ZonedDateTime.from(yyyyMMddHHmmss.parse("2020-03-10"));
    //
    //    //    Instant instant = Instant.parse("2020-03-10T");
    //
    //    Instant startsAt = Instant.from(yyyyMMddHHmmss.parse("2020-12-31T0:00:00"));
    //
    //    System.out.println();

    String name = null;

    CompletableFuture<String> completableFuture =
        CompletableFuture.supplyAsync(
                () -> {
                  if (name == null) {
                    throw new RuntimeException("Computation error!");
                  }
                  return "Hello, " + name;
                })
            .exceptionally(
                ex -> {
                  handlest(ex);
                  return null;
                })
            .thenApply(response -> response + " response");

    System.out.println(completableFuture.get());
  }

  private static String handlest(Throwable throwable) {
    if (throwable != null) {
      System.out.println("Exception");
    }
    if (throwable instanceof RuntimeException) {
      System.out.println("RuntimeException");
      throw new CompletionException(throwable);
    }
    return null;
  }
}
