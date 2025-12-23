package fr.sidranie.training.reservation;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.sidranie.training.room.Room;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        SELECT (count(r) > 0)
        FROM Reservation r
        WHERE r.room = :room
          AND (r.reservationBeginDate <= :endDate
            OR :beginDate <= r.reservationEndDate)
    """)
    boolean existsReservationForRoomInInterval(Room room, LocalDate beginDate, LocalDate endDate);
}