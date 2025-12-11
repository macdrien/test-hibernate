package fr.sidranie.training.room;

import fr.sidranie.training.room.data.roomName.RoomName;
import fr.sidranie.training.room.data.roomName.RoomNameConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Convert(converter = RoomNameConverter.class)
    private RoomName name;

    public Room() {
    }

    public Room(RoomName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomName getName() {
        return name;
    }

    public void setName(RoomName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name.toString());
        sb.append('}');
        return sb.toString();
    }
}
