package fr.sidranie.training.user;

import java.util.List;

import jakarta.persistence.EntityManager;

import fr.sidranie.training.SessionFactoryProvider;
import fr.sidranie.training.user.User;
import fr.sidranie.training.user.data.username.Username;

public class UserRepository {
    private final EntityManager entityManager;

    public UserRepository() {
        this.entityManager = SessionFactoryProvider.getSessionFactory().createEntityManager();
    }

    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class)
            .getResultList();
    }

    public void save(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User already have an id");
        }
        if (findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public User findByUsername(Username username) {
        return entityManager.createQuery("from User where username = :username", User.class)
            .setParameter("username", username)
            .getSingleResultOrNull();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
