package com.interview;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirbnbInterview {

  static Map<Integer, Integer> cache = new HashMap<>();

  public AirbnbInterview() {}

  public static int maxExperiences(int index, List<List<Integer>> e) {

    if (index >= e.size()) {
      return 0;
    }
    if (index == e.size() - 1) {
      e.get(index).get(0);
    }

    int num = maxExperiences(index + 1, e);
    int nextValid = nextValid(index, e);
    int c = 0;
    if (nextValid < e.size()) {
      c = cache.get(nextValid);
    }

    int ans = Math.max(num, c + e.get(index).get(2));
    cache.put(index, ans);
    return ans;
  }

  static int nextValid(int index, List<List<Integer>> e) {
    int i = index + 1;
    while (i < e.size() && e.get(i).get(1) < e.get(index).get(2)) {
      i++;
    }
    return i;
  }

  public static void main(String[] args) {
    List<List<Integer>> experiences =
        asList(
            asList(2, 5, 5),
            asList(3, 6, 6),
            asList(5, 10, 2),
            asList(4, 10, 8),
            asList(8, 9, 5),
            asList(13, 14, 1),
            asList(13, 17, 5),
            asList(14, 16, 8));

    Collections.sort(experiences, (a, b) -> a.get(1) - b.get(1));
    int answer = maxExperiences(0, experiences);
    System.out.println("Answer = " + answer);
  }
}
