package _02_Exercise._07_CollectionHierarchy.classes;

import _02_Exercise._07_CollectionHierarchy.interfaces.Addable;

public class AddCollection extends Collection implements Addable {
    @Override
    public int add(String items) {
        this.items.add(items);
        return this.items.size() - 1;
    }
}
