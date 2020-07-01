package ua.nure.stepanenko.thesis.model.constant;

import java.io.Serializable;

public enum Role implements Serializable {

    CLIENT, MANAGER, ADMIN;

    public static Role getRoleById(int roleId) {
        return Role.values()[--roleId];
    }

    public String getName() { return name().toLowerCase(); }

    public int getId() {
        return ordinal() + 1;
    }
}

