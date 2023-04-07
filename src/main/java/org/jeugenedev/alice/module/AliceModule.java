package org.jeugenedev.alice.module;

import org.jeugenedev.alice.Alice;
import org.jeugenedev.alice.Request;

public interface AliceModule {
    Alice.OnStartListener pre();
    Alice.OnStopListener post();
    Request requestListener();
}
