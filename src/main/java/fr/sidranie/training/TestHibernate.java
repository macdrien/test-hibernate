package fr.sidranie.training;

import jakarta.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sidranie.training.data.password.Password;
import fr.sidranie.training.data.username.Username;
import fr.sidranie.training.domain.User;

public class TestHibernate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHibernate.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = new User(new Username("macdrien"), new Password("password123"));
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        entityManager.createQuery("from User", User.class)
            .getResultList()
            .forEach(result -> LOGGER.debug("{}", result));
    }
}
