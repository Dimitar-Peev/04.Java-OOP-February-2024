package _02_Exercise._07_CollectionHierarchy.classes;

import _02_Exercise._07_CollectionHierarchy.interfaces.MyList;

public class MyListImpl extends Collection implements MyList {
    @Override
    public int getUsed() {
        return this.items.size();
    }

    @Override
    public String remove() {
        return this.items.remove(0);
    }

    @Override
    public int add(String item) {
        this.items.add(0, item);
        return 0;
    }
}
