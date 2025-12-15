package fr.sidranie.training.user.data.username;

import java.util.Objects;

public class Username {
    private String value;

    public Username() {
    }

    public Username(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Username other = (Username) obj;
        return Objects.equals(this.value, other.getValue());
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Username{")
            .append(value)
            .append('}')
            .toString();
    }
}
