package com.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FileSystem {

  Map<String, Node> pathMap;

  public FileSystem() {
    this.pathMap = new HashMap<>();
    this.pathMap.put("", new Node(0));
  }

  public void create(String path, int value) throws Exception {
    if (pathMap.containsKey(path)) return;

    int lastSlashIndex = path.lastIndexOf('/');
    if (!pathMap.containsKey(path.substring(0, lastSlashIndex)))
      throw new Exception("Invalid path");

    pathMap.put(path, new Node(value));
  }

  public void set(String path, int value) throws Exception {
    if (!pathMap.containsKey(path)) {
      throw new Exception("Invalid path");
    }

    pathMap.get(path).value = value;

    String curPath = path;
    while (curPath.length() > 0) {
      if (pathMap.containsKey(curPath)) {
        Node node = pathMap.get(curPath);
        if (node.callback != null) node.callback.accept(node.value);
      }
      int lastSlashIndex = curPath.lastIndexOf('/');
      curPath = curPath.substring(0, lastSlashIndex);
    }
  }

  public int get(String path) throws Exception {
    if (!pathMap.containsKey(path)) throw new Exception("Invalid path");
    return pathMap.get(path).value;
  }

  public void watch(String path, Consumer<Integer> callback) throws Exception {
    if (!pathMap.containsKey(path)) {
      throw new Exception("Invalid path");
    }

    pathMap.get(path).callback = callback;
  }

  private static class Node {
    public int value;
    public Consumer<Integer> callback;

    public Node(int value) {
      this.value = value;
      this.callback = null;
    }

    public Node(int value, Consumer<Integer> callback) {
      this.value = value;
      this.callback = callback;
    }
  }

  public static void main(String[] args) throws Exception {
    Consumer<Integer> consumer =
        new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) {
            System.out.println("current value is " + integer);
          }
        };
    FileSystem solution = new FileSystem();
    solution.create("/a", 1);
    System.out.println(solution.get("/a"));
    solution.create("/a/b", 2);
    System.out.println(solution.get("/a/b"));
    //    solution.create("/c/d", 3);
    //    System.out.println(solution.get("/c"));
    solution.set("/a/b", 4);
    System.out.println(solution.get("/a/b"));
    solution.watch("/a", consumer);
    solution.watch("/a/b", consumer);
    //    solution.set("/d", 5);
    solution.create("/a/b/c", 10);
    System.out.println(solution.get("/a/b/c"));
    solution.set("/a/b/c", 11);
    System.out.println(solution.get("/a/b/c"));
  }
}
