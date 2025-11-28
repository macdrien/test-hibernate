package fr.sidranie.training;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import fr.sidranie.training.domain.Event;

public class SessionFactoryProvider {
    
    private static SessionFactory instance;

    private SessionFactoryProvider() {
    }

    private static void buildSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();

        try {
            instance = new MetadataSources(registry)
                    .addAnnotatedClass(Event.class)
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
