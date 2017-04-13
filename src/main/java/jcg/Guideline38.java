package jcg;

import java.util.*;

public class Guideline38<T> {

  private T a, b, c[], d;

  private final Hashtable<String, Integer> items;

  public Guideline38(T in) {
    items = new Hashtable<String, Integer>();
    a = in;
    b = in;
    c = (T[]) new Object[10]; d = in;
  }

  @Override
  public String toString() {
    return "Guideline38{" +
            "a=" + a +
            ", b=" + b +
            ", c=" + Arrays.toString(c) +
            ", d=" + d +
            '}';
  }

  public List<String> getStock() {
    List<String> stock = new ArrayList<String>();
    Integer noOfItems; // Number of items left in the inventory
    Enumeration itemKeys = items.keys();
    while (itemKeys.hasMoreElements()) {
      Object value = itemKeys.nextElement();
      if ((noOfItems = items.get(value)) == 0) {
        stock.add((String)value);
      }
    }
    if (stock.isEmpty()) {
      return Collections.EMPTY_LIST; // Always zero-length
    } else {
      return stock; // Return list
    }

  }
}

