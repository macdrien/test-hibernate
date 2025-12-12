package fr.sidranie.training;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.sidranie.training.reservation.Reservation;
import fr.sidranie.training.room.Room;
import fr.sidranie.training.user.User;

public class SessionFactoryProvider {
    
    private static SessionFactory instance;

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
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            buildSessionFactory();
        }

        return instance;
    }

}
