package fr.sidranie.training.user.data.lastName;

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
    public String toString() {
        return new StringBuilder()
            .append("LastName{")
            .append(value)
            .append('}')
            .toString();
    }

}
