package fr.sidranie.training.reservation;

import java.time.LocalDateTime;
import java.util.List;

import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void createReservation(Reservation reservation) {
        reservation.setReservationDateTime(ReservationDateTime.now());
        reservationRepository.save(reservation);
    }

}
