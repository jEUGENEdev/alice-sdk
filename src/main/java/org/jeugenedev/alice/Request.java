package org.jeugenedev.alice;

import org.jeugenedev.alice.core.server.request.ServerRequest;
import org.jeugenedev.alice.core.server.response.ServerResponse;

import java.io.IOException;

public interface Request {
    ServerResponse request(ServerRequest serverRequest) throws IOException;
}
