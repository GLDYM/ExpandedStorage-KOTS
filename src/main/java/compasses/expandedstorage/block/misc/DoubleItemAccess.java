package compasses.expandedstorage.block.misc;

import compasses.expandedstorage.block.strategies.ItemAccess;

public interface DoubleItemAccess<T> extends ItemAccess<T> {
    T getSingle();

    void setOther(DoubleItemAccess<T> other);

    boolean hasCachedAccess();
}

