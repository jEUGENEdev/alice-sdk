package org.jeugenedev.alice.builder;

abstract public class Returning<T> {
    private final T prev;

    Returning(T prev) {
        this.prev = prev;
    }

    public T back() {
        return this.prev;
    }
}
