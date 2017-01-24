package jcg.guideline03;

public class CompliantMutableProtector extends Mutable {

    @Override
    public int[] getArray() {
        return super.getArray().clone();
    }

    @Override
    public void setArray(int[] array) {
        throw new UnsupportedOperationException();
    }

}
