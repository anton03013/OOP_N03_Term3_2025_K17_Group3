package Sequence;

import Selector.Selector;

public class Sequence {
    private Object[] objects;
    private int next = 0;

    public Sequence(int size) {
        objects = new Object[size];
    }

    public void add(Object x) {
        if (next < objects.length) {
            objects[next] = x;
            next++;
        }
    }

    // Inner class SSelector implements Selector
    private class SSelector implements Selector {
        int i = 0;

        @Override
        public boolean end() {
            return i == objects.length;
        }

        @Override
        public Object current() {
            return objects[i];
        }

        @Override
        public void next() {
            if (i < objects.length) i++;
        }
    }

    // Trả về Selector
    public Selector getSelector() {
        return new SSelector();
    }
}
