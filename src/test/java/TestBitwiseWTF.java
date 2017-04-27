import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestBitwiseWTF {
  @Test
  public void testBitwiseOps() {
    boolean iAmFalse = false;
    assertFalse(iAmFalse & true);
    assertTrue(iAmFalse | true);
    assertFalse(iAmFalse ^ false);
    assertTrue(iAmFalse ^ true);

    assertFalse(justReturn(iAmFalse) & justReturn(true));
    System.out.println("now use the logical &&");
    assertFalse(justReturn(iAmFalse) && justReturn(true));

    assertTrue(true ^ iAmFalse);
  }

  public boolean justReturn(boolean r) {
    System.out.println("justReturn( " + r + " )");
    return r;
  }
}
