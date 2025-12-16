package fr.sidranie.training.reservation;

import java.time.LocalDate;
import java.util.List;

import fr.sidranie.training.SessionFactoryProvider;
import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDate;
import fr.sidranie.training.room.Room;

import jakarta.persistence.EntityManager;

public class ReservationRepository {
    private final EntityManager entityManager;

    public ReservationRepository() {
        this.entityManager = SessionFactoryProvider.getEntityManager();
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

    public boolean existsReservationForRoomInInterval(Room room, LocalDate beginDate, LocalDate endDate) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(r) FROM Reservation r WHERE r.room = :room AND r.reservationBeginDate <= :endDate AND r.reservationEndDate >= :beginDate",
                Long.class)
            .setParameter("room", room)
            .setParameter("beginDate", new ReservationEndDate(beginDate))
            .setParameter("endDate", new ReservationBeginDate(endDate))
            .getSingleResult();
        return count > 0;
    }
}