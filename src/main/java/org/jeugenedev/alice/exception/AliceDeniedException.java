package org.jeugenedev.alice.exception;

public class AliceDeniedException extends RuntimeException {
    public AliceDeniedException() {
        super("Access to Alice was closed");
    }
}
