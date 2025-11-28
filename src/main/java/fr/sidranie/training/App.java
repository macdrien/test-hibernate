package fr.sidranie.training;

import java.time.LocalDateTime;

import org.hibernate.SessionFactory;

import fr.sidranie.training.domain.Event;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        sessionFactory.inTransaction(session -> {
            session.persist(new Event("The first event"));
            session.persist(new Event("A very old event", LocalDateTime.of(1999, 11, 8, 16, 44)));
        });

        sessionFactory.inTransaction(session -> {
            session.createSelectionQuery("from Event", Event.class)
                .getResultList().forEach(System.out::println);
        });
    }
}
