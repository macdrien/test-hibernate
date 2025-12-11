package fr.sidranie.training.user.data.firstName;

public class FirstName {
    private String value;

    public FirstName(String value) {
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
        sb.append("FirstName{");
        sb.append(value);
        sb.append('}');
        return sb.toString();
    }

}
