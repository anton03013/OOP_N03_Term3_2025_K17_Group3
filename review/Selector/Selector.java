package Selector;

public interface Selector {
    boolean end();
    Object current();
    void next();
}