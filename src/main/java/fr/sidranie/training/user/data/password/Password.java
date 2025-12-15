package fr.sidranie.training.user.data.password;

public class Password {
    private String value;

    public Password() {
    }

    public Password(String value) {
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
            .append("Password{")
            .append(value.isEmpty() ? "" : "**********")
            .append('}')
            .toString();
    }

}
