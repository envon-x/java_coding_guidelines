package jcg.guideline03;

public class NonCompliantMutableProtector extends Mutable {
    @Override
    public int[] getArray() {
        return super.getArray().clone();
    }

    // Still sucks as Mutable's setArray(int[] array) is still exposed
}
