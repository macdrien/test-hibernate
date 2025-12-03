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
        StringBuilder sb = new StringBuilder();
        sb.append("Password{");
        sb.append(value.isEmpty() ? "" : "**********");
        sb.append('}');
        return sb.toString();
    }

}
