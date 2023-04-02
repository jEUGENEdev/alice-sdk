package org.jeugenedev.alice;

import org.jeugenedev.alice.core.server.request.Listenable;

public interface Request {
    void request(Listenable listenable);
}
