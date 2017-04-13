package jcg;

public class CloneBypassesConstructor implements Cloneable {
  public CloneBypassesConstructor() {
    System.out.println("CloneByPassConstructor");
  }

  public static void main(String[] args) {
    CloneBypassesConstructor first = new CloneBypassesConstructor();
    try {
      CloneBypassesConstructor second = (CloneBypassesConstructor) first.clone();
      System.out.printf("first:\t%s\nsecond:\t%s\n", first, second);
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
  }
}
