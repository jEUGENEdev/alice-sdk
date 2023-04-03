package org.jeugenedev.alice.exception;

import org.jeugenedev.alice.Alice;

public class NoServerException extends RuntimeException {
    public NoServerException() {
        super("You cannot get getInstance() without initializing the server using the method getInstance(int serverPort)");
    }
}
