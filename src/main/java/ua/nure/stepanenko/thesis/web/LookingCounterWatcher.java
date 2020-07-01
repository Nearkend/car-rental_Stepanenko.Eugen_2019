package ua.nure.stepanenko.thesis.web;

import ua.nure.stepanenko.thesis.model.been.LookingCounter;

public class LookingCounterWatcher implements Runnable {

    private LookingCounter lookingCounter;

    public LookingCounterWatcher(LookingCounter lookingCounter) {
        this.lookingCounter = lookingCounter;
    }

    @Override
    public void run() {
        lookingCounter.setCount(1);
    }
}
