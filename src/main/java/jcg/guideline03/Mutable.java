package jcg.guideline03;

public class Mutable implements IMutable {
    private int[] array = new int[10];

    @Override
    public int[] getArray() {
        return array;
    }

    @Override
    public void setArray(int[] array) {
        this.array = array;
    }

}
