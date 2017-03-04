package jcg;

public class OneCantBeADouble {
  public static final void main(String[] args) {

    int big = 1999999999;
    float fOne = 1.0f;
    // Binary operation, loses precision because of implicit cast
    System.out.println(big * fOne);


    double dOne = 1.0d; // Double instead of float
    System.out.println(big * dOne);
  }
}
