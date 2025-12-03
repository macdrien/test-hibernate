package fr.sidranie.training.user.data.username;

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username{");
        sb.append(value);
        sb.append('}');
        return sb.toString();
    }

}
