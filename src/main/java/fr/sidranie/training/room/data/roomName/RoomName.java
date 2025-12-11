package fr.sidranie.training.room.data.roomName;

public class RoomName {
    private String value;

    public RoomName(String value) {
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
        sb.append("RoomName{");
        sb.append("value=").append(value);
        sb.append(" Room}");
        return sb.toString();
    }
}
