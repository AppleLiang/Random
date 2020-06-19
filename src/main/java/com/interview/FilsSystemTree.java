package com.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;

public class FilsSystemTree {

  private Node root;

  public FilsSystemTree() {
    root = new Node("", 0, null, new HashMap<>());
  }

  public void create(String path, int value) throws Exception {
    String[] paths = path.split("/");
    String lastPath = paths[paths.length - 1];
    String[] pathsWithoutLast = Arrays.copyOf(paths, paths.length - 1);
    Node parent = findAndGet(pathsWithoutLast);
    if (parent.children.containsKey(lastPath)) return;
    parent.children.put(lastPath, new Node(lastPath, value, null, new HashMap<>()));
  }

  public void set(String path, int value) throws Exception {
    Node node = findAndGet(path.split("/"));
    node.value = value;
  }

  public int get(String path) throws Exception {
    return findAndGet(path.split("/")).value;
  }

  public void watch(String path, Consumer<Integer> callback) throws Exception {
    Node node = findAndGet(path.split("/"));
    node.callback = callback;
  }

  private Node findAndGet(String[] paths) throws Exception {
    Node cur = root;
    for (int i = 1; i < paths.length; i++) {
      String curPath = paths[i];
      if (!cur.children.containsKey(curPath)) throw new Exception("Invalid Path");
      cur = cur.children.get(curPath);
    }
    return cur;
  }

  private static class Node {
    String key;
    int value;
    Consumer<Integer> callback;
    HashMap<String, Node> children;

    public Node(String key, int value, Consumer<Integer> callback, HashMap<String, Node> children) {
      this.key = key;
      this.value = value;
      this.callback = callback;
      this.children = children;
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
    FilsSystemTree solution = new FilsSystemTree();
    solution.create("/a", 1);
    System.out.println(solution.get("/a"));
    solution.create("/a/b", 2);
    System.out.println(solution.get("/a/b"));
  }
}
