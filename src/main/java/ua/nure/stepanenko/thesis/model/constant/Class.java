package ua.nure.stepanenko.thesis.model.constant;

import java.io.Serializable;

public enum Class implements Serializable {

    BUSINESS, SPORT, ECONOMY, MEDIUM;

    public static Class getClassById(int roleId) {
        return Class.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}

