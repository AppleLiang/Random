package com.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FindMedian {

  private long search(int[] nums, int k, long left, long right) {
    if (left >= right) return left;

    long res = left;
    long guess = left + (right - left) / 2;
    int count = 0;
    for (int num : nums) {
      if (num <= guess) {
        count++;
        res = Math.max(res, num);
      }
    }

    if (count == k) {
      return res;
    } else if (count < k) {
      return search(nums, k, guess + 1, right);
    } else {
      return search(nums, k, left, res);
    }
  }

  public double findMedian(int[] nums) {
    int len = nums.length;

    if (len % 2 == 1) {
      return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    } else {
      return (double)
              (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE)
                  + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE))
          / 2;
    }
  }

  public double findMedian(String filePath) {
    List<Integer> numsLisnt = readFile(filePath);
    int[] nums = numsLisnt.stream().mapToInt(i -> i).toArray();
    int len = nums.length;

    if (len % 2 == 1) {
      return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    } else {
      return (double)
              (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE)
                  + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE))
          / 2;
    }
  }

  public List<Integer> readFile(String filePath) {
    List<Integer> result = new ArrayList<>();
    File file = new File(filePath);

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));

      String line = null;
      while ((line = br.readLine()) != null) {
        result.add(Integer.parseInt(line));
      }

      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = new int[100];
    Random rand = new Random();
    FindMedian sol = new FindMedian();

    //        System.out.println(sol.findMedian("F:\\nums.txt"));
    //        System.out.println();

    for (int i = 0; i < 100; i++) {
      // int num = rand.nextInt(300);
      nums[i] = 50;
    }
    System.out.println(sol.findMedian(nums));
    Arrays.sort(nums);
    for (int i = 0; i < 100; i++) {
      System.out.print(nums[i] + ",");
    }
    System.out.println();
    System.out.println(nums[49]);
    System.out.println(nums[50]);

    for (int i = 0; i < 100; i++) {
      int num = rand.nextInt(300);
      nums[i] = num;
    }
    System.out.println(sol.findMedian(nums));
    for (int i = 0; i < 100; i++) {
      System.out.print(nums[i] + ",");
    }
    System.out.println();
    Arrays.sort(nums);
    for (int i = 0; i < 100; i++) {
      System.out.print(nums[i] + ",");
    }
    System.out.println();
    System.out.println(nums[49]);
    System.out.println(nums[50]);
  }
}
