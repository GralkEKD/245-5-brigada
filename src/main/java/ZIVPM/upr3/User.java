package ZIVPM.upr3;

import java.io.Serial;
import java.util.Objects;

public final class User implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 0xC000L;

    private String username;
    private boolean isAdmin;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User() {}

    public User(
            String username,
            boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.username, that.username) &&
                this.isAdmin == that.isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, isAdmin);
    }

    @Override
    public String toString() {
        return "User[" +
                "getUsername=" + username + ", " +
                "isAdmin=" + isAdmin + ']';
    }

}
