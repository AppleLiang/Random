package com.interview;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InterestingItenerary {

  public int latesetPossibleExperience(List<List<Integer>> experiences, Integer i) {
    for (int j = i - 1; j >= 0; j--) {
      if (experiences.get(j).get(1) <= experiences.get(i).get(0)) {
        return j;
      }
    }
    return -1;
  }

  public int scheduleMaximizingInterest(List<List<Integer>> experiences) {
    Collections.sort(
        experiences,
        new Comparator<List<Integer>>() {
          public int compare(List<Integer> l1, List<Integer> l2) {
            if (l1.get(0) < l2.get(0)) {
              return -1;
            } else {
              return 1;
            }
          }
        });

    List<Integer> dp = new ArrayList<Integer>(Collections.nCopies(experiences.size(), 0));
    for (int i = 0; i < experiences.size(); i++) {
      int currentExperienceInterest = experiences.get(i).get(2);
      int previousExperienceIndex = latesetPossibleExperience(experiences, i);

      if (previousExperienceIndex != -1) {
        currentExperienceInterest += dp.get(previousExperienceIndex);
      }

      if (i == 0) {
        dp.set(i, currentExperienceInterest);
      } else {
        dp.set(i, Math.max(currentExperienceInterest, dp.get(i - 1)));
      }
    }

    return dp.get(experiences.size() - 1);
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

    List<List<Integer>> experiences1 =
        asList(asList(1, 11, 10), asList(2, 5, 5), asList(3, 6, 6), asList(5, 10, 2));

    InterestingItenerary test = new InterestingItenerary();
    System.out.println(test.scheduleMaximizingInterest(experiences));
    System.out.println(test.scheduleMaximizingInterest(experiences1));
  }
}
