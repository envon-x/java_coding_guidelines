package jcg.guideline03;

public final class UnmodifiableMutable implements IMutable {
    private final IMutable mutable;

    public UnmodifiableMutable(IMutable mutable) {
        if(mutable==null) {
            throw new IllegalArgumentException("mutable cannot be null");
        }
        this.mutable = mutable;
    }

    @Override
    public int[] getArray() {
        return mutable.getArray().clone();
    }

    @Override
    public void setArray(int[] array) {
        throw new UnsupportedOperationException();
    }
}
