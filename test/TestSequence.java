package test;

import Sequence.Sequence;
import Selector.Selector;

public class TestSequence {
    public static void test() {
        Sequence s = new Sequence(10);
        for (int i = 0; i < 10; i++)
            s.add(Integer.toString(i));

        Selector selector = s.getSelector();
        while (!selector.end()) {
            System.out.println(selector.current());
            selector.next();
        }
        System.out.println();
    }
}
