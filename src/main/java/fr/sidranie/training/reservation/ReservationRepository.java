package fr.sidranie.training.reservation;

import java.util.List;

import fr.sidranie.training.SessionFactoryProvider;

import jakarta.persistence.EntityManager;

public class ReservationRepository {
    private final EntityManager entityManager;

    public ReservationRepository() {
        this.entityManager = SessionFactoryProvider.getSessionFactory().createEntityManager();
    }

    public List<Reservation> findAll() {
        return entityManager.createQuery("from Reservation", Reservation.class)
            .getResultList();
    }

    public void save(Reservation reservation) {
        if (reservation.getId() != null) {
            throw new IllegalArgumentException("Reservation already has an id");
        }

        entityManager.getTransaction().begin();
        entityManager.persist(reservation);
        entityManager.getTransaction().commit();
    }
}