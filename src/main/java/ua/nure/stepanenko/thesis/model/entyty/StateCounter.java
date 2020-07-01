package ua.nure.stepanenko.thesis.model.entyty;

import ua.nure.stepanenko.thesis.model.constant.State;

public class StateCounter {

    private State state;
    private int count;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "state - " + state +
                ", count - " + count;
    }
}
