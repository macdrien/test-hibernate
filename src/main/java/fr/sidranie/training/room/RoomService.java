package fr.sidranie.training.room;

import java.util.Collection;

import fr.sidranie.training.room.data.roomName.RoomName;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepository();
    }
    
    public Collection<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    
    public Room getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room getRoomByName(RoomName name) {
        return roomRepository.findByName(name);
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }
}
