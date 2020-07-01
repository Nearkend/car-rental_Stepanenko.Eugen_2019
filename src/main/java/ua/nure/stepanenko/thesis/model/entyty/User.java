package ua.nure.stepanenko.thesis.model.entyty;
import ua.nure.stepanenko.thesis.model.constant.Role;

import java.io.Serializable;


public class User implements Serializable {

    private String login;
    private String password;
    private String fullName;
    private String passport;
    private boolean blocked;
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isFilled() {
        return this.login != null &&
                this.password != null &&
                this.fullName != null &&
                this.role != null;
    }

    @Override
    public String toString() {
        return "login - " + login +
                ", password - " + password +
                ", fullName - " + fullName +
                ", passport - " + passport +
                ", blocked - " + blocked +
                ", role - " + role;
    }
}

