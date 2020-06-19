package com.test.numberformat;

import com.google.common.collect.ImmutableList;
import java.text.NumberFormat;
import java.util.Locale;

public class Test {

  public static void main(String[] args) {
    double n = 0.143;

    helperMethod(n);
  }

  private static void helperMethod(double value) {
    ImmutableList.of(
            new Locale("en", "US"),
            new Locale("sk", "SK"),
            new Locale("da", "DK"),
            Locale.SIMPLIFIED_CHINESE,
            Locale.GERMANY,
            new Locale("sv", "SE"))
        .stream()
        .forEach(
            locale -> {
              // NumberFormat nf = NumberFormat.getInstance(locale);
              NumberFormat nf = NumberFormat.getPercentInstance(locale);
              nf.setMaximumFractionDigits(5);
              // NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

              System.out.println(nf.format(value));
            });
  }
}
