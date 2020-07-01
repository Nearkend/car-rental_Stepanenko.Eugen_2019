package ua.nure.stepanenko.thesis.model.constant;

import java.io.Serializable;

public enum State implements Serializable {

    EXPECTATION, REJECTED, CONFIRMED, PAID, COMPLETED;

    public static State getStateById(int roleId) {
        return State.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}
