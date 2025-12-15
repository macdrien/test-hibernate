package fr.sidranie.training;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.sidranie.training.reservation.Reservation;
import fr.sidranie.training.room.Room;
import fr.sidranie.training.user.User;

import jakarta.persistence.EntityManager;

public class SessionFactoryProvider {
    
    private static EntityManager instance;

    private SessionFactoryProvider() {
    }

    private static void buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();

        try {
            instance = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Room.class)
                    .addAnnotatedClass(Reservation.class)
                    .buildMetadata()
                    .buildSessionFactory()
                    .createEntityManager();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public static EntityManager getEntityManager() {
        if (instance == null) {
            buildSessionFactory();
        }

        return instance;
    }

}
