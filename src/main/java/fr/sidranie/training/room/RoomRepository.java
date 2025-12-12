package fr.sidranie.training.room;

import java.util.List;

import fr.sidranie.training.SessionFactoryProvider;
import fr.sidranie.training.room.data.roomName.RoomName;

import jakarta.persistence.EntityManager;

public class RoomRepository {
    private final EntityManager entityManager;

    public RoomRepository() {
        this.entityManager = SessionFactoryProvider.getSessionFactory().createEntityManager();
    }

    public List<Room> findAll() {
        return entityManager.createQuery("from Room", Room.class)
            .getResultList();
    }

    public void save(Room room) {
        if (room.getId() != null) {
            throw new IllegalArgumentException("Room already has an id");
        }
        if (findByName(room.getName()) != null) {
            throw new IllegalArgumentException("Room already exists");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(room);
        entityManager.getTransaction().commit();
    }

    public Room findByName(RoomName name) {
        return entityManager.createQuery("from Room where name = :name", Room.class)
            .setParameter("name", name)
            .getSingleResultOrNull();
    }

    public Room findById(Long id) {
        return entityManager.find(Room.class, id);
    }
}
