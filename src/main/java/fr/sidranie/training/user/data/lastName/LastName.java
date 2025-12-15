package fr.sidranie.training.user.data.lastName;

import java.util.Objects;

public class LastName {
    private String value;

    public LastName() {
    }

    public LastName(String value) {
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
        final LastName other = (LastName) obj;
        return Objects.equals(this.value, other.getValue());
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("LastName{")
            .append(value)
            .append('}')
            .toString();
    }
}
