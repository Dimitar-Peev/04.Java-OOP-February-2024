package _02_Exercise._07_CollectionHierarchy.classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    private final int maxSize = 100;
    protected List<String> items;

    protected Collection(){
        this.items = new ArrayList<>(maxSize);
    }
}
